package com.onlinecommunity.service;

import com.onlinecommunity.mapper.*;
import com.onlinecommunity.pojo.*;
import com.onlinecommunity.result.Result;
import com.onlinecommunity.result.ResultCode;
import com.onlinecommunity.util.MyEnvBeanUtil;
import com.onlinecommunity.util.UploadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class MomentService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    MomentMapper momentMapper;

    @Autowired
    LikeMapper likeMapper;

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    RepostMapper repostMapper;

    @Autowired
    PictureMapper pictureMapper;

    /**
     * @param multiPartFiles 待检查图片
     * @return 如果检查没问题返回Result.success()，否则返回对应的错误(如：ResultCode.EXCEED_MAX_PIC_SIZE)
     */
    public Result checkPictures(MultipartFile[] multiPartFiles) {
        for (MultipartFile multipartFile : multiPartFiles) {
            //check the file
            if (multipartFile.isEmpty())
                return Result.failure(ResultCode.EMPTY_UPLOAD_FILE);
            if (multipartFile.getSize() > Long.parseLong(MyEnvBeanUtil.getPictureMaxSize()))
                return Result.failure(ResultCode.EXCEED_MAX_PIC_SIZE);
        }
        return Result.success();
    }


    /**
     * @param multipartFiles 要上传至服务器的图片，已经检验过没有问题(如：超出大小限制)
     * @return 这些图片成功保存后对应的URL列表
     */
    public List<String> uploadPictures(MultipartFile[] multipartFiles) {

        return UploadUtil.uploadPictures(multipartFiles);

    }


    /**
     * @param moment 发布的动态
     * @return Result
     */
    public Result post(Moment moment) {
        Integer uid = moment.getUid();
        User user = userMapper.getUserByUid(uid);
        if (user != null) {
            if (moment.getMomentId() == null) {
                //设置发布动态的时间为当前时间
                moment.setMomentTime(new Timestamp(System.currentTimeMillis()));
                log.info("MomentService before saving moment, mid:{}", moment.getMomentId());
                System.out.println("momentMapper.saveMoment(moment) = " + momentMapper.saveMoment(moment));
                log.info("MomentService after saving moment, mid:{}", moment.getMomentId());
                return Result.success();
            } else {
                return Result.failure(ResultCode.EXIST_MID);
            }
        } else {
            return Result.failure(ResultCode.NONEXISTENT_UID);
        }
    }

    /**
     * @param urlList  图片URL列表
     * @param momentId 这些图片对应的动态ID
     * @return Result.success()
     */
    public Result savePicturesUrl(List<String> urlList, Integer momentId) {
        if (momentMapper.getOneMomentByMomentId(momentId) == null)
            pictureMapper.insertPicturesUrl(urlList, momentId);
        else
            pictureMapper.updatePicturesUrl(urlList, momentId);
        return Result.success();
    }


    /**
     * @param page 分页对应对象，属性包含上一页最后一条动态的ID：lastId，以及页面大小pageSize
     * @param uid  用户ID
     * @return Result, 获得的动态列表在Result里的data中
     */
    public Result getSelfMomentList(Page page, Integer uid) {
        List<Moment> momentList = momentMapper.getActiveSelfMomentsByPage(page, uid);
        Result result = Result.success();
        Map<String, Object> resultMap = getMomentResultMap(momentList);
        result.setData(resultMap);
        return result;
    }


    /**
     * @param page 分页对应对象，属性包含上一页最后一条动态的ID：lastId，以及页面大小pageSize
     * @param uid  用户ID
     * @return 主页动态列表，以及用户名列表，现在是分开的，后续可以考虑改进
     */
    public Result getHomeMomentList(Page page, Integer uid) {
        List<Moment> momentList = momentMapper.getActiveHomeMomentsByPage(page, uid);
        Result result = Result.success();
        Map<String, Object> resultMap = getMomentResultMap(momentList);
        result.setData(resultMap);
        return result;
    }

    private Map<String, Object> getMomentResultMap(List<Moment> momentList) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("momentList", momentList);
        List<String> usernameList = getUsernameList(momentList);
        resultMap.put("usernameList", usernameList);
        return resultMap;
    }

    private List<String> getUsernameList(List<Moment> momentList) {
        List<String> usernameList = new LinkedList<>();
        String username;
        for (Moment moment : momentList) {
            username = userMapper.getUsernameByUid(moment.getUid());
            usernameList.add(username);
        }
        return usernameList;
    }

    /**
     * @param momentId  要删除的动态ID
     * @param deleteUid 执行删除操作的用户ID
     * @return Result
     */
    public Result delete(Integer momentId, Integer deleteUid) {
        Moment moment = momentMapper.getOneMomentByMomentId(momentId);
        if (moment != null) {
            if (moment.getUid().equals(deleteUid)) {
                momentMapper.deleteMomentByMomentId(momentId);
                return Result.success();
            } else {
                return Result.failure(ResultCode.CANNOT_DELETE_OTHERS_MOMENT);
            }
        } else {
            return Result.failure(ResultCode.NONEXISTENT_MID);
        }

    }


    /**
     * @param momentId 动态ID
     * @param likeUid  执行点赞操作的用户ID
     * @return Result
     */
    public Result like(Integer momentId, Integer likeUid) {
        Moment moment = momentMapper.getOneMomentByMomentId(momentId);
        if (moment != null) {//要点赞的动态需要存在
            User user = userMapper.getUserByUid(likeUid);
            if (user != null) {//执行点赞的用户需要存在
                Like like = likeMapper.getOneLikeByLikeUidMomentId(likeUid, momentId);
                System.out.println("likeMapper.getOneLikeByLikeUidMomentId(likeUid, momentId):" + like);
                if (like == null) {//该用户没有点赞过该动态
                    like = new Like();
                    like.setLikeUid(likeUid);
                    like.setMomentId(moment.getMomentId());
                    like.setMomentUid(moment.getUid());
                    //设置点赞的时间为当前时间
                    like.setLikeTime(new Timestamp(System.currentTimeMillis()));
                    log.info(String.valueOf(like));
                    log.info("save like..");
                    likeMapper.saveLike(like);
                    log.info("successfully saved.");
                    return Result.success();
                } else {
                    log.info("Repeated like!");
                    return Result.failure(ResultCode.REPEATED_LIKE);
                }
            } else {
                log.info("Non existent uid!");
                return Result.failure(ResultCode.NONEXISTENT_UID);
            }
        } else {
            return Result.failure(ResultCode.NONEXISTENT_MID);
        }
    }


    /**
     * @param momentId   评论的动态ID
     * @param commentUid 执行评论操作的用户ID
     * @param content    评论内容
     * @return Result
     */
    public Result comment(Integer momentId, Integer commentUid, String content) {
        Moment moment = momentMapper.getOneMomentByMomentId(momentId);
        if (moment != null) {//要评论的动态需要存在
            User user = userMapper.getUserByUid(commentUid);
            if (user != null) {//执行评论的用户需要存在
                Comment comment = new Comment();
                comment.setMomentId(momentId);
                comment.setContent(content);
                comment.setCommentUid(commentUid);
                comment.setMomentUid(moment.getUid());
                //设置评论时间为当前时间
                comment.setCommentTime(new Timestamp(System.currentTimeMillis()));
                commentMapper.comment(comment);
                return Result.success();
            } else {
                return Result.failure(ResultCode.NONEXISTENT_UID);
            }
        } else {
            return Result.failure(ResultCode.NONEXISTENT_MID);
        }
    }


    /**
     * @param momentId  要转发的动态ID
     * @param repostUid 执行转发操作的用户ID
     * @return Result
     */
    public Result repost(Integer momentId, Integer repostUid) {
        Moment moment = momentMapper.getOneMomentByMomentId(momentId);
        if (moment != null) {//被转发的动态需要存在
            User user = userMapper.getUserByUid(repostUid);
            if (user != null) {//执行转发的用户需要存在
                Repost repost = new Repost();
                repost.setMomentId(momentId);
                repost.setMomentUid(moment.getUid());
                repost.setRepostUid(repostUid);
                //设置转发时间为当前时间
                repost.setRepostTime(new Timestamp(System.currentTimeMillis()));
                repostMapper.saveRepost(repost);
                return Result.success();
            } else {
                return Result.failure(ResultCode.NONEXISTENT_UID);
            }

        } else {
            return Result.failure(ResultCode.NONEXISTENT_MID);
        }
    }
}
