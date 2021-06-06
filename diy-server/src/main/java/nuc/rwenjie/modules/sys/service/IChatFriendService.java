package nuc.rwenjie.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import nuc.rwenjie.modules.sys.dataobject.ChatFriend;
import nuc.rwenjie.modules.sys.entity.ChatFriendEntity;
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
public interface IChatFriendService extends IService<ChatFriendEntity> {

    /**
     * 添加聊天对象
     * @param userId 我的id
     * @param fid 聊天对象的id
     * @return nuc.rwenjie.modules.sys.entity.UserEntity
     **/
    int addChatFriend(String userId, String fid);

    /**
     * 查询聊天对象
     * @param userEntity
     * @return java.util.List<nuc.rwenjie.modules.sys.entity.UserFollowerEntity>
     **/
    List<ChatFriendEntity> getChatFriend(UserEntity userEntity);
}
