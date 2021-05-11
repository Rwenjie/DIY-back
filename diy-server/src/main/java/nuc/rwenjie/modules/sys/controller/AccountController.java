package nuc.rwenjie.modules.sys.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import nuc.rwenjie.common.error.BusinessException;
import nuc.rwenjie.common.utils.RespBean;
import nuc.rwenjie.modules.sys.mapper.UserMapper;
import nuc.rwenjie.modules.sys.service.AccountService;
import nuc.rwenjie.modules.sys.service.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @Author Rwenjie
 * @ClassName AccountController
 * @Description TODO 账号相关 修改密码 找回
 * @Date 2021/5/7 9:42
 **/

@Api(tags = "AccountController")
@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountService accountService;


    @ApiOperation(value = "原密码是否正确")
    @GetMapping("/oldpass")
    public RespBean checkOldPwd(String password, Authentication authentication) throws BusinessException {
        UserModel userModel = (UserModel) authentication.getPrincipal();
        System.out.println(userModel);
        return accountService.checkOldPwd(password, userModel.getMobile());
    }

    @ApiOperation(value = "修改密码")
    @PostMapping("/change/pass")
    public RespBean chengPwd(String password, Principal principal) throws BusinessException {
        return accountService.chengPwd(password, principal.getName());
    }
}
