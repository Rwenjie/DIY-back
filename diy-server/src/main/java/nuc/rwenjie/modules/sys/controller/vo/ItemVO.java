package nuc.rwenjie.modules.sys.controller.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import nuc.rwenjie.modules.sys.entity.AddressEntity;
import nuc.rwenjie.modules.sys.service.model.SkuModel;

import java.io.Serializable;
import java.util.List;

/**
 * @Author Rwenjie
 * @ClassName ItemVO
 * @Description TODO 商品详情页返回数据
 * @Date 2021/5/21 14:45
 **/


@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ItemVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String title;

    private String subTitle;

    private String article;

    private String packingList;

    private String description;

    private String afterService;

    private Integer soldCount;

    private Integer star;

    private String spec;

    private List<PayMethod> payMethod;

    private List<SkuModel> skus;

    private Integer status;

    private List<String> imageList;

    private String mainImage;

    private List<AddressEntity> from;

    private List<AddressEntity> to;



}
