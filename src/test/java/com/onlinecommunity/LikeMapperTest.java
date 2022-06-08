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
        like.setMomentId(4);
        like.setLikeUid(3);
        like.setMomentUid(3);
        like.setLikeTime(new Timestamp(System.currentTimeMillis()));
        likeMapper.saveLike(like);
    }
}
