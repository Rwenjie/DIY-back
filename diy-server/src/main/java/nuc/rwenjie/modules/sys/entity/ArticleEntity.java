package nuc.rwenjie.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 * @Description TODO 文章实体类
 * @author Rwenjie
 * @since 2021-05-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_article")
@ApiModel(value="Article对象", description="")
public class ArticleEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "正文")
    private String text;

    @ApiModelProperty(value = "文章分类")
    private Long categoryId;

    @ApiModelProperty(value = "文章分类")
    private Long[] subCategory;

    @ApiModelProperty(value = "首页图片")
    private String image;

    @ApiModelProperty(value = "文章标签")
    private String tags;

    @ApiModelProperty(value = "浏览次数")
    private Long look;

    @ApiModelProperty(value = "点赞")
    private Long star;

    @ApiModelProperty(value = "摘要")
    private String brief;

    private Date createTime;

    private Date updateTime;


}
