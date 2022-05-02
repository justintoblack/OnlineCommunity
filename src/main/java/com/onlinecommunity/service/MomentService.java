package com.onlinecommunity.service;

import com.onlinecommunity.mapper.*;
import com.onlinecommunity.pojo.*;
import com.onlinecommunity.result.Result;
import com.onlinecommunity.result.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

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

    public Result post(Moment moment) {
        Integer uid = moment.getUid();
        User user = userMapper.findUserByUid(uid);
        if (user != null) {
            if (moment.getMid() == null) {
                //设置发布动态的时间为当前时间
                moment.setMtime(new Timestamp(System.currentTimeMillis()));
                momentMapper.saveMoment(moment);
                return Result.success();
            } else {
                return Result.failure(ResultCode.EXIST_MID);
            }
        } else {
            return Result.failure(ResultCode.NONEXISTENT_UID);
        }
    }

    public Result delete(Integer mid, Integer duid) {
        Moment moment = momentMapper.findOneMomentByMid(mid);
        if (moment != null) {
            if (moment.getUid().equals(duid)) {
                momentMapper.deleteMomentByMid(mid);
                return Result.success();
            } else {
                return Result.failure(ResultCode.CANNOT_DELETE_OTHERS_MOMENT);
            }
        } else {
            return Result.failure(ResultCode.NONEXISTENT_MID);
        }

    }

    public Result like(Integer mid, Integer luid) {
        Moment moment = momentMapper.findOneMomentByMid(mid);
        if (moment != null) {//要点赞的动态需要存在
            User user = userMapper.findUserByUid(luid);
            if (user != null) {//执行点赞的用户需要存在
                Like like = likeMapper.getOneLikeByLuidMid(luid, mid);
                if (like == null) {//该用户没有点赞过该动态
                    like = new Like();
                    like.setLuid(luid);
                    like.setMid(moment.getMid());
                    like.setMuid(moment.getUid());
                    //设置点赞的时间为当前时间
                    like.setLtime(new Timestamp(System.currentTimeMillis()));
                    log.info(String.valueOf(like));
                    likeMapper.likeMoment(like);
                    return Result.success();
                } else {
                    return Result.failure(ResultCode.REPEATED_LIKE);
                }
            } else {
                return Result.failure(ResultCode.NONEXISTENT_UID);
            }
        } else {
            return Result.failure(ResultCode.NONEXISTENT_MID);
        }
    }

    public Result comment(Integer mid, Integer cuid, String ccontent) {
        Moment moment = momentMapper.findOneMomentByMid(mid);
        if (moment != null) {//要评论的动态需要存在
            User user = userMapper.findUserByUid(cuid);
            if (user != null) {//执行评论的用户需要存在
                Comment comment = new Comment();
                comment.setMid(mid);
                comment.setCcontent(ccontent);
                comment.setCuid(cuid);
                comment.setMuid(moment.getUid());
                //设置评论时间为当前时间
                comment.setCtime(new Timestamp(System.currentTimeMillis()));
                commentMapper.comment(comment);
                return Result.success();
            } else {
                return Result.failure(ResultCode.NONEXISTENT_UID);
            }
        } else {
            return Result.failure(ResultCode.NONEXISTENT_MID);
        }
    }

    public Result repost(Integer mid, Integer ruid){
        Moment moment = momentMapper.findOneMomentByMid(mid);
        if (moment != null){//被转发的动态需要存在
            User user = userMapper.findUserByUid(ruid);
            if (user!=null){//执行转发的用户需要存在
                Repost repost = new Repost();
                repost.setMid(mid);
                repost.setMuid(moment.getUid());
                repost.setRuid(ruid);
                repost.setMtime(moment.getMtime());
                //设置转发时间为当前时间
                repost.setRtime(new Timestamp(System.currentTimeMillis()));
                repostMapper.repost(repost);
                return Result.success();
            }else{
                return Result.failure(ResultCode.NONEXISTENT_UID);
            }

        }else{
            return Result.failure(ResultCode.NONEXISTENT_MID);
        }
    }
}
