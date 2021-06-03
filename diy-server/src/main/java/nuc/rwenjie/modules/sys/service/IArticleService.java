package nuc.rwenjie.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import nuc.rwenjie.modules.sys.entity.ArticleEntity;
import nuc.rwenjie.modules.sys.entity.UserEntity;

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


    /**
     * 插入文章
     * @return
     **/
    int submitArticle(ArticleEntity articleEntity);

    /**
     * 根据用户查询文章
     * @Param: userModel
     * @return java.util.List<nuc.rwenjie.modules.sys.entity.ArticleEntity>
     **/
    List<ArticleEntity> publicGoodArticle(UserEntity userModel);
    
    /**
     * 更新是否有商品出售
     * @Param: aid
     * @return int
     **/
    int updateArticleSell(String aid);

    /**
     * 根据用户查询文章
     * @param: userModel
     * @return java.util.List<nuc.rwenjie.modules.sys.entity.ArticleEntity>
     **/
    List<ArticleEntity> getArticleByUser(UserEntity userModel);
}
