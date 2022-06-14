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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;

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
                return Result.success(token);
            } else {
                return Result.failure(ResultCode.WRONG_PASSWORD);
            }
        } else {
            return Result.failure(ResultCode.NONEXISTENT_USERNAME);
        }
    }

    public Result setPassWord(User user){
        if ("".equals(user.getPassword())) {
            return Result.failure(ResultCode.NULL_USERNAME);
        }
        userMapper.setPassword(user);
        return Result.success();
    }


    public Result getSelfInfo(Integer uid) {

        UserInfo userInfoByUid = userInfoMapper.getUserInfoByUid(uid);
        if (userInfoByUid != null) {
            Result result = Result.success();
            result.setData(userInfoByUid);
            return result;
        }
        else {
            return Result.failure(ResultCode.NONEXISTENT_UID);
        }
    }

    public Result modifySelfInfo(UserInfo userInfo) {
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
        userInfoMapper.updateUserInfo(userInfo);
        return Result.success();


    }

    public Result setAvatarUrl(Integer uid, List<String> urlList) {

        Result result = getSelfInfo(uid);
        UserInfo userInfo = (UserInfo) result.getData();
        log.info(String.valueOf(userInfo));
        log.info(String.valueOf(urlList));
        if (urlList != null) {
            log.info(String.valueOf(urlList.get(0)));
            userInfo.setAvatarUrl(urlList.get(0));
            userInfoMapper.updateUserInfo(userInfo);
        }
        return Result.success();
    }

    public Result getAllUserInfo(Page page) {

        PageHelper.startPage(page.getCurrentPage(),10);
        List<UserInfo> allUserInfo = userInfoMapper.getAllUserInfo();
        PageInfo<UserInfo> pageInfo = new PageInfo<UserInfo>(allUserInfo,3);
        log.info(String.valueOf(pageInfo));
        Result result = Result.success();
        result.setData(pageInfo);
        return result;

    }

    public Result addFollowing(Integer uid, Integer followingUid) {

        Integer integer = followingMapper.saveFollowing(uid, followingUid);
        if (integer <= 0) return Result.failure(ResultCode.WRONG_ADDFOLLOWING);


        Result uresult = getSelfInfo(uid);
        UserInfo uuserInfo = (UserInfo)uresult.getData();
        uuserInfo.setFollowing(uuserInfo.getFollowing() + 1);
        Result fresult = getSelfInfo(followingUid);
        UserInfo fuserInfo = (UserInfo) fresult.getData();
        fuserInfo.setFollowers(fuserInfo.getFollowers() + 1);

        userInfoMapper.updateUserInfo(uuserInfo);
        userInfoMapper.updateUserInfo(fuserInfo);

        return Result.success();
    }

    public Result deleteFollowing(Integer uid, Integer followingUid) {

        Integer integer = followingMapper.deleteFollowing(uid, followingUid);
        if (integer <= 0) return Result.failure(ResultCode.WRONG_DELETEFOLLOWING);

        Result uresult = getSelfInfo(uid);
        UserInfo uuserInfo = (UserInfo)uresult.getData();
        uuserInfo.setFollowing(uuserInfo.getFollowing() - 1);
        Result fresult = getSelfInfo(followingUid);
        UserInfo fuserInfo = (UserInfo) fresult.getData();
        fuserInfo.setFollowers(fuserInfo.getFollowers() - 1);

        userInfoMapper.updateUserInfo(uuserInfo);
        userInfoMapper.updateUserInfo(fuserInfo);
        return  Result.success();

    }

    public Result getAllFollowingByUid(Page page, Integer uid) {

        PageHelper.startPage(page.getCurrentPage(),10);
        List<UserInfo> allFollowingByUid = followingMapper.getAllFollowingByUid(uid);
        PageInfo<UserInfo> pageInfo = new PageInfo<UserInfo>(allFollowingByUid,3);
        log.info(String.valueOf(pageInfo));
        Result result = Result.success();
        result.setData(pageInfo);

        return result;
    }

    public Result getAllFollowersByUid(Page page, Integer uid) {

        PageHelper.startPage(page.getCurrentPage(),10);
        List<UserInfo> allFollowersByUid = followingMapper.getAllFollowersByUid(uid);
        PageInfo<UserInfo> pageInfo = new PageInfo<UserInfo>(allFollowersByUid,3);
        log.info(String.valueOf(pageInfo));
        Result result = Result.success();
        result.setData(pageInfo);

        return result;
    }
}
