package com.onlinecommunity;

import com.onlinecommunity.mapper.LikeMapper;
import com.onlinecommunity.pojo.Like;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;

@SpringBootTest
public class LikeMapperTest {

    @Autowired
    LikeMapper likeMapper;

    @Test
    public void testLike() {
        Like like = new Like();
        like.setMid(4);
        like.setLuid(3);
        like.setMuid(3);
        like.setLtime(new Timestamp(System.currentTimeMillis()));
        likeMapper.likeMoment(like);
    }
}
