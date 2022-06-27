package com.onlinecommunity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@Slf4j
//@SpringBootApplication
//public class SpringbootApplication {
//
//    public static void main(String[] args) {
//
//        SpringApplication.run(SpringbootApplication.class, args);
//        log.info("项目启动成功。。。");
//    }
//
//}
@SpringBootApplication
public class SpringbootApplication extends SpringBootServletInitializer{
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SpringbootApplication.class);
    }

    public static void main(String[] args){
        SpringApplication.run(SpringbootApplication.class, args);
    }
}
