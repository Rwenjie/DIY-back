package nuc.rwenjie.common.utils.kuaidi100.pojo;

import com.kuaidi100.sdk.contant.CompanyConstant;
import lombok.Data;

/**
 * @Author Rwenjie
 * @ClassName ExpressRequest
 * @Description TODO 快递请求数据
 * @Date 2021/3/7 18:11
 **/

@Data
public class ExpressRequest {

    /**
     * 快递公司编码
     */
    private String com;

    /**
     * 快递单号
     */
    private String num;

    /**
     * 快递电话
     */
    private String phone;

    /**
     * 出发城市
     */
    private String from;

    /**
     * 目的地城市
     */
    private String to;


}
