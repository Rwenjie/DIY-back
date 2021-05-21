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
 *
 * @author Rwenjie
 * @since 2021-05-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_address")
@ApiModel(value="Address对象", description="")
public class AddressEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户编号")
    private String userId;

    @ApiModelProperty(value = "收货人姓名")
    private String nickName;

    @ApiModelProperty(value = "手机号码")
    private String tel;

    @ApiModelProperty(value = "省")
    private Integer prov;

    @ApiModelProperty(value = "市")
    private Integer city;

    @ApiModelProperty(value = "区")
    private Integer area;

    @ApiModelProperty(value = "街道地址")
    private String address;

    @ApiModelProperty(value = "邮政编码")
    private Integer number;

    @ApiModelProperty(value = "默认收货地址 1=>默认")
    private String defaultAddr;

    private Date deletedAt;

    private Date createdAt;

    private Date updatedAt;


}
