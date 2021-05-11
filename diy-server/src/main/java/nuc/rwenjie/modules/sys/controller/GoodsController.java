package nuc.rwenjie.modules.sys.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import nuc.rwenjie.common.utils.RespBean;
import nuc.rwenjie.modules.sys.entity.UserEntity;
import nuc.rwenjie.modules.sys.service.IGoodsService;
import nuc.rwenjie.modules.sys.service.model.GoodsModel;
import nuc.rwenjie.modules.sys.service.model.SkuModel;
import nuc.rwenjie.modules.sys.service.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author Rwenjie
 * @ClassName GoodsController
 * @Description TODO 商品
 * @Date 2021/5/9 15:39
 **/

@Api(tags = "GoodsController")
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    IGoodsService goodsService;


    @ApiOperation("提交商品 商品发布")
    @PostMapping("/submit")
    public RespBean submit (@RequestBody GoodsModel goods,  Authentication authentication) {
        System.out.println("++++++++++++++++++++++++++++++++++++"+goods);
        UserEntity userModel = (UserEntity) authentication.getPrincipal();


        return goodsService.insertGoods(goods, userModel.getUserId());
    }

    @ApiOperation("根据用户查询商品")
    @GetMapping("/getByUser")
    public RespBean getByUser(Authentication authentication) {
        UserEntity userModel = (UserEntity) authentication.getPrincipal();
        return RespBean.success(goodsService.selectGoodsByUserId(userModel.getUserId())) ;
    }

    @ApiOperation("根据用户查询商品")
    @GetMapping("/all")
    public RespBean selectAll() {
        return RespBean.success(goodsService.selectAll()) ;
    }

    @ApiOperation("删除商品")
    @GetMapping("/delete")
    public RespBean deleteGoods(String gid) {
        Integer row = goodsService.deleteGoods(gid);
        if (row == 1) {
            return RespBean.success("删除成功");
        }

        return RespBean.error("删除失败") ;
    }
}
