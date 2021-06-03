package nuc.rwenjie.modules.sys.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import nuc.rwenjie.common.error.BusinessException;
import nuc.rwenjie.common.utils.RedisOperator;
import nuc.rwenjie.common.utils.RespBean;
import nuc.rwenjie.modules.sys.service.LoginService;
import nuc.rwenjie.modules.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author Rwenjie
 * @ClassName LoginController
 * @Description TODO 登录
 * @Date 2021/3/18 13:56
 **/

@Api(tags = "LoginController")
@RestController
public class LoginController extends BaseController{

    @Autowired
    LoginService loginService;
    @Autowired
    private UserService userService;

    @Autowired
    private RedisOperator redisOperator;

    @ApiOperation(value = "退出登录")
    @GetMapping("/logout")
    public RespBean logout() {
        return RespBean.success("注销成功！");
    }

    @ApiOperation(value = "短信验证码登录")
    @PostMapping("/login/sms")
    public RespBean smsLogin(String mobile, String smsCode, HttpServletRequest request) throws BusinessException {
        return loginService.smsLogin(mobile, smsCode);
    }

    @ApiOperation(value = "账号密码")
    @PostMapping("/login/mobile")
    public RespBean mobileLogin(String mobile, String password, HttpServletRequest request) throws BusinessException {
        return loginService.login(mobile, password);
    }

}
