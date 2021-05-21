package nuc.rwenjie.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @Author Rwenjie
 * @ClassName GoodsStarEntity
 * @Description TODO 收藏商品
 * @Date 2021/5/20 17:28
 **/

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_user_goods_collect")
@ApiModel(value="商品收藏", description="")
public class GoodsStarEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String uid;
    private String gid;
    private String createTime;

}
