package nuc.rwenjie.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import nuc.rwenjie.modules.sys.entity.ArticleEntity;
import nuc.rwenjie.modules.sys.mapper.ArticleMapper;
import nuc.rwenjie.modules.sys.service.IArticleService;
import nuc.rwenjie.modules.sys.service.IArticleStarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


}
