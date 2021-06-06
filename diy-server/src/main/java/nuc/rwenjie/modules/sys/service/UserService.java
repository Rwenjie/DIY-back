package nuc.rwenjie.modules.sys.service;


import com.baomidou.mybatisplus.extension.service.IService;
import nuc.rwenjie.common.error.BusinessException;
import nuc.rwenjie.modules.sys.dataobject.UserDO;
import nuc.rwenjie.modules.sys.entity.UserEntity;
import nuc.rwenjie.modules.sys.service.model.UserModel;

import java.util.List;

/**
 * @Author Rwenjie
 * @ClassName UserService
 * @Description TODO
 * @Date 2021/3/18 14:00
 **/


public interface UserService extends IService<UserEntity> {

    /**
     * 根据用户ID获得用户
     *
     * @return com.rwenjie.server.pojo.User
     * @Param: username
     **/

    public UserEntity getUserById(String id) throws BusinessException;


    /**
     * 根据用户名获得用户
     *
     * @return com.rwenjie.server.pojo.User
     * @Param: username
     **/
    UserEntity getUserByUsername(String username) throws BusinessException;


    /**
     * 根据电话号码获得用户
     *
     * @return com.rwenjie.server.pojo.User
     * @Param: mobile
     **/
    UserEntity getUserByMobile(String mobile) throws BusinessException;

    /**
     * 更新用户信息
     *
     * @return nuc.rwenjie.modules.sys.entity.UserEntity
     * @param: user
     **/
    UserEntity updateUserInfo(UserEntity user);

    /**
     * 获得所有操作人
     *
     * @return java.util.List<nuc.rwenjie.modules.sys.entity.UserEntity>
     * @param: keywords
     **/
    List<UserEntity> getAllFriends(String keywords);

    /**
     * 查找关注的用户
     *
     * @param keywords
     * @return java.util.List<nuc.rwenjie.modules.sys.entity.UserEntity>
     **/
    List<UserEntity> getChatFriends(String keywords, UserEntity userEntity);
}

