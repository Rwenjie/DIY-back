package nuc.rwenjie.common.utils.ali.phoneVerify.pojo;

import lombok.Data;

/**
 * @Author Rwenjie
 * @ClassName SMSResult
 * @Description TODO 验证码返回结果
 * @Date 2021/3/6 20:50
 **/

@Data
public class SMSResponse {

    private String Message;
    private String RequestId;
    private String BizId;
    private String Code;
}
