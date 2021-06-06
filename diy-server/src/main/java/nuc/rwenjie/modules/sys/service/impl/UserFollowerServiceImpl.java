package nuc.rwenjie.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import nuc.rwenjie.common.utils.Time;
import nuc.rwenjie.modules.sys.entity.UserEntity;
import nuc.rwenjie.modules.sys.entity.UserFollowerEntity;
import nuc.rwenjie.modules.sys.mapper.UserFollowerMapper;
import nuc.rwenjie.modules.sys.service.IUserFollowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Rwenjie
 * @since 2021-06-06
 */
@Service
public class UserFollowerServiceImpl extends ServiceImpl<UserFollowerMapper, UserFollowerEntity> implements IUserFollowerService {

    @Autowired
    UserFollowerMapper userFollowerMapper;

    /**
     * 关注的状态
     *
     * @param fuid   被关注的ID
     * @param userId 我的id
     * @return int
     **/
    @Override
    public int getFollowState(String fuid, String userId) {
        UserFollowerEntity userFollowerEntity = userFollowerMapper.selectOne(new QueryWrapper<UserFollowerEntity>()
                .eq("uid", fuid)
                .eq("followerId", userId));
        if (userFollowerEntity!=null) {
            return 1;
        }
        return 0;
    }

    /**
     * 取消关注
     *
     * @param fuid   被关注的ID
     * @param userId 我的id
     * @return int
     **/
    @Override
    public int cancelFollow(String fuid, String userId) {
        return userFollowerMapper.delete(new QueryWrapper<UserFollowerEntity>()
                .eq("uid", fuid)
                .eq("followerId", userId));
    }

    /**
     * 关注用户
     *
     * @param fuid   被关注的ID
     * @param userId 我的id
     * @return int
     **/
    @Override
    public int followUser(String fuid, String userId) {
        System.out.println("++++++++++++++++"+fuid+"+++++++++"+userId);
        UserFollowerEntity userFollowerEntity = new UserFollowerEntity();
        userFollowerEntity.setFollowerId(userId);
        userFollowerEntity.setUid(fuid);
        userFollowerEntity.setCreateTime(Time.NowTime());
        return userFollowerMapper.insert(userFollowerEntity);
    }
}
