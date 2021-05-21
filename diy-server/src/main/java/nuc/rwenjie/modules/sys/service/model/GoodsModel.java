package nuc.rwenjie.modules.sys.service.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import nuc.rwenjie.modules.sys.entity.AddressEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @Author Rwenjie
 * @ClassName GoodsModel
 * @Description TODO 商品
 * @Date 2021/5/8 21:09
 **/

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "GoodsModel", description = "商品")
public class GoodsModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    @ApiModelProperty(value = "标题")
    @JsonProperty("title")
    private String title;

    @ApiModelProperty(value = "商品卖点")
    private String subTitle;

    private String userId;

    @ApiModelProperty(value = "文章编号")
    private ArticleModel article;

    @ApiModelProperty(value = "包装清单")
    private String packingList;

    @ApiModelProperty(value = "商品描述")
    private String description;

    @ApiModelProperty(value = "售后服务")
    private List<String> afterService;

    @ApiModelProperty(value = "支付方式")
    private String payMethod;

    @ApiModelProperty(value = "skus")
    private List<SkuModel> skus;

    @ApiModelProperty(value = "图片")
    private List<String> images;

    private List<Map<String, String>> fromAddr;

    private AddressEntity toAddr;

    @ApiModelProperty(value = "视频")
    private String video;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "卖出数量")
    private Integer soldCount;

    @ApiModelProperty(value = "收藏数量")
    private Integer star;

    private String spec;

    private String createTime;

    private String updateTime;


    @Override
    public String toString() {
        return "GoodsModel{" +
                "title='" + title + '\'' +
                ", subTitle='" + subTitle + '\'' +
                ", articleId=" + article +
                ", packingList='" + packingList + '\'' +
                ", description='" + description + '\'' +
                ", afterService='" + afterService + '\'' +
                ", payMethod='" + payMethod + '\'' +
                ", skus=" + skus +
                ", images='" + images + '\'' +
                '}';
    }
}
