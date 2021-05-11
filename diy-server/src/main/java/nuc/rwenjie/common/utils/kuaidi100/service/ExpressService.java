package nuc.rwenjie.common.utils.kuaidi100.service;

import com.google.gson.Gson;
import com.kuaidi100.sdk.api.QueryTrackMap;
import com.kuaidi100.sdk.contant.CompanyConstant;
import com.kuaidi100.sdk.core.IBaseClient;
import com.kuaidi100.sdk.pojo.HttpResult;
import com.kuaidi100.sdk.request.QueryTrackParam;
import com.kuaidi100.sdk.request.QueryTrackReq;
import com.kuaidi100.sdk.response.QueryTrackMapResp;
import com.kuaidi100.sdk.utils.SignUtils;
import nuc.rwenjie.common.utils.kuaidi100.pojo.ExpressRequest;
import nuc.rwenjie.common.utils.kuaidi100.pojo.ExpressResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @Author Rwenjie
 * @ClassName ExpressService
 * @Description TODO 快递
 * @Date 2021/3/7 18:02
 **/

@Service
public class ExpressService {

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
     * 快递信息地图轨迹
     */
    public ExpressResponse queryMapView(ExpressRequest expRequest) {

        QueryTrackReq queryTrackReq = new QueryTrackReq();

        QueryTrackParam queryTrackParam = new QueryTrackParam();
        queryTrackParam.setCom(expRequest.getCom());
        queryTrackParam.setNum(expRequest.getNum());
        queryTrackParam.setPhone(expRequest.getPhone());
        queryTrackParam.setFrom(expRequest.getFrom());
        queryTrackParam.setTo(expRequest.getTo());
        queryTrackParam.setResultv2("2");
        String param = new Gson().toJson(queryTrackParam);

        queryTrackReq.setParam(param);
        queryTrackReq.setCustomer(customer);
        queryTrackReq.setSign(SignUtils.querySign(param ,key,customer));

        IBaseClient baseClient = new QueryTrackMap();
        HttpResult result = null;
        try {
            result = baseClient.execute(queryTrackReq);
            System.out.println(result.getBody());
        } catch (Exception e) {
            e.printStackTrace();
        }

        QueryTrackMapResp queryTrackMapResp = new Gson().fromJson(result.getBody(), QueryTrackMapResp.class);

        ExpressResponse exprResponse = new ExpressResponse();

        exprResponse.setTrailUrl(queryTrackMapResp.getTrailUrl());
        exprResponse.setList(queryTrackMapResp.getData());
        exprResponse.setArrivalTime(queryTrackMapResp.getArrivalTime());
        exprResponse.setCom(queryTrackMapResp.getCom());
        exprResponse.setIsCheck(queryTrackMapResp.getIscheck());
        exprResponse.setMessage(queryTrackMapResp.getMessage());
        exprResponse.setNu(queryTrackMapResp.getNu());
        exprResponse.setStatus(queryTrackMapResp.getStatus());
        exprResponse.setTotalTime(queryTrackMapResp.getTotalTime());

       return exprResponse;

    }
}
