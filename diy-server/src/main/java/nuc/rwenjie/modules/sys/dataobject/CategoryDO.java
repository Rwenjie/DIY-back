package nuc.rwenjie.modules.sys.dataobject;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import nuc.rwenjie.modules.sys.service.model.CategoryModel;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author Rwenjie
 * @ClassName CategoryDO
 * @Description TODO 商品分类
 * @Date 2021/4/26 22:11
 **/

@Data
@TableName(value = "tb_category")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CategoryDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId("id")
    private Integer id;

    private Integer parentId;

    private String name;

    private Integer status;

    private Integer sortOrder;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")//后台输出到前台
    private Date createTime;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")//后台输出到前台
    private Date updateTime;

    private List<CategoryModel> subCategories;
}
