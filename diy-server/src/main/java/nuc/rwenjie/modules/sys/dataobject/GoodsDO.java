package nuc.rwenjie.modules.sys.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import nuc.rwenjie.modules.sys.service.model.SkuModel;

import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author Rwenjie
 * @ClassName GoodsDO
 * @Description TODO 商品DO
 * @Date 2021/5/10 17:33
 **/


@Data
@TableName(value = "tb_goods")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class GoodsDO implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    private String title;

    private String userId;

    private String articleId;

    private String subTitle;

    private String packingList;

    private String description;

    private String spec;

    private String afterService;

    private String payMethod;

    private String fromAddr;

    private String images;

    private String video;

    private Integer status;

    private Integer soldCount;

    private Integer star;

    private String createTime;

    private String updateTime;


}

