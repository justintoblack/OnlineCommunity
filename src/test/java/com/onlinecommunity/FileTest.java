package com.onlinecommunity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;


@SpringBootTest
public class FileTest {
    @Value("${server.uploadPath}")
    String uploadPath;

    @Test
    public void testFile(){
        System.out.println("uploadPath = " + uploadPath);
        String filename = "file1";
        String suffix = ".jpg";
        String filePath = uploadPath + "/pictures";
        String fileurl = filePath + "/" + filename + suffix;
        System.out.println("fileurl = " + fileurl);
        File targetFile = new File(filePath);
        if (!targetFile.exists()){
            targetFile.mkdirs();
        }
    }
}
