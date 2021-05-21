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
import java.util.List;

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
@ApiModel(value="Sku对象", description="sku表,该表表示具体的商品实体,如黑色的64GB的iphone 8")
public class SkuModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "sku id")
    private Long id;

    @ApiModelProperty(value = "goodsId")
    private String goodsId;

    @ApiModelProperty(value = "商品标题")
    private String title;

    @ApiModelProperty(value = "绑定商品图片")
    private String images;

    @ApiModelProperty(value = "单价,单位-元保留两位小数")
    private BigDecimal price;

    @ApiModelProperty(value = "库存数量")
    private Integer stock;

    @ApiModelProperty(value = "sku的特有规格参数，json格式")
    private String ownSpec;

    @ApiModelProperty(value = "是否有效，0无效，1有效")
    private Integer enable;

    private int[] indexes;

    @ApiModelProperty(value = "选项")
    private List<String> options;

    @ApiModelProperty(value = "添加时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "最后修改时间")
    private LocalDateTime updateTime;

    @Override
    public String toString() {
        return "SkuModel{" +
                "id=" + id +
                ", goodsId=" + goodsId +
                ", title='" + title + '\'' +
                ", images='" + images + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", ownSpec='" + ownSpec + '\'' +
                ", enable=" + enable +
                ", options=" + options +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
