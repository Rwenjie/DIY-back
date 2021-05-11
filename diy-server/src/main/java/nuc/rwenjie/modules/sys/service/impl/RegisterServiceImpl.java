package nuc.rwenjie.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import nuc.rwenjie.modules.sys.dataobject.UserDO;
import nuc.rwenjie.modules.sys.entity.UserEntity;
import nuc.rwenjie.modules.sys.mapper.RegisterMapper;
import nuc.rwenjie.modules.sys.service.AsyncService;
import nuc.rwenjie.modules.sys.service.RegisterService;
import nuc.rwenjie.modules.sys.service.model.UserModel;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @Author Rwenjie
 * @ClassName RegisterServiceImpl
 * @Description TODO 用户注册
 * @Date 2021/3/20 21:29
 **/


@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RegisterMapper registerMapper;
    @Autowired
    AsyncService asyncService;

    @Override
    public int userRegister(UserEntity user){

        System.out.println("开始用户注册");
        //唯一id
        //id 数据库自动生产
        /*String id = UUID.randomUUID().toString();
        userDO.setId(id);*/
        //密码加密
        //sha256加密
        String salt = RandomStringUtils.randomAlphanumeric(20);
        user.setPassword(new Sha256Hash(user.getPassword(), salt).toHex());
        user.setSalt(salt);
        System.out.println("未加密=====》"+user.getPassword());

        //用户注册时间
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = format.format(date);
        System.out.println(dateStr);
        user.setCreateTime(new Date());

       int row =  registerMapper.insert(user);
        return row;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public int findByPhone(String mobile) {
        int phoneNum = registerMapper.selectCount(new QueryWrapper<UserEntity>().eq("mobile", mobile));
        // 异步把数据库中的手机号存入缓存
        asyncService.insUserPhone();
        return phoneNum;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public int findByUsername(String username) {
        int userNum = registerMapper.selectCount(new QueryWrapper<UserEntity>().eq("username", username));
        // 异步把数据库中的用户名存入缓存
        asyncService.insUsername();
        return userNum;
    }
}

