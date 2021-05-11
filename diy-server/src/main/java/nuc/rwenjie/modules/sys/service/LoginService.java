package nuc.rwenjie.modules.sys.service;

import nuc.rwenjie.common.error.BusinessException;
import nuc.rwenjie.common.utils.RespBean;
import nuc.rwenjie.modules.sys.service.model.UserModel;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author Rwenjie
 * @ClassName LoginService
 * @Description TODO
 * @Date 2021/3/24 19:11
 **/


public interface LoginService {

   /**
    * 账号密码 登录之后返回token
     * @Param: username
     * @Param: password
     * @Param: request
     * @return com.rwenjie.server.pojo.RespBean
     **/
    RespBean login(String username, String password) throws BusinessException;;

    /**
            * 短信验证 登录之后返回token
     * @Param: username
     * @Param: password
     * @Param: request
     * @return com.rwenjie.server.pojo.RespBean
     **/
    RespBean smsLogin(String mobile, String smsCode) throws BusinessException;

}
