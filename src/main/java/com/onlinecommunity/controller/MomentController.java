package com.onlinecommunity.controller;


import com.onlinecommunity.pojo.Moment;
import com.onlinecommunity.result.Result;
import com.onlinecommunity.result.ResultCode;
import com.onlinecommunity.service.MomentService;
import com.onlinecommunity.util.UploadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@ResponseBody
@Slf4j
public class MomentController {

    @Autowired
    MomentService momentService;

    /**
     * @param moment 请求体中的uid, mid, mcontent封装到moment中
     * @return 结果
     */
    @PostMapping("/post_moment")
    public Result post(@Validated Moment moment, @RequestParam("pictures") MultipartFile[] multiPartFiles) {

        //保存该动态中的图片
        log.info("接收到了{}张图片", multiPartFiles.length);
        List<String> urlList = null;
        if (multiPartFiles.length > 0) {

            //检查所有图片是否满足限制
            Result checkResult = momentService.checkPictures(multiPartFiles);
            if (!checkResult.getMsg().equals("success"))
                return checkResult;

            //上传图片
            urlList = momentService.uploadPictures(multiPartFiles);
        }
        //保存该动态中的文字内容
        log.info("MomentController before:{}", moment.getMid());
        Result textResult = momentService.post(moment);
        log.info("MomentController after:{}", moment.getMid());

        if (!textResult.getMsg().equals("success")) {
            return textResult;
        }
        log.info("successfully save moment content text!");

        //将保存好的图片URL存至数据库
        if (urlList != null)
            momentService.savePicturesUrl(urlList, moment.getMid());
        return Result.success();
    }

    /**
     * @param mid  要删除的moment id
     * @param duid 执行删除操作的用户id
     * @return 删除结果
     */
    @PostMapping("/delete_moment")
    public Result delete(Integer mid, Integer duid) {
        return momentService.delete(mid, duid);
    }

    /**
     * @param mid  要点赞的moment id
     * @param luid 执行点赞操作的用户id
     * @return 点赞结果
     */
    @GetMapping("/like_moment")
    public Result like(Integer mid, Integer luid) {
        if (mid == null) {
            return Result.failure(ResultCode.NULL_UID);
        }
        if (luid == null) {
            return Result.failure(ResultCode.NULL_MID);
        }
        return momentService.like(mid, luid);
    }

    @PostMapping("/comment_moment")
    public Result comment(Integer mid, Integer cuid, String ccontent) {
        return momentService.comment(mid, cuid, ccontent);
    }

    @GetMapping("/repost_moment")
    public Result repost(Integer mid, Integer ruid) {
        return momentService.repost(mid, ruid);

    }

}
