package nuc.rwenjie.modules.sys.dataobject;

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
 * sku表,该表表示具体的商品实体,如黑色的64GB的iphone 8
 * </p>
 *
 * @author Rwenjie
 * @since 2021-04-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_sku")
public class SkuDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String goodsId;

    private String title;

    private String images;

    private BigDecimal price;

    private Integer stock;

    private String ownSpec;

    private Integer enable;

    private String indexes;

    private String createTime;

    private String updateTime;
}
