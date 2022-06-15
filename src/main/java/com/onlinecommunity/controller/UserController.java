package com.onlinecommunity.controller;

import com.onlinecommunity.pojo.Page;
import com.onlinecommunity.pojo.User;
import com.onlinecommunity.pojo.UserInfo;
import com.onlinecommunity.result.Result;
import com.onlinecommunity.result.ResultCode;
import com.onlinecommunity.service.MomentService;
import com.onlinecommunity.service.UserService;
import com.onlinecommunity.util.MyEnvBeanUtil;
import com.onlinecommunity.util.UploadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Slf4j
@Controller
@ResponseBody
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public Result register(@Validated User user) {
        return userService.register(user);
    }

    @PostMapping("/login")
    public Result login(@Validated User user) {
        return userService.login(user);


    }
    /**@param uid   当前登录用户ID
     * @param infoUid   查看信息用户ID
     * 获取用户基本信息
     */
    @GetMapping("/get_self_info")
    public Result getSelfInfo(@RequestParam("uid") Integer uid,@RequestParam("infoUid") Integer infoUid){
        return userService.getSelfInfo(uid, infoUid);
    }

    /**
     * 获取全部用户基本信息
     */
    @GetMapping("/get_all_info")
    public Result getAllUserInfo(Page page){

        return userService.getAllUserInfo(page);
    }

    /**
     * 修改用户基本信息
     */
    @PostMapping("/modify_self_info")
    public Result modifySelfInfo(@Validated UserInfo userInfo){

        return userService.modifySelfInfo(userInfo);
    }

    /**
     * 修改用户密码
     */
    @PostMapping("/modify_password")
    public Result setPassWord(@Validated User user){

        return userService.setPassWord(user);
    }

    /**
     * 修改用户头像
     */
    @PostMapping("/modify_avatarUrl")
    public Result setAvatarUrl(@RequestParam("uid") Integer uid, @RequestParam("pictures") MultipartFile[] multiPartFiles){


        List<String> urlList = null;

        log.info(multiPartFiles.toString());
        if (multiPartFiles.length != 0) {
            //检查所有图片是否满足限制
            log.info("checking uploaded pictures...");
            for (MultipartFile multipartFile : multiPartFiles) {
                //check the file
                if (multipartFile.isEmpty())
                    return Result.failure(ResultCode.EMPTY_UPLOAD_FILE);
                if (multipartFile.getSize() > Long.parseLong(MyEnvBeanUtil.getPictureMaxSize()))
                    return Result.failure(ResultCode.EXCEED_MAX_PIC_SIZE);
            }
            log.info("uploading pictures...");
            //上传图片,成功后得到访问图片的URL列表
            urlList = UploadUtil.uploadPictures(multiPartFiles);
            log.info("successfully uploaded");
        }

        //将保存好的头像存至数据库
        return userService.setAvatarUrl(uid, urlList);

    }

    /**
     *
     * @param uid   用户ID
     * @param followUserInfo  关注或取关的用户信息
     * @return  Result，结果
     */
    @PostMapping("/follow")
    public Result addFollowing(@RequestParam("uid") Integer uid, @RequestParam("followingUserInfo") UserInfo followUserInfo){

        if (followUserInfo.getIsFollowing() == false)

            return userService.addFollowing(uid, followUserInfo.getUid());
        else
            return userService.deleteFollowing(uid, followUserInfo.getUid());
    }


    /**
     *
     * @param uid   用户ID
     * @return  Result，结果
     */
    @GetMapping("/get_followings_list")
    public Result getAllFollowingByUid(Page page, @RequestParam("uid") Integer uid){

        return userService.getAllFollowingByUid(page, uid);
    }

    /**
     *
     * @param uid   用户ID
     * @return  Result，结果
     */
    @GetMapping("/get_followers_list")
    public Result getAllFollowersByUid(Page page, @RequestParam("uid") Integer uid){

        return userService.getAllFollowersByUid(page, uid);
    }

}
