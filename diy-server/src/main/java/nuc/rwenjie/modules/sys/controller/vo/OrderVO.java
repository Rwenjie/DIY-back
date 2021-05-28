package nuc.rwenjie.modules.sys.controller.vo;

import lombok.Data;
import nuc.rwenjie.modules.sys.entity.OrderEntity;
import nuc.rwenjie.modules.sys.service.model.OrderDetailModel;

import java.util.List;

/**
 * @Author Rwenjie
 * @ClassName OrderVo
 * @Description TODO 返回订单
 * @Date 2021/5/28 14:19
 **/

@Data
public class OrderVO {

    private OrderEntity order;
    private List<OrderDetailModel> orderDetailList;
}
