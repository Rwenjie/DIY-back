package nuc.rwenjie.modules.sys.service.impl;

import nuc.rwenjie.common.constant.RedisConstant;
import nuc.rwenjie.common.utils.RedisOperator;
import nuc.rwenjie.modules.sys.dataobject.UserDO;
import nuc.rwenjie.modules.sys.entity.UserEntity;
import nuc.rwenjie.modules.sys.mapper.RegisterMapper;
import nuc.rwenjie.modules.sys.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author Rwenjie
 * @ClassName AsyncServiceImpl
 * @Description TODO 异步任务(数据库和redis保持一致)
 * @Date 2021/3/9 21:53
 **/

@Service
public class AsyncServiceImpl implements AsyncService {

    @Autowired
    private RedisOperator redisOperator;

    @Autowired
    private RegisterMapper registerMapper;

    @Async
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public void insUserPhone() {
        List<UserEntity> allUsers = registerMapper.selectList(null);
        for (UserEntity m : allUsers) {
            // 手机号加入缓存
            redisOperator.hset(RedisConstant.USER_PHONE_EXIST, m.getMobile(), 1);
        }
    }

    @Async
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void insUsername() {
        List<UserEntity> allUsers = registerMapper.selectList(null);
        for (UserEntity m : allUsers) {
            // 用户名加入缓存
            redisOperator.hset(RedisConstant.USER_NAME_EXIST, m.getUsername(), 1);
        }
    }
}
