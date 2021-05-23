package nuc.rwenjie.modules.sys.service;

import nuc.rwenjie.common.utils.ServiceResult;
import nuc.rwenjie.modules.sys.entity.CommentEntity;
import nuc.rwenjie.modules.sys.entity.UserEntity;

import java.util.Map;

public interface CommentService {
    /**
     * 查询当前文章的首条评论
     *
     * @param page      当前页
     * @param pageSize  页面大小
     * @param articleId 文章id
     * @return
     */
    ServiceResult<Map> selCommentReviewer(String articleId, Integer page, Integer pageSize);

    /**
     * 插入评论
     *
     * @param comment 评论信息
     * @param userEntity   用户身份
     * @return
     */
    ServiceResult<Boolean> insComment(CommentEntity comment, UserEntity userEntity);
}
