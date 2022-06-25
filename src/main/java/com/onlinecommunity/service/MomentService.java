package com.onlinecommunity.service;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
import redis.clients.jedis.Jedis;

import javax.validation.constraints.*;
import java.sql.Timestamp;
import java.util.*;

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

    @Autowired
    FollowingMapper followingMapper;

    @Autowired
    UserInfoMapper userInfoMapper;

    @Autowired
    StarMapper starMapper;

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
                log.info("MoemntService::post():moment.getUid()={}", moment.getUid());
                UserInfo userInfo = userInfoMapper.getUserInfoByUid(moment.getUid());
                log.info("MoemntService::post():userinfo={}", userInfo);
                assert (userInfo != null);
                userInfo.setMomentCount(userInfo.getMomentCount() + 1);
                userInfoMapper.updateUserInfo(userInfo);
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
        if (pictureMapper.getPicUrlsByMomentId(momentId).size() == 0)
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

        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.auth(MyEnvBeanUtil.getProperty("spring.redis.password"));
        Set<String> smembersStar = jedis.smembers(uid.toString() + "star");
        Set<String> smembersLike = jedis.smembers(uid.toString() + "like");
        Set<String> smembersRepost = jedis.smembers(uid.toString() + "repost");
        for (Moment moment : momentList) {
            if (smembersStar.contains(moment.getMomentId().toString()))
                moment.setStar(true);
            if (smembersLike.contains(moment.getMomentId().toString()))
                moment.setLike(true);
            if (smembersRepost.contains(moment.getMomentId().toString()))
                moment.setRepost(true);
        }

        jedis.close();

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

        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.auth(MyEnvBeanUtil.getProperty("spring.redis.password"));
        Set<String> smembersStar = jedis.smembers(uid.toString() + "star");
        Set<String> smembersLike = jedis.smembers(uid.toString() + "like");
        Set<String> smembersRepost = jedis.smembers(uid.toString() + "repost");
        for (Moment moment : momentList) {
            if (smembersStar.contains(moment.getMomentId().toString()))
                moment.setStar(true);
            if (smembersLike.contains(moment.getMomentId().toString()))
                moment.setLike(true);
            if (smembersRepost.contains(moment.getMomentId().toString()))
                moment.setRepost(true);
        }

        jedis.close();
