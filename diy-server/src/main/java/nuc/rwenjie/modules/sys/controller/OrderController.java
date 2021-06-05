package nuc.rwenjie.modules.sys.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import nuc.rwenjie.common.utils.RespBean;
import nuc.rwenjie.modules.sys.controller.vo.OrderVO;
import nuc.rwenjie.modules.sys.dataobject.OrderDetailDO;
import nuc.rwenjie.modules.sys.entity.UserEntity;
import nuc.rwenjie.modules.sys.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Rwenjie
 * @since 2021-05-27
 */
@RestController
@Api(tags = "OrderController")
@RequestMapping("/order")
public class OrderController {

    @Autowired
    IOrderService orderService;

    @Transactional
    @ApiOperation(value="在购物车创建新的订单")
    @PostMapping("/create/cart")
    public RespBean createOrderByCart(@RequestBody Long [] cartId, Authentication authentication) {
        System.out.println("======================================="+cartId);
        UserEntity user = (UserEntity)authentication.getPrincipal();
        if (user==null) {
            return RespBean.error(401, "用户未登录");
        }
        String oid = orderService.createOrderByCart(cartId, user);
        if (oid!=null) {
            return RespBean.success(200, "创建订单成功", oid);
        } else {
            return RespBean.error("下单失败");
        }
    }

    @ApiOperation(value="商品页面直接下单")
    @PostMapping("/create/now")
    public RespBean createOrderNow(@RequestBody OrderDetailDO orderDetailDO, Authentication authentication) {
        UserEntity user = (UserEntity)authentication.getPrincipal();
        if (user==null) {
            return RespBean.error(401, "用户未登录");
        }
        String oid = orderService.createOrderNow(orderDetailDO, user);
        if (!oid.equals("-1")) {
            return RespBean.success(200, "创建订单成功", oid);
        }
        else {
            return RespBean.error(405, "下单失败");
        }
    }

    @ApiOperation(value="根据用户查询商品")
    @GetMapping("/user/all")
    public RespBean getOrderByUser(Authentication authentication) {
        System.out.println("user/all");
        UserEntity user = (UserEntity)authentication.getPrincipal();
        if (user==null) {
            return RespBean.error(401, "用户未登录");
        }
        List<OrderVO> orderVOS =  orderService.getOrderByUser(user);
        return RespBean.success(orderVOS);
    }

    @ApiOperation(value="根据id查询订单")
    @GetMapping("/user/oid")
    public RespBean getOrderByOid(String oid,  Authentication authentication) {
        UserEntity user = (UserEntity)authentication.getPrincipal();
        if (user==null) {
            return RespBean.error(401, "用户未登录");
        }
        OrderVO orderVO = orderService.getOrderByOid(oid);
        return RespBean.success(orderVO);
    }

    @ApiOperation(value = "更新配送地址")
    @GetMapping("/addr/aid")
    public RespBean updateDeliveryAddr(String aid, String oid, Authentication authentication) {
        System.out.println("==============================================="+aid);
        UserEntity user = (UserEntity)authentication.getPrincipal();
        if (user==null) {
            return RespBean.error(401, "用户未登录");
        }
        int row = orderService.updateDeliveryAddr(Long.valueOf(aid), oid);
        if (row == 0) {
            return RespBean.error("更新收货地址失败");
        } else {
            return RespBean.success(200, "", null);
        }

    }

}
