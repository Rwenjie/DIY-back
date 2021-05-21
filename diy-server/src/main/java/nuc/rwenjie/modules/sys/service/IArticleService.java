package nuc.rwenjie.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import nuc.rwenjie.modules.sys.entity.ArticleEntity;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Rwenjie
 * @since 2021-05-18
 */
public interface IArticleService extends IService<ArticleEntity> {

    /**
     * 查询所有文章
     * @return java.util.List<nuc.rwenjie.modules.sys.entity.Article>
     **/
    List<ArticleEntity> getAllArticle();

    /**
     * ge
     * @Param: id
     * @return nuc.rwenjie.modules.sys.entity.ArticleEntity
     **/
    ArticleEntity getArticleById(String id);


    /**
     * 更新Article文章的点赞总数
     * @return int
     **/
    int updateArticleStar(String aid);
}
