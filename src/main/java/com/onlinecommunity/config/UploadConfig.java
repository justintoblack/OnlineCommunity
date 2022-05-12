package com.onlinecommunity.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@PropertySource("classpath:upload.properties")
@ConfigurationProperties(prefix = "upload")
public class UploadConfig {
    String path;
    Integer pictureMaxSize;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getPictureMaxSize() {
        return pictureMaxSize;
    }

    public void setPictureMaxSize(Integer pictureMaxSize) {
        this.pictureMaxSize = pictureMaxSize;
    }
}
