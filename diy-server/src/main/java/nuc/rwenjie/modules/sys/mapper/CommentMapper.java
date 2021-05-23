package nuc.rwenjie.modules.sys.mapper;

import nuc.rwenjie.modules.sys.entity.CommentEntity;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;

@Repository
public interface CommentMapper {

    /**
     * 查询当前文章的首条评论
     *
     * @param articleId 文章id
     * @return
     */
    LinkedList<CommentEntity> selCommentReviewer(String articleId);


    /**
     * 查询当前评论下的子评论
     *
     * @param articleId 文章id
     * @param parentId  当前评论的id
     * @return
     */
    LinkedList<CommentEntity> selCommentReply(String articleId, String parentId);

    /**
     * 插入评论
     *
     * @param comment 评论信息
     * @return
     */
    int insComment(CommentEntity comment);
}
