package com.etcenteprise.newsoftheearth.services;

import com.etcenteprise.newsoftheearth.entities.NewsUserDetails;
import com.etcenteprise.newsoftheearth.entities.User;
import com.etcenteprise.newsoftheearth.repositories.UserRepository;
import com.etcenteprise.newsoftheearth.utilities.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Component("userdetailsservice")
@Service
public class NewsUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        user.orElseThrow(() -> new UsernameNotFoundException("username not found"));
        if (user != null) {
            final String jwt = jwtUtil.generateToken(username);
            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
            HttpServletResponse response = ((ServletRequestAttributes)requestAttributes).getResponse();
            createJwtCookie(jwt,response);
        }
        return user.map(NewsUserDetails::new).get();
    }


    public void createJwtCookie(String jwtToken, HttpServletResponse response) {
        try {
            Cookie uiColorCookie = new Cookie("SSID", jwtToken);
            response.addCookie(uiColorCookie);
        } catch (Exception e) {

        }

    }
}
