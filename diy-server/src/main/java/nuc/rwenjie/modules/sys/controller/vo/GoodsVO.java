package nuc.rwenjie.modules.sys.controller.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import nuc.rwenjie.modules.sys.service.model.SkuModel;

import java.io.Serializable;
import java.util.List;

/**
 * @Author Rwenjie
 * @ClassName GoodsVO
 * @Description TODO θΏεεε
 * @Date 2021/5/11 10:40
 **/

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class GoodsVO implements Serializable{

    private static final long serialVersionUID = 1L;

    private String id;

    private String title;

    private String subTitle;

    private String article;

    private String packingList;

    private String description;

    private String afterService;

    private List<PayMethod> payMethod;

    private List<SkuModel> skus;

    private Integer status;

    private String images;

    private String mainImage;



}
