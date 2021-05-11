package nuc.rwenjie.modules.sys.service;


import com.baomidou.mybatisplus.extension.service.IService;
import nuc.rwenjie.common.error.BusinessException;
import nuc.rwenjie.modules.sys.dataobject.UserDO;
import nuc.rwenjie.modules.sys.entity.UserEntity;
import nuc.rwenjie.modules.sys.service.model.UserModel;

/**
 * @Author Rwenjie
 * @ClassName UserService
 * @Description TODO
 * @Date 2021/3/18 14:00
 **/


public interface UserService extends IService<UserEntity> {

    /**
     * 根据用户ID获得用户
     * @Param: username
     * @return com.rwenjie.server.pojo.User
     **/

    public UserEntity getUserById(String id) throws BusinessException;


    /**
     * 根据用户名获得用户
     * @Param: username
     * @return com.rwenjie.server.pojo.User
     **/
    UserEntity getUserByUsername(String username) throws BusinessException;


    /**
     * 根据电话号码获得用户
     * @Param: mobile
     * @return com.rwenjie.server.pojo.User
     **/
    UserEntity getUserByMobile(String mobile) throws BusinessException;
}
