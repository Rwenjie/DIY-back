package nuc.rwenjie.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import nuc.rwenjie.modules.sys.entity.ArticleEntity;
import nuc.rwenjie.modules.sys.entity.ArticleStarEntity;
import nuc.rwenjie.modules.sys.entity.UserEntity;
import nuc.rwenjie.modules.sys.mapper.ArticleMapper;
import nuc.rwenjie.modules.sys.service.IArticleService;
import nuc.rwenjie.modules.sys.service.IArticleStarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Rwenjie
 * @since 2021-05-18
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, ArticleEntity> implements IArticleService {

    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    IArticleStarService articleStarService;
    /**
     * 查询所有文章
     *
     * @return java.util.List<nuc.rwenjie.modules.sys.entity.Article>
     **/
    @Override
    public List<ArticleEntity> getAllArticle() {
        return articleMapper.selectList(new QueryWrapper<ArticleEntity>());
    }

    /**
     * 根据id查询文章详情
     *
     * @param id
     * @return nuc.rwenjie.modules.sys.entity.ArticleEntity
     * @Param: id
     */
    @Override
    public ArticleEntity getArticleById(String id) {
        System.out.println(id);
        ArticleEntity articleEntity = articleMapper.selectById(id);
        if (articleEntity==null) {
            return null;
        }
        return articleMapper.selectById(id);
    }

    /**
     * 更新Article文章的点赞总数
     *
     * @return int
     **/
    @Override
    public int updateArticleStar(String aid) {
        long count = articleStarService.getStarCount(aid);
        ArticleEntity articleEntity = getArticleById(aid);
        if (articleEntity!=null) {
            articleEntity.setStar(count);
            return articleMapper.update(articleEntity, new UpdateWrapper<ArticleEntity>().eq("id", aid));
        }
        return 0;
    }

    /**
     * 插入文章
     *
     * @param articleEntity
     * @return
     */
    @Override
    public int submitArticle(ArticleEntity articleEntity) {
        articleEntity.setCreateTime(new Date());
        articleEntity.setUpdateTime(new Date());
        return articleMapper.insert(articleEntity);
    }

    /**
     * 根据用户查询文章
     *
     * @param userModel
     * @return java.util.List<nuc.rwenjie.modules.sys.entity.ArticleEntity>
     * @Param: userModel
     */
    @Override
    public List<ArticleEntity> publicGoodArticle(UserEntity userModel) {
        return articleMapper.selectList(new QueryWrapper<ArticleEntity>()
                .eq("user_id", userModel.getUserId())
                .eq("sell", 0));
    }

    /**
     * 更新是否有商品出售
     *
     * @param aid
     * @return int
     * @Param: aid
     */
    @Override
    public int updateArticleSell(String aid) {
        ArticleEntity articleEntity = articleMapper.selectById(aid);
        if (articleEntity!=null) {
            articleEntity.setSell(1);
            return articleMapper.updateById(articleEntity);
        }
        return 0;
    }

    /**
     * 根据用户查询文章
     *
     * @param userModel
     * @return java.util.List<nuc.rwenjie.modules.sys.entity.ArticleEntity>
     * @param: userModel
     */
    @Override
    public List<ArticleEntity> getArticleByUser(UserEntity userModel) {
        return articleMapper.selectList(new QueryWrapper<ArticleEntity>()
                .eq("user_id", userModel.getUserId()));
    }

    /**
     * 查询用户关注的文章
     *
     * @param userModel 用户
     * @return java.util.List<nuc.rwenjie.modules.sys.entity.ArticleEntity>
     **/
    @Override
    public List<ArticleEntity> getStarArticle(UserEntity userModel) {
        List<ArticleStarEntity> starArticleList = articleStarService.getStarArticle(userModel);
        List<ArticleEntity> articleEntities = new ArrayList<>();
        starArticleList.forEach(item -> {
            ArticleEntity articleEntity = articleMapper.selectById(item.getAid());
            articleEntities.add(articleEntity);
        });
        return articleEntities;
    }
}
