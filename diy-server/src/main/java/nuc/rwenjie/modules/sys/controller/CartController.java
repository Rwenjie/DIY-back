package nuc.rwenjie.modules.sys.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import nuc.rwenjie.common.utils.RespBean;
import nuc.rwenjie.modules.sys.dataobject.CartDO;
import nuc.rwenjie.modules.sys.entity.UserEntity;
import nuc.rwenjie.modules.sys.service.ICartService;
import nuc.rwenjie.modules.sys.service.model.CartModel;
import org.hibernate.type.LongType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Rwenjie
 * @since 2021-05-26
 */
@Api(tags = "CartController")
@RestController
@RequestMapping("/cart")
public class CartController {


    @Autowired
    ICartService cartService;

    @ApiOperation(value = "根据用户查询购物车列表")
    @GetMapping("/all")
    public RespBean getCartListByUser(Authentication authentication) {
        UserEntity user = (UserEntity)authentication.getPrincipal();
        if (user==null) {
            return RespBean.error(401, "用户未登录");
        }
        List<CartModel> cartList = cartService.getCartListByUser(user);
        return RespBean.success(cartList);
    }

    @ApiOperation(value = "添加新的商品")
    @PostMapping("/add")
    public RespBean addCart(@RequestBody CartDO cartDO, Authentication authentication) {
        System.out.println(cartDO);
        UserEntity user = (UserEntity)authentication.getPrincipal();
        if (user==null) {
            return RespBean.error(401, "用户未登录");
        }
        cartDO.setUid(user.getUserId());
        int row = cartService.addCart(cartDO);
        return RespBean.success(row);
    }

    @ApiOperation(value = "购物车已有的商品数量增加")
    @PostMapping("/count/add")
    public RespBean updateCartCount(@RequestBody CartDO cartDO, Authentication authentication) {
        UserEntity user = (UserEntity)authentication.getPrincipal();
        if (user==null) {
            return RespBean.error(401, "用户未登录");
        }
        int row = cartService.updateCartCount(cartDO);
        return RespBean.success(row);
    }

    @ApiOperation(value = "删除商品")
    @GetMapping("/delete")
    public RespBean deleteCart(Integer[] idList, Authentication authentication) {
        UserEntity user = (UserEntity)authentication.getPrincipal();
        if (user==null) {
            return RespBean.error(401, "用户未登录");
        }
        int row = cartService.deleteCart(idList);
        return RespBean.success(row);
    }




}
