package nuc.rwenjie.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import nuc.rwenjie.common.utils.Time;
import nuc.rwenjie.modules.sys.controller.vo.OrderVO;
import nuc.rwenjie.modules.sys.dataobject.OrderDetailDO;
import nuc.rwenjie.modules.sys.entity.OrderEntity;
import nuc.rwenjie.modules.sys.entity.UserEntity;
import nuc.rwenjie.modules.sys.mapper.OrderDetailMapper;
import nuc.rwenjie.modules.sys.mapper.OrderMapper;
import nuc.rwenjie.modules.sys.service.ICartService;
import nuc.rwenjie.modules.sys.service.IGoodsService;
import nuc.rwenjie.modules.sys.service.IOrderService;
import nuc.rwenjie.modules.sys.service.ISkuService;
import nuc.rwenjie.modules.sys.service.model.CartModel;
import nuc.rwenjie.modules.sys.service.model.OrderDetailModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Rwenjie
 * @since 2021-05-27
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, OrderEntity> implements IOrderService {

    @Autowired
    ICartService cartService;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    OrderDetailMapper detailMapper;

    @Autowired
    IGoodsService goodsService;

    @Autowired
    ISkuService skuService;

    /**
     * 根据购物车创建订单
     * @param cartId
     * @param user
     * @return int
     */
    @Override
    public Long createOrderByCart(Long[] cartId, UserEntity user) {

        List<CartModel> cartModels = cartService.getCartListByIds(cartId);
        String sellId = cartService.getCartById(cartId[0]).getProduct().getUserId();
        OrderEntity order = new OrderEntity();
        order.setBuyerId(user.getUserId());
        order.setOrderStatus(0);
        order.setSellerId(sellId);
        order.setAfterStatus(0);
        order.setCreatedTime(Time.NowTime());
        orderMapper.insert(order);
        AtomicReference<Integer> rows = new AtomicReference<>(0);
        cartModels.forEach( cart -> {
            OrderDetailDO detailDO = new OrderDetailDO();
            detailDO.setOrderId(order.getId());
            detailDO.setArticleId(cart.getArticleId());
            detailDO.setCount(cart.getCount());
            detailDO.setAmount(cart.getAmount());
            detailDO.setGoodsId(cart.getProduct().getId());
            detailDO.setSkuId(cart.getSku().getId());
            detailDO.setCreateTime(Time.NowTime());
            rows.updateAndGet(v -> v + detailMapper.insert(detailDO));
        });
        return order.getId();
    }

    /**
     * 商品页面直接创建订单
     *
     * @param orderDetailDO 用户详情
     * @param user 用户名
     * @return int
     */
    @Override
    public Long createOrderNow(OrderDetailDO orderDetailDO, UserEntity user) {
        String sellId = goodsService.getGoodsById(orderDetailDO.getGoodsId()).getUserId();
        OrderEntity order = new OrderEntity();
        order.setBuyerId(user.getUserId());
        order.setOrderStatus(0);
        order.setSellerId(sellId);
        order.setAfterStatus(0);
        order.setCreatedTime(Time.NowTime());
        orderMapper.insert(order);
        orderDetailDO.setOrderId(order.getId());
        orderDetailDO.setCreateTime(Time.NowTime());
        int row = detailMapper.insert(orderDetailDO);
        if (row !=0) {
            return order.getId();
        }
        return -1L;
    }

    public List<OrderDetailModel> getOrderDetailByOrder(OrderEntity orderEntity) {
        List<OrderDetailDO> detailDOS = detailMapper.selectList(new QueryWrapper<OrderDetailDO>()
                .eq("order_id", orderEntity.getId()));
        List<OrderDetailModel> detailModelList = new ArrayList<>();
        detailDOS.forEach(detailDO -> {
            OrderDetailModel detailModel = convertFromModel(detailDO);
            if (detailModel!=null) {
                detailModelList.add(detailModel);
            }
        });
        return detailModelList;
    }



    /**
     * 根据用户查询订单
     *
     * @param user 用户
     * @return java.util.List<nuc.rwenjie.modules.sys.controller.vo.OrderVo>
     *
     */
    @Override
    public List<OrderVO> getOrderByUser(UserEntity user) {
        List<OrderEntity> orderList = orderMapper.selectList(new QueryWrapper<OrderEntity>()
                .eq("buyer_id", user.getUserId()));
        List<OrderVO> orderVOS = new ArrayList<>();
        orderList.forEach( orderEntity -> {
            OrderVO orderVO = new OrderVO();
            orderVO.setOrder(orderEntity);
            List<OrderDetailModel> detailModels = getOrderDetailByOrder(orderEntity);
            orderVO.setOrderDetailList(detailModels);
            orderVOS.add(orderVO);
        });
        return orderVOS;
    }

    /**
     * 根据id查询
     *
     * @param oid
     * @return nuc.rwenjie.modules.sys.controller.vo.OrderVO
     */
    @Override
    public OrderVO getOrderByOid(Long oid) {
        OrderVO orderVO = new OrderVO();
        OrderEntity orderEntity = orderMapper.selectById(oid);
        System.out.println("+++++++++++++++++++++++++"+oid+"+++++++++++++++++++++++++++++++++++++++++++++++"+orderEntity);
        orderVO.setOrder(orderEntity);
        orderVO.setOrderDetailList(getOrderDetailByOrder(orderEntity));
        return orderVO;
    }

    private OrderDetailModel convertFromModel(OrderDetailDO detailDO){
        if (detailDO == null){
            return null;
        }
        OrderDetailModel orderDetailModel = new OrderDetailModel();
        BeanUtils.copyProperties(detailDO, orderDetailModel);
        orderDetailModel.setGoods(goodsService.getGoodsById(detailDO.getGoodsId()));
        orderDetailModel.setSku(skuService.getSkuModelBySkuId(detailDO.getSkuId()));
        return orderDetailModel;
    }
}
