package nuc.rwenjie.modules.sys.controller.vo;

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
public class SkuVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long spuId;

    private String name;

    private String title;

    private String images;

    private BigDecimal price;

    private Integer stock;

    private String ownSpec;

    private Integer enable;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
