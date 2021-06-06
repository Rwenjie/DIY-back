package nuc.rwenjie.modules.sys.controller;

import com.kuaidi100.sdk.contant.CompanyConstant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import nuc.rwenjie.common.utils.RespBean;
import nuc.rwenjie.common.utils.kuaidi100.BaseServiceTest;
import nuc.rwenjie.common.utils.kuaidi100.pojo.ExpressRequest;
import nuc.rwenjie.common.utils.kuaidi100.pojo.ExpressResponse;
import nuc.rwenjie.common.utils.kuaidi100.service.ExpressService;
import nuc.rwenjie.modules.sys.controller.vo.OrderVO;
import nuc.rwenjie.modules.sys.entity.AddressEntity;
import nuc.rwenjie.modules.sys.entity.ExpressEntity;
import nuc.rwenjie.modules.sys.entity.OrderEntity;
import nuc.rwenjie.modules.sys.entity.UserEntity;
import nuc.rwenjie.modules.sys.service.IAddressService;
import nuc.rwenjie.modules.sys.service.IAreaService;
import nuc.rwenjie.modules.sys.service.IExpressService;
import nuc.rwenjie.modules.sys.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @Autowired
    IExpressService iExpressService;

    @Autowired
    IOrderService orderService;

    @Autowired
    IAreaService areaService;
    @Autowired
    IAddressService addressService;


    @ApiOperation(value = "查询快递")
    @GetMapping("/detail")
    public RespBean findExpress(String orderId) {
        System.out.println("查询快递");

        OrderVO orderVO = orderService.getOrderByOid(orderId);
        System.out.println(orderId);
        System.out.println(orderVO);
        ExpressRequest exprRequest = new ExpressRequest();
        exprRequest.setCom(CompanyConstant.YD);
        System.out.println("num" + orderVO.getOrder().getExpressNum());
        exprRequest.setNum(orderVO.getOrder().getExpressNum());

        System.out.println("from" + areaService.getAreaById(Integer.valueOf(orderVO.getOrder().getAddressFrom())).getName());
        exprRequest.setFrom(areaService.getAreaById(Integer.valueOf(orderVO.getOrder().getAddressFrom())).getName());
        AddressEntity addressEntity = addressService.getAddrById(orderVO.getOrder().getAddressTo());
        System.out.println("tell+" + addressEntity.getTel());
        exprRequest.setPhone(addressEntity.getTel());
        System.out.println("to" + areaService.getAreaById(addressEntity.getCity()).getName());
        exprRequest.setTo(areaService.getAreaById(addressEntity.getCity()).getName());

        ExpressResponse exprResponse = expressService.queryMapView(exprRequest);

        System.out.println("controller=>"+exprResponse);


        // exprRequest.setTo(queryTrackMapResp.get);
        return RespBean.success("获得成功",exprResponse);
    }

    @ApiOperation(value = "查询快递")
    @GetMapping("/company")
    public RespBean getAllExpressCom(Authentication authentication) {
        UserEntity userModel = (UserEntity) authentication.getPrincipal();
        if (userModel==null) {
            return RespBean.error(401, "用户未登录");
        }
        List<ExpressEntity> entityList = iExpressService.getAllExpressCom();

        return RespBean.success(entityList);
    }

    @ApiOperation(value = "查询快递")
    @GetMapping("/eid")
    public RespBean getExpressComById(String eid, String num,  Authentication authentication) {
        UserEntity userModel = (UserEntity) authentication.getPrincipal();
        if (userModel==null) {
            return RespBean.error(401, "用户未登录");
        }
        ExpressEntity entityList = iExpressService.getExpressComById(eid);

        return RespBean.success(entityList);
    }
}