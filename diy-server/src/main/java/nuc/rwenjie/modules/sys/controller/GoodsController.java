package nuc.rwenjie.modules.sys.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import nuc.rwenjie.common.utils.RespBean;
import nuc.rwenjie.modules.sys.entity.GoodsEntity;
import nuc.rwenjie.modules.sys.entity.UserEntity;
import nuc.rwenjie.modules.sys.service.IGoodsService;
import nuc.rwenjie.modules.sys.service.model.GoodsModel;
import nuc.rwenjie.modules.sys.service.model.SkuModel;
import nuc.rwenjie.modules.sys.service.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
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
    public RespBean submit (@RequestBody GoodsEntity goods, Authentication authentication) {
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
    public RespBean changeStatus(String gid, Integer status) {
        Integer row = goodsService.changeStatus(gid, status);
        if (row == 1) {
            return RespBean.success("操作成功");
        }

        return RespBean.error("操作失败") ;
    }

    @ApiOperation("查询所有商品")
    @GetMapping("/listing")
    public RespBean getAllGoods() {
        return RespBean.success(goodsService.selectAll()) ;
    }

    @Transactional
    @ApiOperation("根据文章查询商品")
    @GetMapping("/ren/aid")
    public RespBean getItemByArticle(String aid) {

        System.out.println("aid=====>"+aid);
       GoodsModel goodsModel = goodsService.getItemByArticle(aid);
       if (goodsModel!=null) {
           return RespBean.success(201, goodsModel);
       }
        return RespBean.error(410, "操作失败");
    }
}
