package nuc.rwenjie.modules.sys.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import nuc.rwenjie.common.utils.RespBean;
import nuc.rwenjie.common.utils.ali.phoneVerify.pojo.SMSResponse;
import nuc.rwenjie.common.utils.ali.phoneVerify.service.SMSService;
import nuc.rwenjie.common.utils.ali.phoneVerify.util.SMSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Rwenjie
 * @ClassName CaptchaController
 * @Description TODO 短信验证码
 * @Date 2021/5/7 9:39
 **/
@Api(tags = "CaptchaController")
@RestController
@RequestMapping("/captcha")
public class CaptchaController extends BaseController{

    @Autowired
    SMSService smsService;

    @ApiOperation(value = "发送短信验证码")
    @GetMapping("/sent")
    public RespBean sendCaptcha(String mobile) {
        SMSResponse sms = smsService.sendMessModel(mobile, 1);
        if(sms.getCode().equals("OK")) {
            return RespBean.success("获取验证码成功");
        } else {
            return RespBean.error("获取验证码失败");
        }
    }


    @ApiOperation(value = "验证短信验证码")
    @GetMapping("/verify")
    public RespBean CheckCaptcha(String mobile, String smsCode) {
        String res =  smsService.verifyCode(mobile, smsCode);
        if (res.equals(SMSUtil.CaptchaOk)){
            return RespBean.success("验证成功");
        }
        return RespBean.error(res);
    }
}
