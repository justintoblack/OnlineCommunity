package com.onlinecommunity;

import com.onlinecommunity.config.UploadConfig;
import com.onlinecommunity.util.MyEnvBeanUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ConfigTest {
    @Autowired
    UploadConfig uplaodConfig;

    @Test
    public void testConfig(){
        System.out.println("uplaodConfig.getPath() = " + uplaodConfig.getPath());
        System.out.println("uplaodConfig.getPictureMaxSize() = " + uplaodConfig.getPictureMaxSize());
        System.out.println("MyEnvBeanUtil.getPictureMaxSize() = " + MyEnvBeanUtil.getPictureMaxSize());
    }
}
