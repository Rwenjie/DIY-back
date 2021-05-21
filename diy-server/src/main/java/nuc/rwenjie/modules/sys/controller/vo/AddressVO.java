package nuc.rwenjie.modules.sys.controller.vo;

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
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 
 * </p>
 *
 * @author Rwenjie
 * @since 2021-05-20
 */
@Data
public class AddressVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String userId;

    private String nickName;

    private String tel;

    private List< Map<String, String> > addrList;

    private String address;

    private Integer number;

    private String defaultAddr;

    private Date deletedAt;

    private Date createdAt;

    private Date updatedAt;




}
