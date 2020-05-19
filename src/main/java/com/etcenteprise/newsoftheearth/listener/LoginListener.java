package com.etcenteprise.newsoftheearth.listener;

import com.etcenteprise.newsoftheearth.utilities.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginListener implements ApplicationListener<InteractiveAuthenticationSuccessEvent> {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public void onApplicationEvent(InteractiveAuthenticationSuccessEvent event) {
        UserDetails userDetails = (UserDetails) event.getAuthentication().getPrincipal();
        if (userDetails != null) {
            final String jwt = jwtUtil.generateToken(userDetails);
            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
            HttpServletResponse response = ((ServletRequestAttributes) requestAttributes).getResponse();
            createJwtCookie(jwt, response);
        }
    }

    public void createJwtCookie(String jwtToken, HttpServletResponse response) {
        try {
            Cookie cookie = new Cookie("SSID", jwtToken);
            cookie.setHttpOnly(true);
            /*Expiration is in 1 week*/
            cookie.setMaxAge(7 * 24 * 60 * 60);
            cookie.setPath("/");
            response.addCookie(cookie);
        } catch (Exception e) {

        }
    }
}
