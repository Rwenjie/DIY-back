package nuc.rwenjie.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import nuc.rwenjie.modules.sys.dataobject.CartDO;
import nuc.rwenjie.modules.sys.entity.UserEntity;
import nuc.rwenjie.modules.sys.service.model.CartModel;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Rwenjie
 * @since 2021-05-26
 */
public interface ICartService extends IService<CartDO> {

    /**
     * 查询现在已有的购物车列表
     * @Param: user
     * @return java.util.List<nuc.rwenjie.modules.sys.service.model.CartModel>
     **/
    List<CartModel> getCartListByUser(UserEntity user);

    /**
     * 添加新的商品
     * @Param: cartDO
     * @return int
     **/
    int addCart(CartDO cartDO);

    /**
     * 更新现在购物车中已有的商品的数量
     * @Param: cartDO
     * @return int
     **/
    int updateCartCount(CartDO cartDO);


    /**
     * 删除商品数量
     * @Param: idList
     * @return int
     **/
    int deleteCart(Integer[] idList);
}
