package nuc.rwenjie.modules.sys.dataobject;

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
@TableName("tb_cart")
@ApiModel(value="Cart对象", description="")
public class CartDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "购物车id")
    private Long id;

    @ApiModelProperty(value = "用户id")
    private String uid;

    @ApiModelProperty(value = "商品数量")
    private Integer count;

    @ApiModelProperty(value = "金额")
    private Float amount;

    @ApiModelProperty(value = "商品编号")
    private Long goodsId;

    @ApiModelProperty(value = "sku 编号")
    private Long skuId;

    @ApiModelProperty(value = "sku 编号")
    private Long articleId;

    @ApiModelProperty(value = "创建时间")
    private String createTime;


}
