package nuc.rwenjie.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

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
public class ChatFriendEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String mid;

    private String fid;

    private String createTime;


}
