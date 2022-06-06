package com.onlinecommunity;

import com.onlinecommunity.mapper.MomentMapper;
import com.onlinecommunity.mapper.UserMapper;
import com.onlinecommunity.pojo.Moment;
import com.onlinecommunity.pojo.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @create 2022/5/31-9:46
 */

@SpringBootTest
public class MomentMapperTest {
    @Autowired
    MomentMapper momentMapper;

    @Autowired
    UserMapper userMapper;

    @Test
    public void testListAllMoments() {
        List<Moment> momentList;
        momentList = momentMapper.listAllMoments();
        System.out.printf("找到%d条动态：\n", momentList.size());
        momentList.forEach(System.out::println);
        System.out.println("输出结束。");
    }

    @Test
    public void testGetAllMomentsByUid() {
        Integer testUid = 4;
        System.out.println("testUid = " + testUid);
        List<Moment> momentList;
        momentList = momentMapper.getAllMomentsByUid(testUid);
        for (Moment moment : momentList) {
            System.out.println(moment);
        }
    }

    @Test
    public void testGetOneMomentByMomentId() {
        Integer momentId = 2;
        System.out.println("momentId = " + momentId);
        Moment moment = momentMapper.getOneMomentByMomentId(momentId);
        System.out.println(moment);
    }

    @Test
    public void testGetActiveMomentsByPage() {
        Page page = new Page();
        page.setNextMomentId(11);
        page.setPageSize(5);
        List<Moment> momentList;
        momentList = momentMapper.getActiveMomentsByPage(page);
        System.out.println("page = " + page);
        System.out.println("momentList = " + momentList);
        if (momentList != null) {
            for (Moment moment : momentList) {
                System.out.println(moment);
            }
        }
    }

    @Test
    public void testGetAllMomentByUid(){
        Integer maxUid = userMapper.getMaxUid();
        System.out.println("maxUid = " + maxUid);
        Random random = new Random();
        Integer testUid = random.nextInt(maxUid);
        System.out.println("随机生成testUid = " + testUid);
        List<Moment> momentList = momentMapper.getAllMomentsByUid(testUid);
        for (Moment moment : momentList) {
            System.out.println(moment);
        }

        System.out.println("momentList.size() = " + momentList.size());
        Integer allMomentCount = momentMapper.getAllMomentCountByUid(testUid);
        System.out.println("allMomentCount = " + allMomentCount);
        System.out.println("(allMomentCount == momentList.size()) = " + (allMomentCount == momentList.size()));

    }


    @Test
    public void testSaveMoment() {

        Random random = new Random();

        //generating test data
        Integer maxUid = userMapper.getMaxUid();
        Integer testUid = random.nextInt(maxUid);
        String testContent = "test内容" + random.nextInt(20000);
        Boolean testIsActive = random.nextInt(2) == 1;
        Timestamp testMomentTime = new Timestamp(new Date().getTime());
        Timestamp testDeletedAt = null;
        if (!testIsActive) {
            testDeletedAt = new Timestamp(new Date().getTime());
        }
        Integer testPictureCount = random.nextInt(10);
        Integer testLikeCount = random.nextInt(Integer.MAX_VALUE);
        Integer testCommentCount = random.nextInt(Integer.MAX_VALUE);
        Integer testRepostCount = random.nextInt(Integer.MAX_VALUE);

        Moment newMoment = new Moment();
        System.out.println("-------before-------");
        System.out.println("newMoment = " + newMoment);
        System.out.println("momentMapper.getAllMomentCount() = " + momentMapper.getAllMomentCount());
        System.out.println("-------before-------");

        newMoment.setUid(testUid);
        newMoment.setContent(testContent);
        newMoment.setIsActive(testIsActive);
        newMoment.setMomentTime(testMomentTime);
        newMoment.setDeletedAt(testDeletedAt);
        newMoment.setPictureCount(testPictureCount);
        newMoment.setLikeCount(testLikeCount);
        newMoment.setCommentCount(testCommentCount);
        newMoment.setRepostCount(testRepostCount);

        System.out.println("随机生成的newMoment = " + newMoment);

        //try saving
        System.out.println("\n-------after-------");
        momentMapper.saveMoment(newMoment);
        System.out.println("newMoment = " + newMoment);
        System.out.println("momentMapper.getAllMomentCount() = " + momentMapper.getAllMomentCount());
        System.out.println("-------after-------");
    }
}
