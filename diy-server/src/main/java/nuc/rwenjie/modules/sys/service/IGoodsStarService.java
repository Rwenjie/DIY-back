package nuc.rwenjie.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import nuc.rwenjie.modules.sys.entity.GoodsStarEntity;

/**
 * @Author Rwenjie
 * @ClassName IGoodsStarService
 * @Description TODO
 * @Date 2021/5/20 17:25
 **/


public interface IGoodsStarService extends IService<GoodsStarEntity> {
    /**
     * 判断用户是否进行了收藏
     * @Param: userId
     * @Param: gid
     * @return int
     **/
    int getStarState(String userId, String gid);

    /**
     * 用户点赞
     * @Param: userId
     * @Param: gid
     * @return int
     **/
    int giveStar(String userId, String gid);

    /**
     * 取消点赞
     * @Param: userId
     * @Param: gid
     * @return int
     **/
    int cancelStar(String userId, String gid);

    /**
     * 统计商品的关注数量
     * @Param: gid
     * @return int
     **/
    Integer getStarCount(String gid);


}
