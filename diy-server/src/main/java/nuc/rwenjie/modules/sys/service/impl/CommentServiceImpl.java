package nuc.rwenjie.modules.sys.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import nuc.rwenjie.common.utils.*;
import nuc.rwenjie.modules.sys.entity.CommentEntity;
import nuc.rwenjie.modules.sys.entity.UserEntity;
import nuc.rwenjie.modules.sys.mapper.CommentMapper;
import nuc.rwenjie.modules.sys.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private AESUtil aesUtil;

    /**
     * 查询当前文章的首条评论
     *
     * @param articleId
     * @param page
     * @param pageSize
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public ServiceResult<Map> selCommentReviewer(String articleId, Integer page, Integer pageSize) {
        // 判断文章id是否存在
        if (StringUtils.isEmpty(articleId)) {
            return ServiceResultHelper.genResultWithParamFailed();
        }
        Page<Object> pageData = PageHelper.startPage(page, pageSize);
        // 查询父评论信息
        LinkedList<CommentEntity> comments = commentMapper.selCommentReviewer(articleId);

        System.out.println("comments"+comments);
        Iterator<CommentEntity> iterator = comments.iterator();
        // 查找子评论信息
        Map<String, Object> replyMap = new HashMap<>();
        while (iterator.hasNext()) {
            CommentEntity next = iterator.next();
            // 父评论名称解密
            //next.getReviewerUser().setUsername(aesUtil.Decrypt(next.getReviewerUser().getUsername()));
            LinkedList<CommentEntity> commentsReply = new LinkedList<>();
            selCommentReply(articleId, next.getId(), commentsReply);
            // 查询当前评论下的全部子评论
            replyMap.put(next.getId(), commentsReply);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("reviewer", PagedUtilHelper.genResultPagedData(comments, pageData));
        // 保存评论内容
        map.put("reply", replyMap);
        return ServiceResultHelper.genResultWithDataBaseSuccess(map);
    }
    /**
     * 查询子评论功能
     *
     * @param articleId 文章id
     * @param parentId  父评论id
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    public void selCommentReply(String articleId, String parentId, LinkedList<CommentEntity> commentsReply) {
        // 文章id不存在 查找网站子评论 否则查找对应文章子评论
        LinkedList<CommentEntity> comments = commentMapper.selCommentReply(articleId,parentId);
        Iterator<CommentEntity> iterator = comments.iterator();
        while (iterator.hasNext()) {
            // 获取评论信息
            CommentEntity next = iterator.next();
            // 回复人解密
            //next.getReplyUser().setUsername(aesUtil.Decrypt(next.getReplyUser().getUsername()));
            // 评论人解密
            //next.getReviewerUser().setUsername(aesUtil.Decrypt(next.getReviewerUser().getUsername()));
            // 将当前评论信息插入到列表中
            commentsReply.add(next);
            // 递归遍历
            selCommentReply(articleId, next.getId(), commentsReply);
        }
    }




    /**
     * 插入评论
     *
     * @param comment 评论信息
     * @param userEntity   用户身份
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public ServiceResult<Boolean> insComment(CommentEntity comment, UserEntity userEntity) {
        ServiceResult<String> serviceResult = getUserId(userEntity);
        String userId = serviceResult.isSuccess() ? serviceResult.getData() : null;
        // 从token中提取用户id信息失败
        if (StringUtils.isEmpty(userId)) {
            return ServiceResultHelper.genResultWithParamFailed();
        }
        // 设置评论人id
        comment.setId(UUID.randomUUID().toString());
        UserEntity user = new UserEntity();
        user.setUserId(userId);
        comment.setReviewerUser(user);
        comment.setCommentTime(TypeConvert.DateToString(new Date()));
        if (commentMapper.insComment(comment) > 0) {
            /*if(!StringUtils.isEmpty(comment.getArticleId())){
                asyncService.articleBlogStatus(3,comment.getArticleId()); // 统计文章评论数（异步）
            }*/
            return ServiceResultHelper.genResultWithDataBaseSuccess();
        }
        return ServiceResultHelper.genResultWithDataBaseFailed();
    }

    /**
     * 从token中提取用户id信息
     *
     * @return
     */
    private ServiceResult<String> getUserId(UserEntity userEntity) {
        // 从请求体中取出用户信息
        // 获取用户token

        if (userEntity==null) {
            return ServiceResultHelper.genResultWithParamFailed();
        }

        try {
            String userId = userEntity.getUserId();
            return ServiceResultHelper.genResultWithTokenSuccess(userId);
        } catch (Exception e) {
            return ServiceResultHelper.genResultWithTokenFailed();
        }
    }
}
