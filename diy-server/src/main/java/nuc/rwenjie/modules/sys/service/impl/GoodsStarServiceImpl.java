package nuc.rwenjie.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import nuc.rwenjie.common.utils.Time;
import nuc.rwenjie.modules.sys.entity.GoodsStarEntity;
import nuc.rwenjie.modules.sys.mapper.GoodsStarMapper;
import nuc.rwenjie.modules.sys.service.IGoodsService;
import nuc.rwenjie.modules.sys.service.IGoodsStarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author Rwenjie
 * @ClassName GoodsStarServiceImpl
 * @Description TODO 商品关注
 * @Date 2021/5/20 17:25
 **/

@Service
public class GoodsStarServiceImpl extends ServiceImpl<GoodsStarMapper, GoodsStarEntity> implements IGoodsStarService {

    @Autowired
    GoodsStarMapper goodeStarMapper;

    @Autowired
    IGoodsService goodsService;
    /**
     * 判断用户是否进行了收藏
     *
     * @param userId
     * @param gid
     * @return int
     */
    @Override
    public int getStarState(String userId, String gid) {
        int row = goodeStarMapper.selectCount(new QueryWrapper<GoodsStarEntity>()
                .eq("uid", userId)
                .eq("gid", gid));
        return row;
    }

    /**
     * 用户点赞
     *
     * @param userId
     * @param gid
     * @return int
     */
    @Override
    public int giveStar(String userId, String gid) {
        if (getStarState(userId, gid) !=0 ){
            return 1;
        }
        GoodsStarEntity goodsStarEntity = new GoodsStarEntity();
        goodsStarEntity.setGid(gid);
        goodsStarEntity.setUid(userId);
        goodsStarEntity.setCreateTime(Time.NowTime());
        int row = goodeStarMapper.insert(goodsStarEntity);;
        goodsService.updateGoodsStar(gid);
        return row;
    }

    /**
     * 取消点赞
     *
     * @param userId
     * @param gid
     * @return int
     */
    @Override
    public int cancelStar(String userId, String gid) {
        if (getStarState(userId, gid) ==0 ){
            return 1;
        }
        int row = goodeStarMapper.delete(new QueryWrapper<GoodsStarEntity>()
                .eq("uid", userId)
                .eq("gid", gid));
        goodsService.updateGoodsStar(gid);
        return row;
    }

    /**
     * 统计商品的关注数量
     *
     * @param gid
     * @return int
     * @Param: gid
     */
    @Override
    public Integer getStarCount(String gid) {
        return goodeStarMapper.selectCount(new QueryWrapper<GoodsStarEntity>()
                .eq("gid", gid));
    }

}