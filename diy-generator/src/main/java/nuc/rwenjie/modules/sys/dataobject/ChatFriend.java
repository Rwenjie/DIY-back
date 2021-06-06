package nuc.rwenjie.modules.sys.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author Rwenjie
 * @since 2021-06-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_chat_friend")
@ApiModel(value="ChatFriend对象", description="")
public class ChatFriend implements Serializable {

    private static final long serialVersionUID = 1L;

    private String mid;

    private String fid;

    private LocalDateTime createTime;


}
