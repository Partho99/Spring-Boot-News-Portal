package com.etcenteprise.newsoftheearth.controllers;

import com.etcenteprise.newsoftheearth.entities.*;
import com.etcenteprise.newsoftheearth.exceptions.MathematicalException;
import com.etcenteprise.newsoftheearth.repositories.*;
import com.etcenteprise.newsoftheearth.services.*;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

@CrossOrigin("http://localhost:4200")
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
    private UserVerificationTokenRepository userVerificationTokenRepository;
    @Autowired
    private RoleService roleService;
    @Autowired
    private NewsVotingRepository newsVotingRepository;
    @Autowired
    private NewsCommentsRepository newsCommentsRepository;
    @Autowired
    private NewsSubCategoryRepository newsSubCategoryRepository;

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

    private static final Logger logger = Logger.getLogger(String.valueOf(NewsController.class));

    @GetMapping("/newscategory")
    public List<NewsCategory> showAllNewsCategory() {
        return newsCategoryServices.findAllNewsCategory();
    }

    @RequestMapping("/")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("news", newsServices.findAllNews());
        modelAndView.addObject("mostViewed", viewsServices.getPopularNewsByViews());
        modelAndView.addObject("category", newsCategoryServices.findAllNewsCategory());
        modelAndView.addObject("business", newsCategoryServices.findByCategoryName("business"));
        modelAndView.addObject("politics", newsCategoryServices.findByCategoryName("politics"));
        modelAndView.addObject("entertainment", newsCategoryServices.findByCategoryName("entertainment"));
        modelAndView.addObject("sports", newsCategoryServices.findByCategoryName("sports"));
        modelAndView.addObject("science", newsCategoryServices.findByCategoryName("science"));

        // News obj = new News();
        //logger.info("this is good for checking" + obj);
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


    // this is for interface segregation
    @Autowired
    private NewsSubCategoryRepository.newMethodAdded newMethodAdded;


    @GetMapping("/Bangladesh")
    public ModelAndView bangladesh() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("categorye", newsCategoryServices.findAllNewsCategory());
        mv.addObject("hasNewsSubCategory", newMethodAdded.findNewsSubCategoriesByCategoryName("Sports"));
        mv.addObject("category", newsCategoryServices.findAllNewsCategory());
        System.out.println(newsSubCategoryRepository.fbc());
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
        modelAndView.addObject("newsImage", newsImageServices.findByNews(newsId));
        modelAndView.addObject("newsComment", new NewsComments());
        modelAndView.setViewName("post-details");
        Long userId = null;
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        try {
            NewsUserDetails s = (NewsUserDetails) loggedInUser.getPrincipal();
            userId = s.getId();
            //System.out.println( s.getId());
        } catch (Exception e) {
        }
        if (loggedInUser.getPrincipal().equals("anonymousUser")) {
        } else {
            newsVotingRepository.upVoteCounting(userId, newsId);
        }
        return modelAndView;
    }

    //@PreAuthorize("hasAnyRole('ADMIN')")
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
    public String upload(@RequestPart(value = "file") MultipartFile upload, @RequestParam(value = "CKEditorFuncNum") String callback, HttpServletRequest request) throws IOException {
        System.out.println(upload.getOriginalFilename());
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
    public String saveNews(@Valid @ModelAttribute News news, @RequestPart(value = "file") MultipartFile[] files, BindingResult bindingResult) throws IOException {
        String sourceName;
        String sourceExt;
        File destFile;
        String destFileName;
        News newsImage = new News();
        NewsImage image = new NewsImage();
        long newsId = newsServices.saveNews(news);
        if (files[0].getBytes().length > 0) {
            for (MultipartFile file : files) {
                sourceName = file.getOriginalFilename();
                sourceExt = FilenameUtils.getExtension(sourceName);
                if(!sourceExt.equals("png")){
                    throw new IOException("Only png is valid");
                }
                do {
                    destFileName = RandomStringUtils.randomAlphabetic(8).concat(".").concat(sourceExt);
                    destFile = new File(uploadPath.concat(destFileName));
                }
                while (destFile.exists());
                file.transferTo(destFile);
                image.setActive(true);
                image.setImageCreationDTM(new java.util.Date());
                image.setImageUpdationDTM(new java.util.Date());
                newsImage.setNewsId(newsId);
                image.setNews(newsImage);
                String s = "/images/" + destFileName;
                newsImageServices.saveImage(new NewsImage(s, true, new java.util.Date(), new java.util.Date(), newsImage));
            }
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
        modelAndView.setViewName("success");
        if (bindingResult.hasErrors()) {
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
        return modelAndView;
    }


    @RequestMapping("/access_denied")
    public ModelAndView accessDenied() {
        ModelAndView modelAndView = new ModelAndView("access-denied");
        return modelAndView;
    }


//    @GetMapping("/user/login")
//    public ResponseEntity<String> showLogin(@RequestParam(value = "error", required = false) String error) {
//        if (error != null) {
//            return new ResponseEntity<>("Wrong",HttpStatus.OK);
//        }
//        return new ResponseEntity<>("Success",HttpStatus.OK);
//    }

    @RequestMapping("/user/login")
    public ModelAndView showLogin() {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        if (loggedInUser.getName().equals("anonymousUser")) {
            return new ModelAndView("user-login");
        }
        return new ModelAndView("redirect:/");

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


    //  CUSTOM ERROR HANDLER

    /*    @RequestMapping("/erroreee")
    public String handleError(HttpServletRequest request) {
        // get error status
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

         log error details here

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());
            // display specific error page
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return "404";
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "500";
            } else if (statusCode == HttpStatus.FORBIDDEN.value()) {
                return "403";
            }
        }

        // display generic error
        return "not-found";
    }

    @Override
    public String getErrorPath() {
        return "errorsss";
    }  */


    @PostMapping("/postComment")
    public ResponseEntity<?> postNewsComments(@Valid @ModelAttribute("newsComment") NewsComments comment, @RequestParam("newsId") long newsId) {
        Long userId = null;
        String userName = null;
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        try {
            NewsUserDetails s = (NewsUserDetails) loggedInUser.getPrincipal();
            userId = s.getId();
            userName = s.getUsername();
        } catch (Exception e) {
        }
        if (loggedInUser.getPrincipal().equals("anonymousUser")) {
        } else {
            newsCommentsRepository.saveComment(newsId, userId, comment.getComment());
            User user = new User();
            user.setUsername(userName);
            NewsComments newsComments = new NewsComments();
            newsComments.setUser(user);
            newsComments.setComment(comment.getComment());
            return new ResponseEntity(newsComments, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/showComments")
    public ResponseEntity<List<NewsComments>> showComments(@RequestParam("newsid") long newsId) {
        return new ResponseEntity<>(newsCommentsRepository.showAllCommentsByNewsId(newsId), HttpStatus.OK);
    }

    @GetMapping("/subcategories")
    public ResponseEntity<List<NewsSubCategory>> showAllSubCategory() {
        return new ResponseEntity<>(newsSubCategoryRepository.findAllSubCategory(), HttpStatus.OK);
    }

    @GetMapping("/subcategorybyid/{subcategoryId}")
    public ResponseEntity<NewsSubCategory> showSubCategoryById(@PathVariable("subcategoryId") int id) {
        return new ResponseEntity<>(newsSubCategoryRepository.findBySubCategoryId(id), HttpStatus.OK);
    }

    @GetMapping("/subcategorybycategoryid/{subcategoryIdx}")
    public ResponseEntity<List<NewsSubCategory>> showSubCategoryByCategoryId(@PathVariable("subcategoryIdx") int id) {
        return new ResponseEntity<>(newsSubCategoryRepository.findSubCategoryByCategoryId(id), HttpStatus.OK);
    }

    @GetMapping("/allcategories")
    public ResponseEntity<List<NewsCategory>> showallCategoryByCategoryId() {
        return new ResponseEntity<>(newsCategoryServices.findAllNewsCategory(), HttpStatus.OK);
    }

    @GetMapping("/subcategorybyname/{subcategoryName}")
    public ResponseEntity<NewsSubCategory> showSubCategoryByName(@PathVariable("subcategoryName") String name) {
        return new ResponseEntity<>(newsSubCategoryRepository.findBySubCategoryName(name), HttpStatus.OK);
    }

    @Autowired
    private NewsRepository newsRepository;

    @GetMapping("/users")
    public ResponseEntity<List<Users>> showAllUsers() {
        return new ResponseEntity<>(newsRepository.showAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/souravdata")
    public ModelAndView showAllSouravUsers(HttpServletRequest request) {
        try {
            request.login("", "");
        } catch (Exception ex) {

        }
        ModelAndView view = new ModelAndView();
        view.setViewName("souravdata");
        view.addObject("souravdata", newsRepository.showAllUsers());
        return view;
    }

    @Autowired
    private UsersService usersService;

    @GetMapping("/usersem")
    public ResponseEntity<List<Users>> getAllEmployees(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy) {
        List<Users> list = usersService.getAllEmployees(pageNo, pageSize, sortBy);

        return new ResponseEntity<List<Users>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/errorEx")
    public void showError() throws MathematicalException {
        int[] arr;
        arr = new int[3];

        System.out.println(arr[5]);
    }
}