package nuc.rwenjie.modules.sys.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import nuc.rwenjie.common.utils.RespBean;
import nuc.rwenjie.modules.sys.entity.ArticleStarEntity;
import nuc.rwenjie.modules.sys.entity.UserEntity;
import nuc.rwenjie.modules.sys.service.IArticleStarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Rwenjie
 * @ClassName ArticleStarController
 * @Description TODO 文章点赞
 * @Date 2021/5/20 14:46
 **/

@RestController
@RequestMapping("/article")
@Api(tags = "ArticleStarController")
public class ArticleStarController extends BaseController{

    @Autowired
    IArticleStarService articleStarService;

    @GetMapping("/state")
    @ApiOperation(value = "用户是否点赞了")
    public RespBean getStarState(String aid, Authentication authentication) {
        UserEntity userModel = (UserEntity) authentication.getPrincipal();
        if (userModel == null) {
            return RespBean.error(401, "", false);
        }
        int re = articleStarService.getStarState(userModel.getUserId(), aid);
        if (re == 0) {
            return RespBean.success(210,"success", false);
        } else {
            return RespBean.success(210,"success", true);
        }
    }

    @ApiOperation(value = "给文章点赞")
    @PostMapping("/star")
    public RespBean giveStar(@RequestBody String aid, Authentication authentication) {
        System.out.println(aid);
        UserEntity userModel = (UserEntity) authentication.getPrincipal();
        if (userModel == null) {
            return RespBean.error("请登录");
        }
        int re = articleStarService.giveStar(userModel.getUserId(), aid);
        if (re == 0) {
            return RespBean.error("操作失败 o(╥﹏╥)o!");
        } else {
            return RespBean.success("点赞成功o(￣▽￣)ｄ!");
        }
    }

    @ApiOperation(value = "取消文章点赞")
    @PostMapping("/cancel")
    public RespBean cancelStar(@RequestBody String aid, Authentication authentication) {
        UserEntity userModel = (UserEntity) authentication.getPrincipal();
        if (userModel==null) {
            return RespBean.error("您还没有登录，不能进行此操作");
        }
        int re = articleStarService.cancelStar(userModel.getUserId(), aid);
        if (re == 0) {
            return RespBean.error("操作失败 o(╥﹏╥)o!");
        } else {
            return RespBean.success("取消点赞╮(╯▽╰)╭ ！");
        }
    }

}
