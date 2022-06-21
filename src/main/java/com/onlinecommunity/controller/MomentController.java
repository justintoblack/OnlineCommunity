package com.onlinecommunity.controller;


import com.onlinecommunity.pojo.Moment;
import com.onlinecommunity.pojo.Page;
import com.onlinecommunity.result.Result;
import com.onlinecommunity.result.ResultCode;
import com.onlinecommunity.service.MomentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@ResponseBody
@Slf4j
public class MomentController {

    @Autowired
    MomentService momentService;


    /**
     * @param moment 请求体中的uid, content封装到moment中 需要传入参数(发布者uid,微博内容content)
     * @param multiPartFiles 上传的图片 需要上传参数(pictures)
     * @return 结果
     */
    @PostMapping("/post_moment")
    public Result post(@Validated Moment moment, @RequestParam("pictures") MultipartFile[] multiPartFiles) {

        //保存该动态中的图片
        log.info("接收到了{}张图片", multiPartFiles.length);
        List<String> urlList = null;
        if (multiPartFiles.length > 0) {
            //检查所有图片是否满足限制
            log.info("checking uploaded pictures...");
            Result checkResult = momentService.checkPictures(multiPartFiles);
            if (!checkResult.getMsg().equals("success"))
                return checkResult;
            log.info("uploading pictures...");
            //上传图片,成功后得到访问图片的URL列表
            urlList = momentService.uploadPictures(multiPartFiles);
            log.info("successfully uploaded");
        }

        moment.setPictureCount(multiPartFiles.length);
        //保存该动态中的文字内容
        log.info("MomentController before:{}", moment.getMomentId());
        Result textResult = momentService.post(moment);
        log.info("MomentController after:{}", moment.getMomentId());

        if (!"success".equals(textResult.getMsg())) {
        //if (!textResult.getMsg().equals("success")) {
            log.info("text result not success");
            return textResult;
        }
        log.info("successfully save moment content text!");

        //将保存好的图片URL存至数据库
        if (urlList != null)
            momentService.savePicturesUrl(urlList, moment.getMomentId());
        return Result.success();
    }






    /**
     *
     * @param page 需要传入参数：上一页最后一条动态ID：lastId
     * @param uid   用户ID 需要传入参数 当前用户ID uid
     * @return  Result，用户自己的动态列表保存在Result里的data中
     */
    @GetMapping("/get_self_moment_list")
    public Result getSelfMomentList(Page page, @RequestParam("uid")Integer uid){
        System.out.println("Controller : page = " + page);
        System.out.println("uid = " + uid);
        return momentService.getSelfMomentList(page, uid);
    }


    /**
     *
     * @param page 需要传入参数：上一页最后一条动态ID：lastId
     * @param uid   用户ID 需要传入参数 当前用户ID uid
     * @return  Result，主页动态列表保存在Result里的data中
     */
    @GetMapping("/get_home_moment_list")
    public Result getHomeMomentList(Page page, @RequestParam("uid")Integer uid){
        System.out.println("Controller : page = " + page);
        System.out.println("uid = " + uid);
        return momentService.getHomeMomentList(page, uid);
    }

    /**
     * @param mid  要删除的moment id 需要传入参数 mid
     * @param duid 执行删除操作的用户id 需要传入当前用户ID uid
     * @return 删除结果
     */
    @PostMapping("/delete_moment")
    public Result deleteMoment(Integer mid, @RequestParam("uid") Integer duid) {
        return momentService.deleteMoment(mid, duid);
    }

    /**
     * @param lid  要删除的likeMoment id 需要传入参数 lid
     * @param duid 执行删除操作的用户id 需要传入当前用户ID uid
     * @return 删除结果
     */
    @PostMapping("/delete_like_moment")
    public Result deleteLikeMoment(Integer lid, @RequestParam("uid") Integer duid) {
        return momentService.deleteLikeMoment(lid, duid);
    }

    /**
     * @param cid  要删除的likeComment id 需要传入参数 cid
     * @return 删除结果
     */
    @PostMapping("/delete_like_comment")
    public Result deleteLikeComment(Integer cid) {
        return momentService.deleteLikeComment(cid);
    }

    /**
     * @param rid  要删除的repostMoment id 需要传入参数 rid
     * @param duid 执行删除操作的用户id 需要传入当前用户ID uid
     * @return 删除结果
     */
    @PostMapping("/delete_repost_moment")
    public Result deleteRepostMoment(Integer rid, @RequestParam("uid") Integer duid) {
        return momentService.deleteRepostMoment(rid, duid);
    }

    /**
     * @param sid  要删除的starMoment id 需要传入参数 sid
     * @param duid 执行删除操作的用户id 需要传入当前用户ID uid
     * @return 删除结果
     */
    @PostMapping("/delete_star_moment")
    public Result deleteStarMoment(Integer sid, @RequestParam("uid") Integer duid) {
        return momentService.deleteStarMoment(sid, duid);
    }