//        Result result = Result.success();
//        Map<String, Object> resultMap = getMomentResultMap(momentList);
//        result.setData(resultMap);
//        return result;

        List<ResultMoment> resultMomentList = getResultMomentList(momentList);
        PageInfo<ResultMoment> pageInfo = new PageInfo<>(resultMomentList, 3);
        Result result = Result.success();
        result.setData(pageInfo);
        return result;

    }

    /**
     * 对momentList中的每个Moment对象，查询得到该动态用户名、头像地址、图片地址等，包装成ResultMoment，得到ResultMomentList
     * @param momentList 数据库中的动态列表，还不包含用户名、头像地址、动态的图片地址
     * @return Map，返回给前端的map，可直接将该map添加到Result的data属性中
     */
    private Map<String, Object> getMomentResultMap(List<Moment> momentList) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("momentList", getResultMomentList(momentList));
        return resultMap;
    }

    /**
     * @param momentList 数据库中的动态列表，还不包含用户名、头像地址、动态的图片地址
     * @return ResultMoment列表，包含返回给前端的动态的所有信息
     */
    public List<ResultMoment> getResultMomentList(List<Moment> momentList) {
        List<ResultMoment> resultMomentList = new ArrayList<>();
        for (Moment moment : momentList) {
            ResultMoment resultMoment = new ResultMoment(moment);

            UserInfo userInfo = userInfoMapper.getUserInfoByUid(resultMoment.uid);
            resultMoment.username = userInfo.getUsername();
            resultMoment.avatarUrl = userInfo.getAvatarUrl();
            resultMoment.pictureUrlList = getPictureUrlList(resultMoment.momentId);

            resultMomentList.add(resultMoment);
        }
        return resultMomentList;
    }

    /**
     * @param momentId 动态ID
     * @return 这条动态所包含的图片的URL列表
     */
    private List<String> getPictureUrlList(Integer momentId){
        return pictureMapper.getPicUrlsByMomentId(momentId);
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
     * @param momentId 动态ID
     * @param likeUid  执行点赞操作的用户ID
     * @return Result
     */
    public Result likeMoment(Integer momentId, Integer likeUid) {
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

                    Jedis jedis = new Jedis("127.0.0.1", 6379);
                    jedis.auth(MyEnvBeanUtil.getProperty("spring.redis.password"));
                    jedis.sadd(likeUid.toString() + "like", momentId.toString());
                    jedis.close();

                    moment.setLikeCount(moment.getLikeCount() + 1);
                    momentMapper.updateMoment(moment);

                    UserInfo userInfo = userInfoMapper.getUserInfoByUid(moment.getUid());
                    userInfo.setLikeCount(userInfo.getLikeCount() + 1);
                    userInfoMapper.updateUserInfo(userInfo);
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

    public Result likeComment(Integer cid) {

        Comment oneCommentByCommentId = commentMapper.getOneCommentByCommentId(cid);

        if (oneCommentByCommentId == null) return Result.failure(ResultCode.NONEXISTENT_CID);

        oneCommentByCommentId.setLikeCount(oneCommentByCommentId.getLikeCount() + 1);
        commentMapper.updateComment(oneCommentByCommentId);

        return Result.success();
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

                moment.setCommentCount(moment.getCommentCount() + 1);
                momentMapper.updateMoment(moment);

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

                Jedis jedis = new Jedis("127.0.0.1", 6379);
                jedis.auth(MyEnvBeanUtil.getProperty("spring.redis.password"));
                jedis.sadd(repostUid.toString() + "repost", momentId.toString());
                jedis.close();

                moment.setRepostCount(moment.getRepostCount() + 1);
                momentMapper.updateMoment(moment);

                return Result.success();
            } else {
                return Result.failure(ResultCode.NONEXISTENT_UID);
            }

        } else {
            return Result.failure(ResultCode.NONEXISTENT_MID);
        }
    }

    /**
     * @param momentId 要收藏的动态ID
     * @param starUid  执行收藏操作的用户ID
     * @return Result
     */
    public Result star(Integer momentId, Integer starUid) {
        Moment moment = momentMapper.getOneMomentByMomentId(momentId);
        if (moment != null) {//被收藏的动态需要存在
            User user = userMapper.getUserByUid(starUid);
            if (user != null) {//执行收藏的用户需要存在
                Star star = starMapper.getOneStarByStarUidMomentId(starUid, momentId);
                if (star == null) {
                    star = new Star();
                    star.setMomentId(momentId);
                    star.setMomentUid(moment.getUid());
                    star.setStarUid(starUid);
                    //设置收藏时间为当前时间
                    star.setStarTime(new Timestamp(System.currentTimeMillis()));
                    starMapper.saveStar(star);

                    Jedis jedis = new Jedis("127.0.0.1", 6379);
                    jedis.auth(MyEnvBeanUtil.getProperty("spring.redis.password"));
                    jedis.sadd(starUid.toString() + "star", momentId.toString());
                    jedis.close();

                    UserInfo userInfo = userInfoMapper.getUserInfoByUid(moment.getUid());
                    userInfo.setStarCount(userInfo.getStarCount() + 1);
                    userInfoMapper.updateUserInfo(userInfo);
                    return Result.success();
                } else {
                    return Result.failure(ResultCode.REPEATED_STAR);
                }
            } else {
                return Result.failure(ResultCode.NONEXISTENT_UID);
            }

        } else {
            return Result.failure(ResultCode.NONEXISTENT_MID);
        }
    }

    public Result searchUserInfo(Integer uid, Page page, String str) {

        User userByUid = userMapper.getUserByUid(uid);

        if (userByUid == null) return Result.failure(ResultCode.NONEXISTENT_UID);

        PageHelper.startPage(page.getCurrentPage(), 10);
        List<UserInfo> userInfoBySearch = userInfoMapper.getUserInfoBySearch(uid, str);
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.auth(MyEnvBeanUtil.getProperty("spring.redis.password"));
        Set<String> smembers = jedis.smembers(uid.toString() + "follow");
        for (UserInfo userInfo : userInfoBySearch) {
            if (smembers.contains(userInfo.getUid().toString()))
                userInfo.setIsFollowing(true);
        }

        jedis.close();
        PageInfo<UserInfo> pageInfo = new PageInfo<UserInfo>(userInfoBySearch, 3);
        log.info(String.valueOf(pageInfo));
        Result result = Result.success();
        result.setData(pageInfo);

        return result;
    }

    public Result searchMoment(Integer uid, Page page, String str) {

        User userByUid = userMapper.getUserByUid(uid);

        if (userByUid == null) return Result.failure(ResultCode.NONEXISTENT_UID);

        PageHelper.startPage(page.getCurrentPage(), 10);
        List<Moment> momentBySearch = momentMapper.getMomentBySearch(str);

        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.auth(MyEnvBeanUtil.getProperty("spring.redis.password"));
        Set<String> smembersStar = jedis.smembers(uid.toString() + "star");
        Set<String> smembersLike = jedis.smembers(uid.toString() + "like");
        Set<String> smembersRepost = jedis.smembers(uid.toString() + "repost");
        for (Moment moment : momentBySearch) {
            if (smembersStar.contains(moment.getMomentId().toString()))
                moment.setStar(true);
            if (smembersLike.contains(moment.getMomentId().toString()))
                moment.setLike(true);
            if (smembersRepost.contains(moment.getMomentId().toString()))
                moment.setRepost(true);
        }

        jedis.close();
        PageInfo<Moment> pageInfo = new PageInfo<Moment>(momentBySearch, 3);
        log.info(String.valueOf(pageInfo));
        Result result = Result.success();
        result.setData(pageInfo);

        return result;
    }

    public Result getLikeList(Page page, Integer uid) {

        User userByUid = userMapper.getUserByUid(uid);

        if (userByUid == null) return Result.failure(ResultCode.NONEXISTENT_UID);

        PageHelper.startPage(page.getCurrentPage(), 10);
        List<Moment> allLikesByUid = likeMapper.getAllLikesByUid(uid);

        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.auth(MyEnvBeanUtil.getProperty("spring.redis.password"));
        Set<String> smembersStar = jedis.smembers(uid.toString() + "star");
        Set<String> smembersLike = jedis.smembers(uid.toString() + "like");
        Set<String> smembersRepost = jedis.smembers(uid.toString() + "repost");
        for (Moment moment : allLikesByUid) {
            if (smembersStar.contains(moment.getMomentId().toString()))
                moment.setStar(true);
            if (smembersLike.contains(moment.getMomentId().toString()))
                moment.setLike(true);
            if (smembersRepost.contains(moment.getMomentId().toString()))
                moment.setRepost(true);
        }

        jedis.close();
        List<ResultMoment> resultMomentList = getResultMomentList(allLikesByUid);
        PageInfo<ResultMoment> pageInfo = new PageInfo<>(resultMomentList, 3);
        Result result = Result.success();
        result.setData(pageInfo);
        return result;
    }

    public Result getStarList(Page page, Integer uid) {

        User userByUid = userMapper.getUserByUid(uid);

        if (userByUid == null) return Result.failure(ResultCode.NONEXISTENT_UID);

        PageHelper.startPage(page.getCurrentPage(), 10);
        List<Moment> allStarsByUid = starMapper.getAllStarsByUid(uid);

        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.auth(MyEnvBeanUtil.getProperty("spring.redis.password"));
        Set<String> smembersStar = jedis.smembers(uid.toString() + "star");
        Set<String> smembersLike = jedis.smembers(uid.toString() + "like");
        Set<String> smembersRepost = jedis.smembers(uid.toString() + "repost");
        for (Moment moment : allStarsByUid) {
            if (smembersStar.contains(moment.getMomentId().toString()))
                moment.setStar(true);
            if (smembersLike.contains(moment.getMomentId().toString()))
                moment.setLike(true);
            if (smembersRepost.contains(moment.getMomentId().toString()))
                moment.setRepost(true);
        }

        jedis.close();
        List<ResultMoment> resultMomentList = getResultMomentList(allStarsByUid);
        PageInfo<ResultMoment> pageInfo = new PageInfo<>(resultMomentList, 3);
        Result result = Result.success();
        result.setData(pageInfo);
        return result;
    }

    public Result getRepostList(Page page, Integer uid) {

        User userByUid = userMapper.getUserByUid(uid);

        if (userByUid == null) return Result.failure(ResultCode.NONEXISTENT_UID);

        PageHelper.startPage(page.getCurrentPage(), 10);
        List<Moment> allRepostsByUid = repostMapper.getAllRepostsByUid(uid);

        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.auth(MyEnvBeanUtil.getProperty("spring.redis.password"));
        Set<String> smembersStar = jedis.smembers(uid.toString() + "star");
        Set<String> smembersLike = jedis.smembers(uid.toString() + "like");
        Set<String> smembersRepost = jedis.smembers(uid.toString() + "repost");
        for (Moment moment : allRepostsByUid) {
            if (smembersStar.contains(moment.getMomentId().toString()))
                moment.setStar(true);
            if (smembersLike.contains(moment.getMomentId().toString()))
                moment.setLike(true);
            if (smembersRepost.contains(moment.getMomentId().toString()))
                moment.setRepost(true);
        }

        jedis.close();
        PageInfo<Moment> pageInfo = new PageInfo<>(allRepostsByUid, 3);
        Result result = Result.success();
        result.setData(pageInfo);
        return result;
    }

    public Result getCommentList(Page page, Integer mid) {

        Moment oneMomentByMomentId = momentMapper.getOneMomentByMomentId(mid);

        if (oneMomentByMomentId == null) return Result.failure(ResultCode.NONEXISTENT_MID);

        PageHelper.startPage(page.getCurrentPage(), 10);
        List<Comment> allCommentsByMid = commentMapper.getAllCommentsByMid(mid);
        PageInfo<Comment> pageInfo = new PageInfo<>(allCommentsByMid, 3);
        Result result = Result.success();
        result.setData(pageInfo);
        return result;
    }

    /**
     * @param momentId  要删除的动态ID
     * @param deleteUid 执行删除操作的用户ID
     * @return Result
     */
    public Result deleteMoment(Integer momentId, Integer deleteUid) {
        Moment moment = momentMapper.getOneMomentByMomentId(momentId);
        if (moment != null) {
            if (moment.getUid().equals(deleteUid)) {
                momentMapper.deleteMomentByMomentId(momentId);

                UserInfo userInfo = userInfoMapper.getUserInfoByUid(moment.getUid());
                userInfo.setMomentCount(userInfo.getMomentCount() - 1);
                userInfoMapper.updateUserInfo(userInfo);
                return Result.success();
            } else {
                return Result.failure(ResultCode.CANNOT_DELETE_OTHERS_MOMENT);
            }
        } else {
            return Result.failure(ResultCode.NONEXISTENT_MID);
        }

    }

    public Result deleteLikeMoment(Integer momentId, Integer deleteUid) {

        Like like = likeMapper.getOneLikeByLikeUidMomentId(deleteUid, momentId);
        System.out.println("like = " + like);
        System.out.println("momentId = " + momentId);
        System.out.println("deleteUid = " + deleteUid);
        if (like != null) {
            log.info("like not null");
            if (like.getLikeUid().equals(deleteUid)) {
                likeMapper.deleteLikeByLikeId(like.getLikeId());

                Moment moment = momentMapper.getOneMomentByMomentId(like.getMomentId());
                if (moment != null) {
                    moment.setLikeCount(moment.getLikeCount() - 1);
                    momentMapper.updateMoment(moment);
                } else return Result.failure(ResultCode.NULL_MID);

                Jedis jedis = new Jedis("127.0.0.1", 6379);
                jedis.auth(MyEnvBeanUtil.getProperty("spring.redis.password"));
                jedis.srem(deleteUid.toString() + "like", like.getLikeId().toString());

                UserInfo userInfo = userInfoMapper.getUserInfoByUid(like.getLikeUid());
                userInfo.setLikeCount(userInfo.getLikeCount() - 1);
                userInfoMapper.updateUserInfo(userInfo);
                return Result.success();
            } else {
                return Result.failure(ResultCode.CANNOT_DELETE_OTHERS_LIKEMOMENT);
            }
        } else {
            log.info("like null");
            return Result.failure(ResultCode.NONEXISTENT_LID);
        }

    }

    public Result deleteLikeComment(Integer cid) {
        Comment oneCommentByCommentId = commentMapper.getOneCommentByCommentId(cid);

        if (oneCommentByCommentId == null) return Result.failure(ResultCode.NONEXISTENT_CID);

        oneCommentByCommentId.setLikeCount(oneCommentByCommentId.getLikeCount() - 1);
        commentMapper.updateComment(oneCommentByCommentId);

        return Result.success();
    }

    public Result deleteRepostMoment(Integer mid, Integer deleteUid) {

        Repost repost = repostMapper.getOneRepostByRepostUidMomentId(deleteUid,mid);
        if (repost != null) {
            if (repost.getRepostUid().equals(deleteUid)) {

                repostMapper.deleteRepostByRepostId(repost.getRepostId());

                Moment moment = momentMapper.getOneMomentByMomentId(repost.getMomentId());
                if (moment != null) {
                    moment.setRepostCount(moment.getRepostCount() - 1);
                    momentMapper.updateMoment(moment);
                } else return Result.failure(ResultCode.NULL_MID);

                Jedis jedis = new Jedis("127.0.0.1", 6379);
                jedis.auth(MyEnvBeanUtil.getProperty("spring.redis.password"));
                jedis.srem(deleteUid.toString() + "repost", repost.getRepostId().toString());

                return Result.success();
            } else {
                return Result.failure(ResultCode.CANNOT_DELETE_OTHERS_REPOSTMOMENT);
            }
        } else {
            return Result.failure(ResultCode.NONEXISTENT_RID);
        }
    }

    public Result deleteStarMoment(Integer mid, Integer deleteUid) {

        Star star = starMapper.getOneStarByStarUidMomentId(deleteUid,mid);
        if (star != null) {
            if (star.getStarUid().equals(deleteUid)) {
                starMapper.deleteStarByStarId(star.getStarId());

                UserInfo userInfo = userInfoMapper.getUserInfoByUid(star.getStarUid());
                userInfo.setStarCount(userInfo.getStarCount() - 1);
                userInfoMapper.updateUserInfo(userInfo);

                Jedis jedis = new Jedis("127.0.0.1", 6379);
                jedis.auth(MyEnvBeanUtil.getProperty("spring.redis.password"));
                jedis.srem(deleteUid.toString() + "star", star.getStarId().toString());
                return Result.success();
            } else {
                return Result.failure(ResultCode.CANNOT_DELETE_OTHERS_STARMOMENT);
            }
        } else {
            return Result.failure(ResultCode.NONEXISTENT_SID);
        }
    }

    public Result deleteCommentMoment(Integer mid, Integer deleteUid) {

        Comment comment = commentMapper.getOneCommentByCommentUidMomentId(deleteUid,mid);
        if (comment != null) {
            if (comment.getCommentUid().equals(deleteUid)) {

                commentMapper.deleteCommentByCid(comment.getCommentId());

                Moment moment = momentMapper.getOneMomentByMomentId(comment.getMomentId());
                if (moment != null) {
                    moment.setCommentCount(moment.getCommentCount() - 1);
                    momentMapper.updateMoment(moment);
                } else return Result.failure(ResultCode.NULL_MID);

                return Result.success();
            } else {
                return Result.failure(ResultCode.CANNOT_DELETE_OTHERS_COMMENTMOMENT);
            }
        } else {
            return Result.failure(ResultCode.NONEXISTENT_CID);
        }
    }

    public class ResultMoment {
        public Integer momentId;
        public Integer uid;
        public String content;
        //设置从数据库取出时间的格式
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        public Timestamp momentTime;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        public Timestamp deletedAt;
        public Integer pictureCount = 0;
        public Integer likeCount = 0;
        public Integer commentCount = 0;
        public Integer repostCount = 0;
        public Boolean isStar;
        public Boolean isLike;
        public Boolean isRepost;
        public List<String> pictureUrlList;
        public String username;
        public String avatarUrl;

        public ResultMoment() {

        }

        public ResultMoment(Moment moment) {
            this.momentId = moment.getMomentId();
            this.uid = moment.getUid();
            this.content = moment.getContent();
            this.momentTime = moment.getMomentTime();
            this.deletedAt = moment.getDeletedAt();
            this.pictureCount = moment.getPictureCount();
            this.commentCount = moment.getCommentCount();
            this.repostCount = moment.getRepostCount();
            this.likeCount = moment.getLikeCount();
            this.isLike = moment.getLike();
            this.isStar = moment.getStar();
            this.isRepost = moment.getRepost();
        }

        @Override
        public String toString() {
            return "ResultMoment{" +
                    "momentId=" + momentId +
                    ", uid=" + uid +
                    ", content='" + content + '\'' +
                    ", momentTime=" + momentTime +
                    ", deletedAt=" + deletedAt +
                    ", pictureCount=" + pictureCount +
                    ", likeCount=" + likeCount +
                    ", commentCount=" + commentCount +
                    ", repostCount=" + repostCount +
                    ", isStar=" + isStar +
                    ", isLike=" + isLike +
                    ", isRepost=" + isRepost +
                    ", pictureUrlList=" + pictureUrlList +
                    ", username='" + username + '\'' +
                    ", avatarUrl='" + avatarUrl + '\'' +
                    '}';
        }
    }
}
