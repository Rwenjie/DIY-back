package nuc.rwenjie.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author Rwenjie
 * @ClassName ChatMsg
 * @Description TODO 聊天消息
 * @Date 2021/6/1 16:18
 **/

@Data
@EqualsAndHashCode(callSuper = false)
public class ChatMsg implements Serializable {

    private static final long serialVersionUID = 1L;
    private String form;

    private String to;

    private String content;

    private LocalDateTime date;

    private String formNickName;
}
