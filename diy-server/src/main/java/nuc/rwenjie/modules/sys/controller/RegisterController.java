package nuc.rwenjie.modules.sys.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import nuc.rwenjie.common.constant.RedisConstant;
import nuc.rwenjie.common.utils.RedisOperator;
import nuc.rwenjie.common.utils.RespBean;
import nuc.rwenjie.modules.sys.entity.UserEntity;
import nuc.rwenjie.modules.sys.service.RegisterService;
import nuc.rwenjie.modules.sys.service.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Rwenjie
 * @ClassName RegisterController
 * @Description TODO 用户注册
 * @Date 2021/3/20 21:17
 **/

@Api(tags = "RegisterController")
@RestController
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    RegisterService registerService;

    @Autowired
    RedisOperator redisOperator;


    @ApiOperation(value = "用户注册")
    @PostMapping("/submit")
    public RespBean userRegister(UserEntity user){
        System.out.println(user);
        int result = registerService.userRegister(user);
        if (result==0){
            return RespBean.error(500, "注册失败");
        }
        return RespBean.success("注册成功");
    }

    @ApiOperation(value = "检查手机号是否被注册")
    @GetMapping("/phone/check")
    public RespBean phoneCheck(@RequestParam("phone") String phone){
        // 先从缓存中查询
        if(redisOperator.hsize(RedisConstant.USER_PHONE_EXIST) != 0){
            if(!redisOperator.hasHkey(RedisConstant.USER_PHONE_EXIST, phone)){
                System.out.println("1");
                return RespBean.success("手机号可用");
            }else{
                System.out.println("12");
                return RespBean.error("该手机号已被注册");
            }
        }else{
            int result = registerService.findByPhone(phone);
            if(result == 0){
                System.out.println("13");
                return RespBean.success("手机号可用");
            }else{
                System.out.println("14");
                return RespBean.error("该手机号已被注册");
            }
        }
    }


    @ApiOperation(value = "检查用户是否被注册")
    @GetMapping("/user/check")
    public RespBean UserCheck(@RequestParam("username") String username) {
        // 先从缓存中查询
        if(redisOperator.hsize(RedisConstant.USER_NAME_EXIST) != 0){
            if(!redisOperator.hasHkey(RedisConstant.USER_NAME_EXIST, username)){
                return RespBean.success("用户名可用");
            }else{
                return RespBean.error("该用户名已被注册");
            }
        }else{
            int result = registerService.findByUsername(username);
            if(result == 0){
                return RespBean.success("用户名可用");
            }else{
                return RespBean.error("该用户名已被注册");
            }
        }
    }
}
