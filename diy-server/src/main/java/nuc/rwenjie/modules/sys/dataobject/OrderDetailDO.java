package nuc.rwenjie.modules.sys.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
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
@TableName("tb_order_detail")
@ApiModel(value="OrderDetail对象", description="")
public class OrderDetailDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单详情id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "订单主表")
    private Long orderId;

    @ApiModelProperty(value = "文章")
    private Long articleId;

    @ApiModelProperty(value = "商品")
    private String goodsId;

    @ApiModelProperty(value = "sku ID")
    private Long skuId;

    @ApiModelProperty(value = "单金额")
    private Float amount;

    @ApiModelProperty(value = "数量")
    private Integer count;

    @ApiModelProperty(value = "创建时间")
    private String createTime;


}
