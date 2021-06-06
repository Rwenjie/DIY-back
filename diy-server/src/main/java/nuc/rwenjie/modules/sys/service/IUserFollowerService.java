package nuc.rwenjie.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import nuc.rwenjie.modules.sys.entity.UserEntity;
import nuc.rwenjie.modules.sys.entity.UserFollowerEntity;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Rwenjie
 * @since 2021-06-06
 */
public interface IUserFollowerService extends IService<UserFollowerEntity> {



    /**
     * 关注的状态
     * @param fuid 被关注的ID
     * @param userId 我的id
     * @return int
     **/
    int getFollowState(String fuid, String userId);

    /**
     * 取消关注
     * @param fuid 被关注的ID
     * @param userId 我的id
     * @return int
     **/
    int cancelFollow(String fuid, String userId);

    /**
     * 关注用户
     * @param fuid 被关注的ID
     * @param userId 我的id
     * @return int
     **/
    int followUser(String fuid, String userId);
}
