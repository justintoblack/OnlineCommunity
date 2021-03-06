package com.onlinecommunity;

import com.onlinecommunity.config.UploadConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


@SpringBootTest
public class FileTest {
    @Autowired
    UploadConfig uploadConfig;

    @Test
    public void testFile() throws IOException {
        System.out.println("uploadPath = " + uploadConfig.getPath());
        String filename = "file1";
        String suffix = ".jpg";
        String filePath = uploadConfig.getPath() + "/pictures/";
        String fileurl = filePath + filename + suffix;
        System.out.println("fileurl = " + fileurl);
        File targetFile = new File(filePath);
        if (!targetFile.exists()){
            targetFile.mkdirs();
        }


        File file = new File("D:/onlinecommunity/upload/pictures/233.txt");
        if (!file.exists()){
            System.out.println(file.getName() + "not exists, creates one.");
            file.createNewFile();
        }
        System.out.println(file.isFile());
        System.out.println(file.getName());

        FileOutputStream outputStream = new FileOutputStream(filePath + file.getName());
        String data = "一行文本。。。0-0";
        byte[] array = data.getBytes();

        outputStream.write(array);
        outputStream.flush();
        outputStream.close();
    }
}
