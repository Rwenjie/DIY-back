package nuc.rwenjie.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import nuc.rwenjie.common.utils.Time;
import nuc.rwenjie.modules.sys.entity.ArticleStarEntity;
import nuc.rwenjie.modules.sys.mapper.ArticleMapper;
import nuc.rwenjie.modules.sys.mapper.ArticleStarMapper;
import nuc.rwenjie.modules.sys.service.IArticleService;
import nuc.rwenjie.modules.sys.service.IArticleStarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author Rwenjie
 * @ClassName ArticleStarServiceImpl
 * @Description TODO 文章点赞
 * @Date 2021/5/20 14:43
 **/

@Service
public class ArticleStarServiceImpl extends ServiceImpl<ArticleStarMapper, ArticleStarEntity> implements IArticleStarService {

    @Autowired
    ArticleStarMapper articleStarMapper;

    @Autowired
    IArticleService articleService;
    /**
     * 用户给文章点赞
     * @param aid
     * @param uid
     * @return int
     */
    @Override
    public int giveStar(String uid, String aid) {
        if (getStarState(uid, aid) != 0) {
            return 1;
        }
        ArticleStarEntity articleStar = new ArticleStarEntity();
        articleStar.setAid(aid);
        articleStar.setUid(uid);
        articleStar.setCreateTime(Time.NowTime());
        System.out.println("article=>>>"+articleStar);
        int row = articleStarMapper.insert(articleStar);
        articleService.updateArticleStar(aid);
        System.out.println("点赞成功"+row);
        return row;
    }

    /**
     * 用户取消给文章的点赞
     * @param aid
     * @param uid
     * @return int
     */
    @Override
    public int cancelStar(String uid, String aid) {
        if (getStarState(uid, aid)==0) {
            return 1;
        }
        ArticleStarEntity articleStar = new ArticleStarEntity();
        articleStar.setAid(aid);
        articleStar.setUid(uid);
        int row = articleStarMapper.delete(new QueryWrapper<ArticleStarEntity>()
                .eq("uid", articleStar.getUid())
                .eq("aid", articleStar.getAid()));
        articleService.updateArticleStar(aid);
        return row;
    }

    /**
     * 查看用户点赞的状态
     *
     * @Param userId
     * @Param aid
     * @return int
     */
    @Override
    public int getStarState(String userId, String aid) {
        ArticleStarEntity articleStarEntity = articleStarMapper.selectOne(new QueryWrapper<ArticleStarEntity>()
                .eq("uid", userId)
                .eq("aid", aid));
        if (articleStarEntity!=null) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * 查询文章的点赞总数
     *
     * @param aid
     * @return int
     * @Param: aid
     */
    @Override
    public int getStarCount(String aid) {
        int count = articleStarMapper.selectCount(new QueryWrapper<ArticleStarEntity>()
                .eq("aid", aid));
        return count;
    }
}
