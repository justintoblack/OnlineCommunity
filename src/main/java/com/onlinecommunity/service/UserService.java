package com.onlinecommunity.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.onlinecommunity.mapper.FollowingMapper;
import com.onlinecommunity.mapper.UserInfoMapper;
import com.onlinecommunity.mapper.UserMapper;
import com.onlinecommunity.pojo.Page;
import com.onlinecommunity.pojo.User;
import com.onlinecommunity.pojo.UserInfo;
import com.onlinecommunity.result.Result;
import com.onlinecommunity.result.ResultCode;
import com.onlinecommunity.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserInfoMapper userInfoMapper;

    @Autowired
    FollowingMapper followingMapper;

    /**
     *
     * @param user 要注册的封装的User对象
     * @return Result
     */
    public Result register(User user) {
        if ("".equals(user.getUsername())) {
            return Result.failure(ResultCode.NULL_USERNAME);
        }
        if ("".equals(user.getPassword())) {
            return Result.failure(ResultCode.NULL_PASSWORD);
        }

        Integer uid = userMapper.getUidByUsername(user.getUsername());
        if (uid != null) {
            return Result.failure(ResultCode.EXIST_USERNAME);
        }
        userMapper.saveUser(user);
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(user.getUsername());
        userInfo.setRealName(user.getUsername());
        userInfo.setPhone(user.getPhone());
        userInfo.setEmail(user.getEmail());
        userInfo.setAvatarUrl("/pic/image.jpg");
        userInfo.setIsFollowing(false);
        userInfoMapper.saveUserInfo(userInfo);

        return Result.success();
    }

    /**
     *
     * @param user 要登录的封装的User对象
     * @return Result
     */
    public Result login(User user) {
        Integer uid = userMapper.getUidByUsername(user.getUsername());
        if (uid != null) {
            String existUserPwd = userMapper.getPasswordByUid(uid);
            if (existUserPwd.equals(user.getPassword())) {
                String token = JwtUtil.sign(user.getUsername());

                HashMap<String, String> map = new HashMap<>();
                map.put("uid",uid.toString());
                map.put("token",token);

                Jedis jedis = new Jedis("127.0.0.1", 6379);
                List<UserInfo> allFollowingByUid = followingMapper.getAllFollowingByUid(uid);
                for (UserInfo userInfo : allFollowingByUid)
                {
                    jedis.sadd(uid.toString(),userInfo.getUid().toString());
                }
                jedis.close();
                Result result = Result.success();
                result.setData(map);
                return result;
            } else {
                return Result.failure(ResultCode.WRONG_PASSWORD);
            }
        } else {
            return Result.failure(ResultCode.NONEXISTENT_USERNAME);
        }
    }

    public Result setPassWord(Integer uid, String password){
        User userByUid = userMapper.getUserByUid(uid);
        if (userByUid == null)
            return Result.failure(ResultCode.NULL_UID);
        if ("".equals(password))
        {
            return Result.failure(ResultCode.NULL_PASSWORD);
        }
        userByUid.setPassword(password);
        userMapper.setPassword(userByUid);
        return Result.success();
    }


    public Result getSelfInfo(Integer uid, Integer infoUid) {

        UserInfo userInfoByUid = userInfoMapper.getUserInfoByUid(infoUid);
        if (userInfoByUid != null) {
            if (uid != infoUid)
            {
                Jedis jedis = new Jedis("127.0.0.1", 6379);
                Set<String> smembers = jedis.smembers(uid.toString());
                if (smembers.contains(userInfoByUid.getUid().toString()))
                    userInfoByUid.setIsFollowing(true);
                jedis.close();
            }
            Result result = Result.success();
            result.setData(userInfoByUid);
            return result;
        }
        else {
            return Result.failure(ResultCode.NONEXISTENT_UID);
        }
    }

    public Result modifySelfInfo(Integer uid, UserInfo userInfo) {
        if ("".equals(userInfo.getUsername())) {
            return Result.failure(ResultCode.NULL_USERNAME);
        }
        if ("".equals(userInfo.getRealName())) {
            return Result.failure(ResultCode.NULL_REALNAME);
        }
        if ("".equals(userInfo.getPhone())) {
            return Result.failure(ResultCode.NULL_PHONE);
        }
        if ("".equals(userInfo.getEmail())) {
            return Result.failure(ResultCode.NULL_EMAIL);
        }
        if ("".equals(userInfo.getBirthday())) {
            return Result.failure(ResultCode.NULL_BIRTHDAY);
        }
        if ("".equals(userInfo.getAbout())) {
            return Result.failure(ResultCode.NULL_ABOUT);
        }
        userInfo.setUid(uid);
        userInfoMapper.updateUserInfo(userInfo);
        return Result.success();


    }

    public Result setAvatarUrl(Integer uid, List<String> urlList) {


        UserInfo userInfo = userInfoMapper.getUserInfoByUid(uid);
        log.info(String.valueOf(userInfo));
        log.info(String.valueOf(urlList));
        if (userInfo == null) return Result.failure(ResultCode.NONEXISTENT_UID);

        if (urlList != null) {
            log.info(String.valueOf(urlList.get(0)));
            userInfo.setAvatarUrl(urlList.get(0));
            userInfoMapper.updateUserInfo(userInfo);
            return Result.success();
        } else return Result.failure(ResultCode.EMPTY_UPLOAD_FILE);

    }

    public Result getAllUserInfo(Page page, Integer uid) {

        PageHelper.startPage(page.getCurrentPage(),10);
        List<UserInfo> allUserInfo = userInfoMapper.getAllUserInfo(uid);
        if (allUserInfo != null) {
            Jedis jedis = new Jedis("127.0.0.1", 6379);
            Set<String> smembers = jedis.smembers(uid.toString());
            for (UserInfo  userInfo : allUserInfo)
            {
                if (smembers.contains(userInfo.getUid().toString()))
                    userInfo.setIsFollowing(true);
            }
            PageInfo<UserInfo> pageInfo = new PageInfo<UserInfo>(allUserInfo, 3);
            log.info(String.valueOf(pageInfo));
            Result result = Result.success();
            result.setData(pageInfo);
            return result;
        } else
            return Result.failure(ResultCode.NONEXISTENT_UID);

    }

    public Result addFollowing(Integer uid, Integer followingUid) {

        User user = userMapper.getUserByUid(uid);
        User followingUser = userMapper.getUserByUid(followingUid);
        if ((user == null) || (followingUser == null)) return Result.failure(ResultCode.NONEXISTENT_UID);

        Integer integer = followingMapper.saveFollowing(uid, followingUid);
        if (integer <= 0) return Result.failure(ResultCode.WRONG_ADDFOLLOWING);

        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.sadd(uid.toString(),followingUid.toString());
        UserInfo uuserInfo = userInfoMapper.getUserInfoByUid(uid);
        uuserInfo.setFollowings(uuserInfo.getFollowings() + 1);
        UserInfo fuserInfo = userInfoMapper.getUserInfoByUid(followingUid);
        fuserInfo.setFollowers(fuserInfo.getFollowers() + 1);

        jedis.close();
        userInfoMapper.updateUserInfo(uuserInfo);
        userInfoMapper.updateUserInfo(fuserInfo);

        return Result.success();
    }

    public Result deleteFollowing(Integer uid, Integer followingUid) {

        User user = userMapper.getUserByUid(uid);
        User followingUser = userMapper.getUserByUid(followingUid);
        if ((user == null) || (followingUser == null)) return Result.failure(ResultCode.NONEXISTENT_UID);

        Integer integer = followingMapper.deleteFollowing(uid, followingUid);
        if (integer <= 0) return Result.failure(ResultCode.WRONG_DELETEFOLLOWING);

        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.srem(uid.toString(),followingUid.toString());
        UserInfo uuserInfo = userInfoMapper.getUserInfoByUid(uid);
        uuserInfo.setFollowings(uuserInfo.getFollowings() - 1);
        UserInfo fuserInfo = userInfoMapper.getUserInfoByUid(followingUid);
        fuserInfo.setFollowers(fuserInfo.getFollowers() - 1);

        jedis.close();
        userInfoMapper.updateUserInfo(uuserInfo);
        userInfoMapper.updateUserInfo(fuserInfo);


        return  Result.success();

    }

    public Result getAllFollowingByUid(Page page, Integer uid) {

        User userByUid = userMapper.getUserByUid(uid);

        if (userByUid == null) return Result.failure(ResultCode.NONEXISTENT_UID);

        PageHelper.startPage(page.getCurrentPage(),10);
        List<UserInfo> allFollowingByUid = followingMapper.getAllFollowingByUid(uid);
        PageInfo<UserInfo> pageInfo = new PageInfo<UserInfo>(allFollowingByUid, 3);
        log.info(String.valueOf(pageInfo));
        Result result = Result.success();
        result.setData(pageInfo);

        return result;
    }

    public Result getAllFollowersByUid(Page page, Integer uid) {

        User userByUid = userMapper.getUserByUid(uid);
        if (userByUid == null) return Result.failure(ResultCode.NONEXISTENT_UID);

        PageHelper.startPage(page.getCurrentPage(),10);
        List<UserInfo> allFollowersByUid = followingMapper.getAllFollowersByUid(uid);

        PageInfo<UserInfo> pageInfo = new PageInfo<UserInfo>(allFollowersByUid, 3);
        log.info(String.valueOf(pageInfo));
        Result result = Result.success();
        result.setData(pageInfo);

        return result;
    }
}
