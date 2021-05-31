package nuc.rwenjie.modules.sys.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import nuc.rwenjie.common.utils.RespBean;
import nuc.rwenjie.modules.sys.entity.UserEntity;
import nuc.rwenjie.modules.sys.service.IGoodsStarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Rwenjie
 * @ClassName GoodsStarControllrt
 * @Description TODO 收藏商品
 * @Date 2021/5/20 17:34
 **/

@RestController
@RequestMapping("/item")
@Api(tags = "ArticleStarController")
public class GoodsStarController {

    @Autowired
    IGoodsStarService goodsStarService;

    @Transactional
    @GetMapping("/ren/state")
    @ApiOperation(value = "用户是否点赞了")
    public RespBean getStarState(String gid, Authentication authentication) {
        UserEntity userModel = (UserEntity) authentication.getPrincipal();
        if (userModel == null) {
            return RespBean.error(201, "", false);
        }
        int re = goodsStarService.getStarState(userModel.getUserId(), gid);
        if (re == 0) {
            return RespBean.success(210,"success", false);
        } else {
            return RespBean.success(210,"success", true);
        }
    }

    @Transactional
    @ApiOperation(value = "给文章点赞")
    @PostMapping("/star")
    public RespBean giveStar(@RequestBody String gid, Authentication authentication) {
        UserEntity userModel = (UserEntity) authentication.getPrincipal();
        if (userModel == null) {
            return RespBean.error("请登录");
        }
        int re = goodsStarService.giveStar(userModel.getUserId(), gid);
        if (re == 0) {
            return RespBean.error("操作失败 o(╥﹏╥)o!");
        } else {
            return RespBean.success("点赞成功o(￣▽￣)ｄ!");
        }
    }

    @Transactional
    @ApiOperation(value = "取消文章点赞")
    @PostMapping("/cancel")
    public RespBean cancelStar(@RequestBody String gid, Authentication authentication) {
        System.out.println("=====gid===>"+gid);

        UserEntity userModel = (UserEntity) authentication.getPrincipal();
        if (userModel==null) {
            return RespBean.error("您还没有登录，不能进行此操作");
        }
        int re = goodsStarService.cancelStar(userModel.getUserId(), gid);
        if (re == 0) {
            return RespBean.error("操作失败 o(╥﹏╥)o!");
        } else {
            return RespBean.success("取消点赞╮(╯▽╰)╭ ！");
        }
    }

}
