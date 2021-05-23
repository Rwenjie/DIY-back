package nuc.rwenjie.modules.sys.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Rwenjie
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_comment")
@ApiModel(value="Article对象", description="")
public class CommentEntity {

    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    @ApiModelProperty(value = "评论内容")
    private String content;

    @ApiModelProperty(value = "评论时间")
    private String commentTime;

    @ApiModelProperty(value = "点赞数量")
    private Integer likeCounts;

    @ApiModelProperty(value = "评论状态")
    private Integer state;

    @ApiModelProperty(value = "发表人")
    private UserEntity reviewerUser;

    @ApiModelProperty(value = "回复人")
    private UserEntity replyUser;

    @ApiModelProperty(value = "文章id")
    private String articleId;

    @ApiModelProperty(value = "父评论id")
    private String parentId;

}
