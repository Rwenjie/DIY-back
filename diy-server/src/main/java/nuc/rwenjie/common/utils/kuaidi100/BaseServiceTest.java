package nuc.rwenjie.common.utils.kuaidi100;

import com.google.gson.Gson;
import com.kuaidi100.sdk.api.QueryTrack;
import com.kuaidi100.sdk.api.QueryTrackMap;
import com.kuaidi100.sdk.contant.CompanyConstant;
import com.kuaidi100.sdk.core.IBaseClient;
import com.kuaidi100.sdk.pojo.HttpResult;
import com.kuaidi100.sdk.request.QueryTrackParam;
import com.kuaidi100.sdk.request.QueryTrackReq;
import com.kuaidi100.sdk.response.QueryTrackMapResp;
import com.kuaidi100.sdk.utils.SignUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @ClassName Test
 * @Description TODO
 * @Author Rwenjie
 * @Date 2021/3/5 20:35
 **/

@Component
public class BaseServiceTest {

    @Value("${SMS.domain}")
    private String domain;
    @Value("${express.key}")
    private String key;
    @Value("${express.customer}")
    private  String customer;
    @Value("${express.secret}")
    private String secret;
    @Value("${express.userid}")
    private String userid;

    /**
     * 查询物流轨迹
     */
    public void testQueryTrack() throws Exception{


        QueryTrackReq queryTrackReq = new QueryTrackReq();
        QueryTrackParam queryTrackParam = new QueryTrackParam();
        queryTrackParam.setCom(CompanyConstant.YT);
        queryTrackParam.setNum("YT9383342193097");
        queryTrackParam.setPhone("17725390266");
        String param = new Gson().toJson(queryTrackParam);

        queryTrackReq.setParam(param);
        queryTrackReq.setCustomer(customer);
        queryTrackReq.setSign(SignUtils.querySign(param ,key,customer));
        IBaseClient baseClient = new QueryTrack();

        System.out.println(baseClient.execute(queryTrackReq));
    }

    /**
     * 快递信息地图轨迹
     */
    public void testQueryMapView() throws Exception{

        QueryTrackReq queryTrackReq = new QueryTrackReq();
        QueryTrackParam queryTrackParam = new QueryTrackParam();
        queryTrackParam.setCom(CompanyConstant.YD);
        queryTrackParam.setNum("4311159956248");
        queryTrackParam.setPhone("17725390266");
        queryTrackParam.setFrom("河北保定市");
        queryTrackParam.setTo("湖南岳阳市");
        queryTrackParam.setResultv2("2");
        String param = new Gson().toJson(queryTrackParam);

        queryTrackReq.setParam(param);
        queryTrackReq.setCustomer(customer);
        queryTrackReq.setSign(SignUtils.querySign(param ,key,customer));

        IBaseClient baseClient = new QueryTrackMap();
        HttpResult result = baseClient.execute(queryTrackReq);

        // System.out.println(result);
        QueryTrackMapResp queryTrackMapResp = new Gson().fromJson(result.getBody(),QueryTrackMapResp.class);
        System.out.println("_____________________________________________________________________________"+queryTrackMapResp);
        System.out.println("____________________________________________________________________________________________________________________________________________");
        System.out.println(queryTrackMapResp.getTrailUrl());
        System.out.println(queryTrackMapResp.getArrivalTime());
        System.out.println(queryTrackMapResp.getRemainTime());
        System.out.println(queryTrackMapResp.getTotalTime());
        System.out.println("____________________________________________________________________________________________________________________________________________");

        // System.out.println(queryTrackMapResp);
    }



}
