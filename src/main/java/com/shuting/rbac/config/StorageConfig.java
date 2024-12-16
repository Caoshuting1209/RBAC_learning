package com.shuting.rbac.config;

import com.shuting.rbac.common.UploadFileReq;
import com.shuting.rbac.config.properties.UploadProperties;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableConfigurationProperties(value = UploadProperties.class)
public class StorageConfig implements WebMvcConfigurer {
    @Autowired
    private UploadProperties uploadProperties;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String[] locations = {"classpath:/META-INF/resources","classpath:/resources",
                "classpath:/static/", "classpath:/public/",
                "file:" + uploadProperties.getStaticDir()
        };
        registry.addResourceHandler("/static/**")
                .addResourceLocations(locations);
    }
}
