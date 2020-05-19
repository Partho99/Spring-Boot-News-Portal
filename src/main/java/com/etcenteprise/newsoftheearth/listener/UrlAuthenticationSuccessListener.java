package com.etcenteprise.newsoftheearth.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.util.RedirectUrlBuilder;

public class UrlAuthenticationSuccessListener extends SimpleUrlAuthenticationSuccessHandler {
    @Autowired
    public UrlAuthenticationSuccessListener(RedirectUrlBuilder redirectUriProvider) {
        setUseReferer(true);   // use referer
        //  setTargetUrlParameter(REDIRECT_PARAMETER);   // if you want to use query param
    }

    // determine target url if you want override
    //  @Override
    //protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response){
    // }
}