package com.articles.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static com.articles.controller.main.Main.uploadPath;

@Configuration
public class MVCConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String path = uploadPath;

        registry.addResourceHandler("/css/**").addResourceLocations("file:" + path + "/css/");
        registry.addResourceHandler("/js/**").addResourceLocations("file:" + path + "/js/");
        registry.addResourceHandler("/img/**").addResourceLocations("file:" + path + "/img/");
    }
}
