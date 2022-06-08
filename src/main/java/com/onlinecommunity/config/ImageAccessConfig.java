package com.onlinecommunity.config;

import com.onlinecommunity.util.MyEnvBeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @create 2022/5/30-15:02
 */
@Configuration
@Slf4j
public class ImageAccessConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String accessPath = MyEnvBeanUtil.getProperty("file.access-path");
        String location = MyEnvBeanUtil.getProperty("file.real-path");
        System.out.println("accessPath = " + accessPath);
        System.out.println("location = " + location);
        registry.addResourceHandler(accessPath).addResourceLocations(location);

    }


}
