package nuc.rwenjie.common.utils.ali.phoneVerify.component;

import org.springframework.stereotype.Component;

/**
 * @ClassName PhoneRandomBuilder
 * @Description TODO 短信验证码随机生成
 * @Author Rwenjie
 * @Date 2021/3/5 13:01
 **/

@Component
public class PhoneRandomBuilder {
    public static String randomBuilder(){
        String result = "";
        for(int i = 0; i < 6; i++ ){
            result += Math.round( Math.random() * 9 );
        }
        return result;
    }
}
