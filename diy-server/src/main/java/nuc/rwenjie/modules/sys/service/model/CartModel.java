package nuc.rwenjie.modules.sys.service.model;

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
 * @since 2021-05-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Cart对象", description="")
public class CartModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "购物车id")
    private Long id;

    @ApiModelProperty(value = "用户id")
    private Long uid;

    @ApiModelProperty(value = "商品数量")
    private Integer count;

    @ApiModelProperty(value = "金额")
    private Float amount;

    @ApiModelProperty(value = "商品编号")
    private GoodsModel product;

    @ApiModelProperty(value = "sku 编号")
    private SkuModel sku;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;


}
