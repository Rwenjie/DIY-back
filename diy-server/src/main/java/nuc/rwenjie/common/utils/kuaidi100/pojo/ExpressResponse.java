package nuc.rwenjie.common.utils.kuaidi100.pojo;

import com.kuaidi100.sdk.response.QueryTrackData;
import lombok.Data;

import java.util.List;

/**
 * @Author Rwenjie
 * @ClassName ExpressResponse
 * @Description TODO 查询快递返回结果
 * @Date 2021/3/7 18:03
 **/

@Data
public class ExpressResponse {

    private String trailUrl;
    private String arrivalTime;
    private String totalTime;
    private String message;
    private String nu;
    private String isCheck;
    private String com;
    private String status;
    private List<QueryTrackData> list;

}
