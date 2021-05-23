package nuc.rwenjie.modules.sys.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import nuc.rwenjie.common.error.BusinessException;
import nuc.rwenjie.common.utils.RespBean;
import nuc.rwenjie.modules.sys.entity.UserEntity;

/**
 * @Author Rwenjie
 * @ClassName AccountService
 * @Description TODO
 * @Date 2021/5/7 10:06
 **/


public interface AccountService extends IService<UserEntity> {

    /**
     * 验证密码是否正确
     * @Param: password
     * @Param: username
     * @return nuc.rwenjie.common.utils.RespBean
     **/
    public RespBean checkOldPwd(String password, String username) throws BusinessException;

    /**
     * 修改密码
     * @Param: password
     * @Param: name
     * @return nuc.rwenjie.common.utils.RespBean
     **/
    RespBean chengPwd(String password, String name);
}
