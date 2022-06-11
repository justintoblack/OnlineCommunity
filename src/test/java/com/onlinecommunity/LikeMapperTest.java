package com.onlinecommunity;

import com.onlinecommunity.mapper.LikeMapper;
import com.onlinecommunity.pojo.Like;
import com.onlinecommunity.service.MomentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;

@SpringBootTest
public class LikeMapperTest {

    @Autowired
    LikeMapper likeMapper;

    @Autowired
    MomentService momentService;

    @Test
    public void testLike() {
        Like like = new Like();
        like.setMomentId(5);
        like.setLikeUid(3);
        like.setMomentUid(3);
        like.setLikeTime(new Timestamp(System.currentTimeMillis()));
        likeMapper.saveLike(like);
    }

    @Test
    public void testGetOneLike(){
        Integer testUid = 3;
        Integer testMomentId = 22;
        System.out.println("likeMapper.getOneLikeByLikeUidMomentId(): " + likeMapper.getOneLikeByLikeUidMomentId(testUid, testMomentId));
    }

    @Test
    public void testAllLikebyUid(){
        Integer testUid = 3;
        System.out.println("likeMapper.getAllLikesByUid(testUid) = " + likeMapper.getAllLikesByUid(testUid));
    }

    @Test
    public void testLikeService(){
        Integer testUid = 3;
        Integer testMomentId = 16;
        System.out.println(momentService.like(testMomentId, testUid));
    }
}