    /**
     * @param cid  要删除的commentMoment id 需要传入参数 cid
     * @param duid 执行删除操作的用户id 需要传入当前用户ID uid
     * @return 删除结果
     */
    @PostMapping("/delete_comment_moment")
    public Result deleteCommentMoment(Integer cid, @RequestParam("uid") Integer duid) {
        return momentService.deleteCommentMoment(cid, duid);
    }

    /**
     * @param mid  要点赞的moment id 需要传入参数 mid
     * @param luid 执行点赞操作的用户id 需要传入当前用户ID uid
     * @return 点赞结果
     */
    @GetMapping("/like_moment")
    public Result likeMoment(Integer mid, @RequestParam("uid") Integer luid) {
        if (mid == null) {
            return Result.failure(ResultCode.NULL_MID);
        }
        if (luid == null) {
            return Result.failure(ResultCode.NULL_UID);
        }
        return momentService.likeMoment(mid, luid);
    }

    /**
     * @param cid  要点赞的comment id 需要传入参数 cid
     * @return 点赞结果
     */
    @GetMapping("/like_comment")
    public Result likeComment(Integer cid) {
        if (cid == null) {
            return Result.failure(ResultCode.NULL_CID);
        }

        return momentService.likeComment(cid);
    }

    /**
     * @param mid 评论事件id 需要传入参数 mid
     * @param cuid  评论的人的id 需要传入当前用户ID uid
     * @param ccontent 评论内容 需要传入参数 ccontent
     * @return 结果
     */
    @PostMapping("/comment_moment")
    public Result comment(Integer mid, @RequestParam("uid") Integer cuid, String ccontent) {
        return momentService.comment(mid, cuid, ccontent);
    }

    /**
     * @param mid 转发事件id 需要传入参数 mid
     * @param ruid  转发的人的id 需要传入当前用户ID uid
     * @return 结果
     */
    @GetMapping("/repost_moment")
    public Result repost(Integer mid, @RequestParam("uid") Integer ruid) {
        if (mid == null) {
            return Result.failure(ResultCode.NULL_MID);
        }
        if (ruid == null) {
            return Result.failure(ResultCode.NULL_UID);
        }
        return momentService.repost(mid, ruid);

    }

    /**
     * @param mid 收藏事件id 需要传入参数 mid
     * @param suid  收藏的人的id 需要传入当前用户ID uid
     * @return 结果
     */
    @GetMapping("/star_moment")
    public Result star(Integer mid, @RequestParam("uid") Integer suid) {
        if (mid == null) {
            return Result.failure(ResultCode.NULL_MID);
        }
        if (suid == null) {
            return Result.failure(ResultCode.NULL_UID);
        }
        return momentService.star(mid, suid);

    }

    /**
     *
     * @param page 需要传入参数：请求页面页数(currentPage)
     * @param uid   用户ID 需要传入当前用户ID uid
     * @return  Result，用户喜爱的列表
     */
    @GetMapping("/get_like_list")
    public Result getLikeList(Page page, @RequestParam("uid")Integer uid){

        return momentService.getLikeList(page, uid);
    }

    /**
     *
     * @param page 需要传入参数：请求页面页数(currentPage)
     * @param mid   评论的微博ID 需要传入参数 mid
     * @return  Result，微博评论的列表
     */
    @GetMapping("/get_comment_list")
    public Result getCommentList(Page page, @RequestParam("mid")Integer mid){

        return momentService.getCommentList(page, mid);
    }

    /**
     *
     * @param page 需要传入参数：请求页面页数(currentPage)
     * @param uid   用户ID 需要传入当前用户ID uid
     * @return  Result，用户收藏的列表
     */
    @GetMapping("/get_star_list")
    public Result getStarList(Page page, @RequestParam("uid")Integer uid){

        return momentService.getStarList(page, uid);
    }

    /**
     *
     * @param page 需要传入参数：请求页面页数
     * @param uid   用户ID 需要传入当前用户ID uid
     * @return  Result，用户转发的列表
     */
    @GetMapping("/get_repost_list")
    public Result getRepostList(Page page, @RequestParam("uid")Integer uid){

        return momentService.getRepostList(page, uid);
    }

    /**
     * @param uid 当前用户id 需要传入当前用户ID uid
     * @param page 需要传入参数：请求页面页数(currentPage)
     * @param str 根据信息查询用户内容 需要传入参数 查询内容
     * @return 结果
     */
    @GetMapping("/search_userInfo")
    public Result searchUserInfo(@RequestParam("uid") Integer uid, Page page, String str){

        return momentService.searchUserInfo(uid, page, str);
    }

    /**
     * @param page 需要传入参数：请求页面页数(currentPage)
     * @param str 根据信息查询事件内容 需要传入参数 查询内容
     * @return 结果
     */
    @GetMapping("/search_moment")
    public Result searchMoment(@RequestParam("uid") Integer uid, Page page, String str){

        return momentService.searchMoment(uid, page, str);
    }

}
