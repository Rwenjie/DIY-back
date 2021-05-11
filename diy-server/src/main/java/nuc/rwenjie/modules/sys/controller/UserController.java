package nuc.rwenjie.modules.sys.controller;

import nuc.rwenjie.common.utils.RespBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import nuc.rwenjie.modules.sys.service.UserService;
import nuc.rwenjie.modules.sys.service.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public RespBean getUserInfo(Principal principal) {
        if (principal==null){
            return null;
        }
      /*  String username = principal.getName();
        UserModel userModel = userService.getUserByUsername(username);
        user.setPassword(null);
        return RespBean.success(user);*/
        return null;
    }

}
