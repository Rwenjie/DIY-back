package nuc.rwenjie.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import nuc.rwenjie.modules.sys.controller.vo.OrderVO;
import nuc.rwenjie.modules.sys.dataobject.OrderDetailDO;
import nuc.rwenjie.modules.sys.entity.OrderEntity;
import nuc.rwenjie.modules.sys.entity.UserEntity;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Rwenjie
 * @since 2021-05-27
 */
public interface IOrderService extends IService<OrderEntity> {

    /**
     * 根据购物车创建订单
     * @Param: cartId
     * @return int
     **/
    Long createOrderByCart(Long[] cartId, UserEntity user);

    /**
     * 商品页面直接创建订单
     * @Param: orderDetailDO
     * @return Long
     **/
    Long createOrderNow(OrderDetailDO orderDetailDO, UserEntity user);

    /**
     * 根据用户查询订单
     * @Param: user
     * @return java.util.List<nuc.rwenjie.modules.sys.controller.vo.OrderVo>
     **/
    List<OrderVO> getOrderByUser(UserEntity user);


    /**
     * 根据id查询
     * @param: oid
     * @return nuc.rwenjie.modules.sys.controller.vo.OrderVO
     **/
    OrderVO getOrderByOid(Long oid);
}
