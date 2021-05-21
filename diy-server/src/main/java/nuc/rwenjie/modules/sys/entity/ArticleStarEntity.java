package nuc.rwenjie.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author Rwenjie
 * @ClassName ArticleStarEntity
 * @Description TODO 文章点赞
 * @Date 2021/5/20 14:33
 **/

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_user_article_star")
@ApiModel(value="文章点赞", description="")
public class ArticleStarEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String uid;
    private String aid;
    private String createTime;
}
