package com.etcenteprise.newsoftheearth.controllers;

import com.etcenteprise.newsoftheearth.entities.*;
import com.etcenteprise.newsoftheearth.repositories.NewsVotingRepository;
import com.etcenteprise.newsoftheearth.repositories.UserVerificationTokenRepository;
import com.etcenteprise.newsoftheearth.services.*;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

//@CrossOrigin("http://localhost:4200")
@RestController
public class NewsController {


    @Autowired
    private NewsServices newsServices;

    @Autowired
    private NewsCategoryServices newsCategoryServices;

    @Autowired
    private ViewsServices viewsServices;

    @Autowired
    private NewsImageServices newsImageServices;

    @Autowired
    private UserService userService;

    @Autowired
    private UserSecurityService userSecurityService;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private EmailService emailService;

    @Autowired
    UserVerificationTokenRepository userVerificationTokenRepository;

    @Autowired
    RoleService roleService;

    @Autowired
    NewsVotingRepository newsVotingRepository;

    @GetMapping("/news")
    public ResponseEntity<List<News>> getAllNews() {
        return new ResponseEntity<>(newsServices.findAllNews(), HttpStatus.OK);
    }

    @GetMapping("/news/sasa")
    public ResponseEntity<List<News>> getAllNewsss() {
        return new ResponseEntity<>(newsServices.findAllNews(), HttpStatus.OK);
    }

    @GetMapping("/news/{id}")
    public ResponseEntity<News> showOneNews(@PathVariable long id) {
        return new ResponseEntity<>(newsServices.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("/news/{id}")
    public String deleteOneNews(@PathVariable long id) {
        newsServices.deleteById(id);
        return "successfully deleted";
    }

    @PostMapping("/news")
    public News addNews(@RequestBody News news) {
        newsServices.saveNews(news);
        return news;
    }

    @GetMapping("/newscategory")
    public List<NewsCategory> showAllNewsCategory() {
        return newsCategoryServices.findAllNewsCategory();
    }

    @RequestMapping("/")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {

        try {
//            Cookie uiColorCookie = new Cookie("color", "red");
//            response.addCookie(uiColorCookie);
            Cookie[] cookie = request.getCookies();
            //System.out.println(cookie[0].getName());
        } catch (Exception e) {

        }



//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String currentPrincipalName = authentication.getName();
//        System.out.println(currentPrincipalName);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("news", newsServices.findAllNews());
        modelAndView.addObject("mostViewed", viewsServices.getPopularNewsByViews());
        modelAndView.setViewName("index");

//        final String appUrl = "http://" + "localhost" + ":" + 8080 + "/news";
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo("das.partho99@gmail.com");
//        message.setSubject("Testing spring mail");
//        message.setText(appUrl);
//        javaMailSender.send(message);
        return modelAndView;
    }

    @GetMapping("/fragments")
    public ModelAndView showNavBar() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("showNavBar", newsCategoryServices.findAllNewsCategory());
        modelAndView.setViewName("main");
        return modelAndView;
    }

    @GetMapping("bangladesh")
    public ModelAndView bangladesh() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("category-bangladesh");
        return mv;
    }

    @GetMapping("/popularnews")
    public List<Views> popularNews() {
        //System.out.print(viewsServices.getPopularNewsByViews());
        return viewsServices.getPopularNewsByViews();
    }

    @GetMapping("/{categoryName}")
    public List<News> showAllNewsByCategoryName(@PathVariable("categoryName") String categoryName) {
        return newsCategoryServices.findByCategoryName(categoryName);
    }

    @GetMapping("/{categoryName}/news/{newsHeading}/{newsId}")
    public ModelAndView showSpecificNews(@PathVariable("categoryName") String categoryName, @PathVariable("newsHeading") String newsHeading, @PathVariable("newsId") long newsId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("singleNews", newsServices.findById(newsId));
        modelAndView.addObject("newsImage",newsImageServices.findByNews(newsId));
        modelAndView.setViewName("post-details");
        Long userId = null;
        try {
            Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
            NewsUserDetails s = (NewsUserDetails) loggedInUser.getPrincipal();
            userId = s.getId();
            //System.out.println( s.getId());
        } catch (Exception e) {
        }
        newsVotingRepository.upVoteCounting(userId,newsId);
        return modelAndView;
    }

