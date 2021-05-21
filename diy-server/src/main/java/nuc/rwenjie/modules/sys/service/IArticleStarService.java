package nuc.rwenjie.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import nuc.rwenjie.modules.sys.entity.ArticleStarEntity;

/**
 * @Author Rwenjie
 * @ClassName ArtileStarService
 * @Description TODO
 * @Date 2021/5/20 14:42
 **/


public interface IArticleStarService extends IService<ArticleStarEntity> {


    /**
     * 用户给文章点赞
     * @Param: aid
     * @Param: uid
     * @return int
     **/
    int giveStar(String uid, String aid);


    /**
     * 用户取消给文章的点赞
     * @Param: aid
     * @Param: uid
     * @return int
     **/
    int cancelStar(String uid, String aid);

    /**
     * 查看用户点赞的状态
     * @Param: userId
     * @Param: aid
     * @return int
     **/
    int getStarState(String userId, String aid);


    /**
     * 查询文章的点赞总数
     * @Param: aid
     * @return int
     **/
    int getStarCount(String aid);
}
