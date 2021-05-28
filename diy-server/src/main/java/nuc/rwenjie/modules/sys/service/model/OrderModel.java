package nuc.rwenjie.modules.sys.service.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author Rwenjie
 * @since 2021-05-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_order")
@ApiModel(value="Order对象", description="")
public class OrderModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "客户编号")
    private Integer buyerId;

    @ApiModelProperty(value = "商户编码")
    private Integer sellerId;

    @ApiModelProperty(value = "订单状态 0未付款,1已付款,2已发货,3已签收,-1退货申请,-2退货中,-3已退货,-4取消交易")
    private Integer orderStatus;

    @ApiModelProperty(value = "用户售后状态 0 未发起售后 1 申请售后 -1 售后已取消 2 处理中 200 处理完毕")
    private Integer afterStatus;

    @ApiModelProperty(value = "商品总价")
    private BigDecimal productAmountTotal;

    @ApiModelProperty(value = "运费金额")
    private BigDecimal logisticsFee;

    @ApiModelProperty(value = "收货地址编码")
    private Long addressId;

    @ApiModelProperty(value = "支付渠道 0余额 1微信 2支付宝")
    private Integer payChannel;

    @ApiModelProperty(value = "订单支付单号")
    private String outTradeNo;

    @ApiModelProperty(value = "第三方支付流水号")
    private String escrowTradeNo;

    @ApiModelProperty(value = "付款时间")
    private LocalDateTime payTime;

    @ApiModelProperty(value = "发货时间")
    private LocalDateTime deliveryTime;

    @ApiModelProperty(value = "快递单号")
    private String expressId;

    @ApiModelProperty(value = "订单结算状态 0未结算 1已结算")
    private Integer orderSettlementStatus;

    @ApiModelProperty(value = "订单结算时间")
    private Integer orderSettlementTime;

    private LocalDateTime createdTime;

    private LocalDateTime updatedTime;

    private LocalDateTime deletedTime;


}