    //    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/newsUpload")
    public ModelAndView newsUpload() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("news", new News());
        modelAndView.addObject("newsCategory", newsCategoryServices.findAllNewsCategory());
        modelAndView.setViewName("newsuploader");
        return modelAndView;
    }

    @Value("${image.upload.path}")
    String uploadPath;

    @Value("${image.upload.uri}")
    String uploadUri;

    @PostMapping("/image/upload")
    public String upload(@RequestPart MultipartFile upload, @RequestParam(value = "CKEditorFuncNum") String callback, HttpServletRequest request) throws IOException {
        String sourceName = upload.getOriginalFilename();
        String sourceExt = FilenameUtils.getExtension(sourceName);
        File destFile;
        String destFileName;
        do {
            destFileName = RandomStringUtils.randomAlphabetic(8).concat(".").concat(sourceExt);
            destFile = new File(uploadPath.concat(destFileName));
        } while (destFile.exists());
        destFile.getParentFile().mkdirs();
        upload.transferTo(destFile);
        String imgUrl = request.getScheme().concat("://").concat(request.getServerName()).concat(":").concat(String.valueOf(request.getServerPort())).concat(uploadUri).concat(destFileName);
        StringBuffer sb = new StringBuffer();
        sb.append("<script type='text/javascript'>window.parent.CKEDITOR.tools.callFunction(");
        sb.append(callback);
        sb.append(",'");
        sb.append(imgUrl);
        sb.append("','image uploaded successfully!!')</script>");
        return sb.toString();
    }

    @PostMapping("/saveNewsCredentials")
    public String saveNews(@Valid @ModelAttribute News news, @RequestParam("files") MultipartFile[] files, BindingResult bindingResult) throws IOException {
        String sourceName;
        String sourceExt;
        File destFile;
        String destFileName;
        News newsImage = new News();
        NewsImage image = new NewsImage();
        long newsId = newsServices.saveNews(news);
        for (MultipartFile file : files) {
            sourceName = file.getOriginalFilename();
            sourceExt = FilenameUtils.getExtension(sourceName);
            do {
                destFileName = RandomStringUtils.randomAlphabetic(8).concat(".").concat(sourceExt);
                destFile = new File(uploadPath.concat(destFileName));
            }
            while (destFile.exists());
            file.transferTo(destFile);
            //image.setImageSource(uploadPath.concat(destFileName));
            //image.setImageSource("/image/"+destFileName);
            image.setActive(true);
            image.setImageCreationDTM(new java.util.Date());
            image.setImageUpdationDTM(new java.util.Date());
            newsImage.setNewsId(newsId);
            image.setNews(newsImage);
            String s = "/images/"+destFileName;
            newsImageServices.saveImage(new NewsImage(s, true, new java.util.Date(), new java.util.Date(), newsImage));
        }
        return "success";
    }

    @GetMapping("/showCate")
    public List<NewsCategory> imageCate() {

        return newsCategoryServices.findAllNewsCategory();
    }

    @GetMapping("/registration")
    public ModelAndView registration(Model model) {
//        model.addAttribute("userForm", new User());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userForm", new User());
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @PostMapping("/registration")
    public ModelAndView registration(@Valid @ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

//            Role role1 = new Role("ADMIN");
//            roleService.saveRole(role1);
//            Role role2 = new Role("USER");
//            roleService.saveRole(role2);

        modelAndView.setViewName("success");
        if (bindingResult.hasErrors()) {
//            List<ObjectError> errors = bindingResult.getAllErrors();
//            for (ObjectError error : errors) {
//                System.out.println(error.getDefaultMessage());
//            }
            modelAndView.setViewName("registration");
            modelAndView.addObject("userForm", userForm);
            return new ModelAndView("registration");
        }
        userService.save(userForm);
        String token = userVerificationTokenRepository.constructToken();
        userVerificationTokenRepository.saveToken(userForm, token);

        final String appUrl = "http://" + "localhost" + ":" + 8080 + "/verifyingUser/" + userForm.getId() + "/" + token;
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(userForm.getEmail());
        message.setSubject("Testing spring mail");
        message.setText(appUrl);
        javaMailSender.send(message);

        //userSecurityService.autoLogin(userForm.getUsername(), userForm.getConfirmPassword());
        return modelAndView;
    }


    @RequestMapping("/access_denied")
    public ModelAndView accessDenied() {
        ModelAndView modelAndView = new ModelAndView("access-denied");
        return modelAndView;
    }


    @RequestMapping("/user/login")
    public ModelAndView showLogin(Authentication authentication) {
        //System.out.print(authentication);
        return new ModelAndView("user-login");
    }

    @PostMapping("/sendingEmail")
    public MailRequestAndResponse sendEmail(@RequestBody MailRequestAndResponse request) {
        Map<String, Object> model = new HashMap<>();
        model.put("name", request.getName());
        model.put("location", "Bangladesh Dhaka");
        return emailService.sendEmail(request, model);
    }

    @GetMapping("/verifyingUser/{id}/{token}")
    public ModelAndView verifyingUser(@PathVariable("id") long id, @PathVariable("token") String token) {

        User user = new User();
        user.setId(id);
        if (userService.verifyingUser(user, token)) {
            ModelAndView mv = new ModelAndView("user-login");
            return mv;
        }
        return null;
    }

    @GetMapping("/findbyusername")
    public Optional<User> user() {

        Optional<User> user = userService.findByUsername("partho");

        System.out.println(user.isPresent());
        return
                userService.findByUsername("partho");
    }

//    @RequestMapping("/erroreee")
//    public String handleError(HttpServletRequest request) {
//        // get error status
//        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
//
//        //  TODO: log error details here
//
//        if (status != null) {
//            int statusCode = Integer.parseInt(status.toString());
//            // display specific error page
//            if (statusCode == HttpStatus.NOT_FOUND.value()) {
//                return "404";
//            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
//                return "500";
//            } else if (statusCode == HttpStatus.FORBIDDEN.value()) {
//                return "403";
//            }
//        }
//
//        // display generic error
//        return "not-found";
//    }
//
//    @Override
//    public String getErrorPath() {
//        return "errorsss";
//    }
}