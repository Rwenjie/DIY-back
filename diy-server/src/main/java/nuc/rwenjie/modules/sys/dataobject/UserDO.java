package nuc.rwenjie.modules.sys.dataobject;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Collection;

/**
 * @Author Rwenjie
 * @ClassName UserModel
 * @Description TODO 用户登录
 * @Date 2021/4/26 22:25
 **/

@Data
@TableName(value = "tb_user")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserDO implements Serializable, UserDetails {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private String id;

    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String encrptPassword;

    @NotBlank(message = "手机号不能为空")
    private String mobile;

    private int status;

    private String createdAt;

    private String updatedAt;

    private String profileUrl;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
