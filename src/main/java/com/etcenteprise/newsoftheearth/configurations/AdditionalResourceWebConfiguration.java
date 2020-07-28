package com.etcenteprise.newsoftheearth.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AdditionalResourceWebConfiguration implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/images/**")
                .addResourceLocations("file:/home/partho/IdeaProjects/newsoftheearth/src/main/resources/static/images/");
        registry
                .addResourceHandler("/webjars/**")
                .addResourceLocations("/webjars/");
    }
}
