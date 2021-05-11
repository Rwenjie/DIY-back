package nuc.rwenjie.modules.sys.controller;

import com.kuaidi100.sdk.contant.CompanyConstant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import nuc.rwenjie.common.utils.RespBean;
import nuc.rwenjie.common.utils.kuaidi100.BaseServiceTest;
import nuc.rwenjie.common.utils.kuaidi100.pojo.ExpressRequest;
import nuc.rwenjie.common.utils.kuaidi100.pojo.ExpressResponse;
import nuc.rwenjie.common.utils.kuaidi100.service.ExpressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Rwenjie
 * @ClassName ExpressController
 * @Description TODO 快递100
 * @Date 2021/3/20 21:13
 **/

@Api(tags = "ExpressController")
@RestController
@RequestMapping("/express")
public class ExpressController {

    @Autowired
    BaseServiceTest baseServiceTest;

    @Autowired
    ExpressService expressService;


    @ApiOperation(value = "查询快递")
    @GetMapping("/detail")
    public RespBean findExpress() {
        System.out.println("查询快递");

        ExpressRequest exprRequest = new ExpressRequest();
        exprRequest.setCom(CompanyConstant.YD);
        exprRequest.setNum("4311159956248");
        exprRequest.setFrom("河北保定市");
        exprRequest.setPhone("17725390266");
        exprRequest.setTo("湖南岳阳市");

        ExpressResponse exprResponse = expressService.queryMapView(exprRequest);

        System.out.println("controller=>"+exprResponse);


        // exprRequest.setTo(queryTrackMapResp.get);



        return RespBean.success("获得成功",exprResponse);
    }
}