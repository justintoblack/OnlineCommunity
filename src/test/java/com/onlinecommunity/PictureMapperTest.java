package com.onlinecommunity;

import com.onlinecommunity.mapper.PictureMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @create 2022/5/30-19:56
 */

@SpringBootTest
@Slf4j
public class PictureMapperTest {

    @Autowired
    PictureMapper pictureMapper;

    List<String> urlList;

    @Test
    public void testMapper(){
        urlList = new ArrayList<>();
        urlList.add("/pic/1/xx1.jpg");
        urlList.add("/pic/1/xx2.jpg");
        urlList.add("/pic/1/xx22.jpg");
        urlList.add("/pic/1/xx225.jpg");

        log.info("save : {}",pictureMapper.savePicturesUrl(urlList, 2));
    }
}
