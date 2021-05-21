package nuc.rwenjie.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import nuc.rwenjie.common.utils.RespBean;
import nuc.rwenjie.modules.sys.dataobject.GoodsDO;
import nuc.rwenjie.modules.sys.service.model.GoodsModel;

import java.util.List;

/**
 * <p>
 * spu表，该表描述的是一个抽象的商品，比如 手机 服务类
 * </p>
 *
 * @author Rwenjie
 * @since 2021-05-10
 */
public interface IGoodsService extends IService<GoodsDO> {

    /**
     * 插入商品
     * @Param: GoodsModel goodsModel Id
     * @return java.lang.Long
     **/
   RespBean insertGoods(GoodsModel goodsModel, String id);

   /**
    * 根据用户id查询商品
    * @Param: uid
    * @return java.util.List<nuc.rwenjie.modules.sys.service.model.GoodsModel>
    **/
   List<GoodsModel> selectGoodsByUserId(String uid);


   /**
    * 查询所有商品
    * @return nuc.rwenjie.common.utils.RespBean
    **/
   List<GoodsModel> selectAll();

   /**
    * 逻辑删除商品
    * @return java.lang.Integer
    **/
    Integer changeStatus(String gid, Integer status);

    /**
     * 根据商品的id查询商品
     * @Param: gid
     * @return nuc.rwenjie.modules.sys.service.model.GoodsModel
     **/

    GoodsModel getGoodsById(String gid);
    /**
     * 更新商品的点赞数量
     * @Param: gid
     * @return int
     **/
    int updateGoodsStar(String gid);

    /**
     * 根据文章查询商品
     * @Param: aid
     * @return nuc.rwenjie.modules.sys.service.model.GoodsModel
     **/
    GoodsModel getItemByArticle(String aid);
}
