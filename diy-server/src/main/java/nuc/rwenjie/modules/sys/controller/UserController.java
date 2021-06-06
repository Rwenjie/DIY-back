package nuc.rwenjie.modules.sys.controller;

import io.rong.models.response.UserResult;
import nuc.rwenjie.common.error.BusinessException;
import nuc.rwenjie.common.utils.RespBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import nuc.rwenjie.modules.sys.entity.UserEntity;
import nuc.rwenjie.modules.sys.service.UserService;
import nuc.rwenjie.modules.sys.service.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * @Author Rwenjie
 * @ClassName UserController
 * @Description TODO 用户信息
 * @Date 2021/3/20 20:18
 **/

@Api(tags = "UserController")
@RestController
@RequestMapping("/user")
public class UserController extends BaseController{

    @Autowired
    private UserService userService;

    @ApiOperation(value = "获取当前用户信息")
    @GetMapping("/info")
    public RespBean getUserInfo(Authentication authentication) throws BusinessException {
        UserEntity userModel = (UserEntity) authentication.getPrincipal();
        if (userModel==null) {
            return RespBean.error("请进行登录");
        }
        UserEntity userEntity = userService.getUserById(userModel.getUserId());
        if (userEntity != null ) {
            userEntity.setPassword("");
            userEntity.setSalt("");
            return RespBean.success(userEntity);
        } else {
            return RespBean.error("获取用户信息失败");
        }
    }

    @ApiOperation(value = "获取当前用户信息")
    @PostMapping("/update")
    public RespBean updateUserInfo(@RequestBody UserEntity user){
        System.out.println("<=======>user"+user.toString());
        UserEntity userEntity = userService.updateUserInfo(user);
        return RespBean.success("更新成功",userEntity);
    }

    @ApiOperation(value = "查询文章作者")
    @GetMapping("/ren/article")
    public RespBean getUserByArticle(String article) throws BusinessException {
        UserEntity userEntity = userService.getUserById(article);
        if (userEntity != null ) {
            userEntity.setPassword("");
            userEntity.setSalt("");
            return RespBean.success(userEntity);
        } else {
            return RespBean.error("获取用户信息失败");
        }
    }

}
