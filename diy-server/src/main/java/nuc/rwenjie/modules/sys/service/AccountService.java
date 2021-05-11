package nuc.rwenjie.modules.sys.service;

import nuc.rwenjie.common.error.BusinessException;
import nuc.rwenjie.common.utils.RespBean;

/**
 * @Author Rwenjie
 * @ClassName AccountService
 * @Description TODO
 * @Date 2021/5/7 10:06
 **/


public interface AccountService {

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
