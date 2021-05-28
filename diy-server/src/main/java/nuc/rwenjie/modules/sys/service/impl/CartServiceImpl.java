package nuc.rwenjie.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import nuc.rwenjie.common.utils.Time;
import nuc.rwenjie.modules.sys.dataobject.CartDO;
import nuc.rwenjie.modules.sys.entity.UserEntity;
import nuc.rwenjie.modules.sys.mapper.CartMapper;
import nuc.rwenjie.modules.sys.service.ICartService;
import nuc.rwenjie.modules.sys.service.IGoodsService;
import nuc.rwenjie.modules.sys.service.ISkuService;
import nuc.rwenjie.modules.sys.service.model.CartModel;
import nuc.rwenjie.modules.sys.service.model.GoodsModel;
import nuc.rwenjie.modules.sys.service.model.SkuModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Rwenjie
 * @since 2021-05-26
 */
@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, CartDO> implements ICartService {

    @Autowired
    CartMapper cartMapper;
    @Autowired
    IGoodsService goodsService;
    @Autowired
    ISkuService skuService;
    /**
     * 查询现在已有的购物车列表
     *
     * @param user
     * @return java.util.List<nuc.rwenjie.modules.sys.service.model.CartModel>
     * @Param: user
     */
    @Override
    public List<CartModel> getCartListByUser(UserEntity user) {
        List<CartDO> cartDOS = cartMapper.selectList(new QueryWrapper<CartDO>().eq("uid", user.getUserId()));
        List<CartModel>  cartModels = new ArrayList<>();
        cartDOS.forEach((cartDO -> {
            CartModel cartModel = convertFromModel(cartDO);
            if (cartModel!=null) {
                cartModels.add(cartModel);
            }
        }));
        return cartModels;
    }

    /**
     * 添加新的商品
     *
     * @param cartDO
     * @return int
     * @Param: cartDO
     */
    @Override
    public int addCart(CartDO cartDO) {
        cartDO.setCreateTime(Time.NowTime());
        return cartMapper.insert(cartDO);
    }

    /**
     * 更新现在购物车中已有的商品的数量
     *
     * @param cartDO
     * @return int
     * @Param: cartDO
     */
    @Override
    public int updateCartCount(CartDO cartDO) {
        return cartMapper.updateById(cartDO);
    }

    /**
     * 删除商品数量
     *
     * @param idList
     * @return int
     * @Param: idList
     */
    @Override
    public int deleteCart(Integer[] idList) {
        int row = 0;
        for (Integer id: idList) {
            row += cartMapper.deleteById(id);
        }
        return row;
    }

    /**
     * 根据id的集合查询购物车商品的集合
     *
     * @param cartId
     * @return java.util.List<nuc.rwenjie.modules.sys.service.model.CartModel>
     * @Param: cartId
     */
    @Override
    public CartModel getCartById(Long cartId) {
        CartDO cartDO = cartMapper.selectById(cartId);
        return convertFromModel(cartDO);
    }

    /**
     * 根据id的集合查询购物车商品的集合
     *
     * @param cartId
     * @return java.util.List<nuc.rwenjie.modules.sys.service.model.CartModel>
     * @Param: cartId
     */
    @Override
    public List<CartModel> getCartListByIds(Long[] cartId) {
        List<CartModel> list = new ArrayList<>();
        for (Long aLong : cartId) {
            CartModel cartModel = getCartById(aLong);
            list.add(cartModel);
        }
        return list;
    }


    private CartModel convertFromModel(CartDO cartDO){
        if (cartDO == null){
            return null;
        }
        CartModel cartModel = new CartModel();
        BeanUtils.copyProperties(cartDO, cartModel);
        GoodsModel goodsModel = goodsService.getGoodsById(cartDO.getGoodsId().toString());
        SkuModel skuModel = skuService.getSkuModelBySkuId(cartDO.getSkuId());
        cartModel.setProduct(goodsModel);
        cartModel.setSku(skuModel);



        return cartModel;
    }
}
