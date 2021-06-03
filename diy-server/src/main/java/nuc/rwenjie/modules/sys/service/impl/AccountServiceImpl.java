package nuc.rwenjie.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import nuc.rwenjie.common.error.BusinessException;
import nuc.rwenjie.common.error.EmBusinessError;
import nuc.rwenjie.common.utils.RespBean;
import nuc.rwenjie.modules.sys.dataobject.UserDO;
import nuc.rwenjie.modules.sys.entity.UserEntity;
import nuc.rwenjie.modules.sys.mapper.UserMapper;
import nuc.rwenjie.modules.sys.service.AccountService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @Author Rwenjie
 * @ClassName AccountServiceImpl
 * @Description TODO 账号相关
 * @Date 2021/5/7 10:09
 **/

@Service
public class AccountServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements AccountService{

    @Autowired
    UserMapper userMapper;
    @Autowired
    PasswordEncoder passwordEncoder;

    /**
     * 验证密码是否正确
     * @param password
     * @param mobile
     * @return nuc.rwenjie.common.utils.RespBean
     * @Param: password
     * @Param: username
     */
    @Override
    public RespBean checkOldPwd(String password, String mobile) throws BusinessException {
        System.out.println(mobile);
        UserEntity user = userMapper.selectOne(new QueryWrapper<UserEntity>().eq("mobile", mobile));
        System.out.println(user);
        if (user == null) {
            throw new BusinessException(EmBusinessError.USER_LOGIN_FAIL);
        }
        System.out.println("=====>"+user.getPassword().equals(new Sha256Hash(password, user.getSalt()).toHex()));
        if(user.getPassword().equals(new Sha256Hash(password, user.getSalt()).toHex())) {
            return RespBean.success(200, "原密码正确");
        } else {
            return RespBean.error(500, "原密码不正确");
        }
    }

    /**
     * 修改密码
     *
     * @param password
     * @param username
     * @return nuc.rwenjie.common.utils.RespBean
     * @Param: password
     * @Param: name
     */
    @Override
    public RespBean chengPwd(String password, UserEntity user) {
        UserEntity userEntity = userMapper.selectById(user.getUserId());
        //密码加密
        //sha256加密
        String salt = RandomStringUtils.randomAlphanumeric(20);
        userEntity.setPassword(new Sha256Hash(user.getPassword(), salt).toHex());
        userEntity.setSalt(salt);
        System.out.println("changePwd=>>>"+password);
        int row = userMapper.updateById(userEntity);
        if (row==0) {
            return RespBean.error("修改密码失败");
        }
        return RespBean.success("修改成功");
    }
}
