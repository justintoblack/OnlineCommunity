package com.onlinecommunity.util;

import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * @create 2022/5/19-20:43
 */
@Configuration
public class MyEnvBeanUtil implements EnvironmentAware {
    private static Environment env;

    public static String getProperty(String key) {
        return env.getProperty(key);
    }

    public static String getPictureMaxSize() {
        return env.getProperty("upload.picture-max-size");
    }

    @Override
    public void setEnvironment(Environment environment) {
        env = environment;
    }
}
