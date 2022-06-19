package com.onlinecommunity.controller;

import com.onlinecommunity.pojo.Page;
import com.onlinecommunity.pojo.User;
import com.onlinecommunity.pojo.UserInfo;
import com.onlinecommunity.result.Result;
import com.onlinecommunity.result.ResultCode;
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

    /**
     * 注册
     * @param user 需要上传参数 (username用户名,password密码,phone电话,email邮箱)
     */
    @PostMapping("/register")
    public Result register(@Validated User user) {
        return userService.register(user);
    }

    /**
     * 登录
     * @param user 需要上传参数 (username用户名,password密码)
     */
    @PostMapping("/login")
    public Result login(@Validated User user) {
        return userService.login(user);


    }
    /**@param uid   当前登录用户ID 需要传入当前用户ID uid
     * @param infoUid   查看信息用户ID 需要传入查看的用户ID infoUid
     * 获取用户基本信息
     */
    @GetMapping("/get_self_info")
    public Result getSelfInfo(@RequestParam("uid") Integer uid,@RequestParam("infoUid") Integer infoUid){
        return userService.getSelfInfo(uid, infoUid);
    }

    /**
     * 获取全部用户基本信息
     * @param page 需要传入参数：请求页面页数(currentPage)
     * @param uid 当前用户id 需要传入当前用户ID uid
     */
    @GetMapping("/get_all_info")
    public Result getAllUserInfo(Page page,@RequestParam("uid") Integer uid){

        return userService.getAllUserInfo(page, uid);
    }

    /**
     * 修改用户基本信息
     * @param uid 当前用户id 需要传入当前用户ID uid
     * @param userInfo 需要上传参数 (username用户名,realName真实名字,phone电话,email邮箱,birthday生日,about简介)
     */
    @PostMapping("/modify_self_info")
    public Result modifySelfInfo(@RequestParam("uid") Integer uid,@Validated UserInfo userInfo){

        return userService.modifySelfInfo(uid, userInfo);
    }

    /**
     * 修改用户密码
     * @param uid 当前用户id 需要传入当前用户ID uid
     * @param password 更改的密码 需要传入当前用户密码 password
     */
    @PostMapping("/modify_password")
    public Result setPassWord(@RequestParam("uid") Integer uid, @RequestParam("password") String password){

        return userService.setPassWord(uid,password);
    }

    /**
     * 修改用户头像
     * @param uid 当前用户id 需要传入当前用户ID uid
     * @param multiPartFiles 上传的图片 需要上传参数(pictures)
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
     * @param uid   当前用户ID 需要传入当前用户ID uid
     * @param followUserInfo  关注或取关的用户信息 需要传入参数(取关或关注的用户uid,isFollowing)
     * @return  Result，结果
     */
    @PostMapping("/follow")
    public Result addFollowing(@RequestParam("uid") Integer uid, @RequestBody UserInfo followUserInfo){

        if (followUserInfo.getIsFollowing() == false)

            return userService.addFollowing(uid, followUserInfo.getUid());
        else
            return userService.deleteFollowing(uid, followUserInfo.getUid());
    }


    /**
     * @param page 需要传入参数：请求页面页数(currentPage)
     * @param uid   用户ID 需要传入当前用户ID uid
     * @return  Result，当前用户的关注
     */
    @GetMapping("/get_followings_list")
    public Result getAllFollowingByUid(Page page, @RequestParam("uid") Integer uid){

        return userService.getAllFollowingByUid(page, uid);
    }

    /**
     * @param page 需要传入参数：请求页面页数(currentPage)
     * @param uid   用户ID 需要传入当前用户ID uid
     * @return  Result，当前用户的粉丝
     */
    @GetMapping("/get_followers_list")
    public Result getAllFollowersByUid(Page page, @RequestParam("uid") Integer uid){

        return userService.getAllFollowersByUid(page, uid);
    }

}
