package nuc.rwenjie.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import io.rong.models.User;
import nuc.rwenjie.common.config.jwt.JwtTokenUtil;
import nuc.rwenjie.common.config.security.DefaultUserDetailsService;
import nuc.rwenjie.common.error.BusinessException;
import nuc.rwenjie.common.error.EmBusinessError;
import nuc.rwenjie.modules.sys.dataobject.UserDO;
import nuc.rwenjie.modules.sys.entity.UserEntity;
import nuc.rwenjie.modules.sys.mapper.UserMapper;
import nuc.rwenjie.modules.sys.service.UserService;
import nuc.rwenjie.modules.sys.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @Author Rwenjie
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Date 2021/3/18 14:01
 **/

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {

    @Autowired
    UserMapper userMapper;

    /**
     * 根据用户ID获得用户
     *
     * @param id
     * @return com.rwenjie.server.pojo.User
     * @Param: username
     */
    @Override
    public UserEntity getUserById(String id) throws BusinessException{
        UserEntity user = userMapper.selectOne(new QueryWrapper<UserEntity>().eq("id", id));
        if (user == null) {
            throw new BusinessException(EmBusinessError.USER_LOGIN_FAIL);
        }
        return user;
    }

    /**
     * 根据用户名获得用户
     *
     * @param username
     * @return com.rwenjie.server.pojo.User
     * @Param: username
     */
    @Override
    public UserEntity getUserByUsername(String username) throws BusinessException {
        UserEntity userDo = userMapper.selectOne(new QueryWrapper<UserEntity>().eq("mobile", username));
        if (userDo == null) {
            throw new BusinessException(EmBusinessError.USER_LOGIN_FAIL);
        }
        return userDo;
    }

    /**
     * 根据电话号码获得用户
     * @param mobile
     * @return com.rwenjie.server.pojo.User
     * @Param: mobile
     */
    @Override
    public UserEntity getUserByMobile(String mobile) throws BusinessException {

        System.out.println("mobile===>"+mobile);
        //UserEntity userEntity = new UserEntity();
        //System.out.println(userMapper.selectUserByMobile(mobile));
        UserEntity userEntity = userMapper.selectOne(new QueryWrapper<UserEntity>().eq("mobile", mobile));
        if (userEntity == null) {
            throw new BusinessException(EmBusinessError.USER_LOGIN_FAIL);
        }
        return userEntity;
    }

    /**
     *  UserModel转换成UserDO
     * @Param: userModel
     * @return nuc.rwenjie.modules.sys.dataobject.UserDO
     **/
    private UserDO convertFromModel(UserModel userModel) {
        if (userModel == null) {
            return null;
        }
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(userModel, userDO);
        userDO.setEncrptPassword(userModel.getPassword());
        return userDO;
    }

    /**
     * UserDO 转换成 UserModel
     * @Param: userDO
     * @return nuc.rwenjie.modules.sys.service.model.UserModel
     **/
    private UserModel convertFromDataObject(UserDO userDO) {
        if (userDO == null) {
            return null;
        }
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userDO, userModel);
        userModel.setPassword(userDO.getEncrptPassword());
        return userModel;
    }
}
