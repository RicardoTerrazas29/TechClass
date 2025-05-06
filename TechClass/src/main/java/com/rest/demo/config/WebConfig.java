package com.rest.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Servir imágenes desde el directorio imágenes-guardar
        String path = "file:" + System.getProperty("user.dir") + "/imagenes-guardar/";
        registry.addResourceHandler("/imagenes-guardar/**").addResourceLocations(path);
        
        // Servir imágenes desde la raíz (sin prefijo)
        String rootPath = "file:" + System.getProperty("user.dir") + "/";
        registry.addResourceHandler("/**").addResourceLocations(rootPath);
    }
}
