package nuc.rwenjie.common.utils.ali.phoneVerify.service;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.Gson;
import nuc.rwenjie.common.constant.RedisConstant;
import nuc.rwenjie.common.utils.RedisOperator;
import nuc.rwenjie.common.utils.ali.phoneVerify.component.PhoneRandomBuilder;
import nuc.rwenjie.common.utils.ali.phoneVerify.pojo.SMSResponse;
import nuc.rwenjie.common.utils.ali.phoneVerify.util.SMSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @ClassName SMSService
 * @Description TODO 阿里云短信服务
 * @Author Rwenjie
 * @Date 2021/3/5 13:03
 **/



@Service
public class SMSService {

    @Value("${SMS.accessKeyId}")
    private String accessKeyId;

    @Value("${SMS.accessKeySecret}")
    private String accessKeySecret;

    @Value("${SMS.product}")
    private String product;

    @Value("${SMS.domain}")
    private String domain;

    @Value("${SMS.signName}")
    private String signName;

    @Value("${SMS.templateLoginCode}")
    private String templateLoginCode;

    @Value("${SMS.templateForgotPwdCode}")
    private String templateForgotPwdCode;

    @Value("${SMS.templateRegiCode}")
    private  String templateRegiCode;

    @Autowired
    RedisOperator redisOperator;


    /**
     * @Author Rwenjie
     * @Description //TODO 发送验证码
     * @Date 17:08 2021/3/6
     * @Param: number 接收验证码的手机号
     * @Param: template 短信模板 1:登录模板  2：注册模板  3:找回密码模板
     * @return java.lang.String
     **/
    public SMSResponse sendMessModel(String phone, Integer template){

        //验证码随机数
        String code = PhoneRandomBuilder.randomBuilder();
        //把验证码存入缓存中
        redisOperator.set(RedisConstant.USER_LOGIN_CODE + phone, code);
        //设置过期时间
        redisOperator.expire(RedisConstant.USER_LOGIN_CODE + phone, 3000);
        String[] params = {code};
        System.out.println("SMSService=>已经为用户"+phone+"发送了验证码："+code);

        DefaultProfile profile = DefaultProfile.getProfile("cn-qingdao", accessKeyId, accessKeySecret);
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain(domain);
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", signName);
        if (template==1) {
            request.putQueryParameter("TemplateCode", templateLoginCode);
        }else if (template==2){
            request.putQueryParameter("TemplateCode", templateRegiCode);
        }else {
            request.putQueryParameter("TemplateCode", templateForgotPwdCode);
        }

        request.putQueryParameter("TemplateParam", "{code: \""+code+"\"}");
        try {
            //发送请求
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
            //将请求结果String转换为一个对象
            return new Gson().fromJson(response.getData(), SMSResponse.class);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Author Rwenjie
     * @Description //TODO 验证验证码
     * @Date 21:46 2021/3/6
     * @Param: phone 手机号码
     * @Param: captcha 验证码
     * @return java.lang.String
     **/

    public String verifyCode(String phone, String captcha) {
        System.out.println(captcha);
        //判断验证码是否存在
        if(redisOperator.hasKey(RedisConstant.USER_LOGIN_CODE+phone)){
            String obj = (String)redisOperator.get(RedisConstant.USER_LOGIN_CODE + phone);
            if (obj.equals(captcha)){
                return SMSUtil.CaptchaOk;
            } else {
                return SMSUtil.CaptchaErr;
            }
        }
        return SMSUtil.CaptchaTimeout;
    }


}
