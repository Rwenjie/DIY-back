package nuc.rwenjie.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import nuc.rwenjie.common.utils.GenerateID;
import nuc.rwenjie.common.utils.Time;
import nuc.rwenjie.modules.sys.controller.vo.OrderVO;
import nuc.rwenjie.modules.sys.dataobject.OrderDetailDO;
import nuc.rwenjie.modules.sys.entity.AddressEntity;
import nuc.rwenjie.modules.sys.entity.OrderEntity;
import nuc.rwenjie.modules.sys.entity.UserEntity;
import nuc.rwenjie.modules.sys.mapper.OrderDetailMapper;
import nuc.rwenjie.modules.sys.mapper.OrderMapper;
import nuc.rwenjie.modules.sys.service.*;
import nuc.rwenjie.modules.sys.service.model.CartModel;
import nuc.rwenjie.modules.sys.service.model.OrderDetailModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.DecimalFormat;
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

    @Autowired
    IAddressService addressService;

    @Resource
    private GenerateID generateId;

    /**
     * 根据购物车创建订单
     * @param cartId
     * @param user
     * @return int
     */
    @Override
    public String createOrderByCart(Long[] cartId, UserEntity user) {
        List<CartModel> cartModels = cartService.getCartListByIds(cartId);
        String sellId = cartService.getCartById(cartId[0]).getProduct().getUserId();
        AddressEntity address = addressService.getDefaultOrder(user.getUserId());
        OrderEntity order = new OrderEntity();
        String oid = generateId.generateOrderId();
        //插入订单表数据
        order.setId(oid);
        order.setAddressId(address.getId());
        order.setBuyerId(user.getUserId());
        order.setOrderStatus(1);
        order.setSellerId(sellId);
        order.setAfterStatus(0);
        order.setLogisticsFee(new BigDecimal("0.00"));
        order.setCreatedTime(Time.NowTime());
        orderMapper.insert(order);
        AtomicReference<Integer> rows = new AtomicReference<>(0);
        AtomicReference<Float> amountTotal = new AtomicReference<>((float) 0);
        //插入订单详情表数据
        cartModels.forEach( cart -> {
            OrderDetailDO detailDO = new OrderDetailDO();
            DecimalFormat df1 = new DecimalFormat("0.00");
            String str = df1.format(cart.getSku().getPrice());
            amountTotal.updateAndGet(v -> (float) (v + Float.parseFloat(str) * cart.getCount()));
            detailDO.setOrderId(order.getId());
            detailDO.setArticleId(cart.getArticleId());
            detailDO.setCount(cart.getCount());
            detailDO.setAmount(cart.getAmount());
            detailDO.setGoodsId(cart.getProduct().getId());
            detailDO.setSkuId(cart.getSku().getId());
            detailDO.setCreateTime(Time.NowTime());
            rows.updateAndGet(v -> v + detailMapper.insert(detailDO));
        });
        // 更新订单总金额
        BigDecimal a1 = new BigDecimal(String.valueOf(amountTotal));
        order.setProductAmountTotal(a1);
        order.setUpdatedTime(Time.NowTime());
        orderMapper.updateById(order);

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
    public String createOrderNow(OrderDetailDO orderDetailDO, UserEntity user) {
        String sellId = goodsService.getGoodsById(orderDetailDO.getGoodsId()).getUserId();
        OrderEntity order = new OrderEntity();
        String oid = generateId.generateOrderId();
        order.setId(oid);
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
        return "-1";
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
    public OrderVO getOrderByOid(String oid) {
        OrderVO orderVO = new OrderVO();
        OrderEntity orderEntity = orderMapper.selectById(oid);
        System.out.println("+++++++++++++++++++++++++"+oid+"+++++++++++++++++++++++++++++++++++++++++++++++"+orderEntity);
        orderVO.setOrder(orderEntity);
        orderVO.setOrderDetailList(getOrderDetailByOrder(orderEntity));
        return orderVO;
    }

    /**
     * 更新收货地址
     *
     * @param aid
     * @return int
     * @param: aid
     */
    @Override
    public int updateDeliveryAddr(Long aid, String oid) {
        OrderEntity orderEntity = orderMapper.selectById(oid);
        if (orderEntity!=null) {
            orderEntity.setAddressId(aid);
            orderEntity.setUpdatedTime(Time.NowTime());
            return orderMapper.updateById(orderEntity);
        }
        return 0;
    }

    /**
     *
     * @param oid 订单编号
     * @param outTradeNo 第三方流水号
     * @param payMethod 支付方式
     * @return int
     **/
    @Override
    public int updatePaySuccess(String oid, String outTradeNo, int payMethod) {

        OrderEntity orderEntity = orderMapper.selectById(oid);
        System.out.println(orderEntity);
        //第三方流水号
        orderEntity.setEscrowTradeNo(outTradeNo);
        // 更新订单状态为 2
        orderEntity.setOrderStatus(2);
        // 支付时间
        orderEntity.setPayTime(Time.NowTime());
        orderEntity.setUpdatedTime(Time.NowTime());
        return orderMapper.updateById(orderEntity);
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
