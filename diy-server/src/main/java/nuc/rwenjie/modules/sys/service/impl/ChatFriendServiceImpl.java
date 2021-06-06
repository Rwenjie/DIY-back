package nuc.rwenjie.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import nuc.rwenjie.common.utils.Time;
import nuc.rwenjie.modules.sys.dataobject.ChatFriend;
import nuc.rwenjie.modules.sys.entity.ChatFriendEntity;
import nuc.rwenjie.modules.sys.entity.UserEntity;
import nuc.rwenjie.modules.sys.entity.UserFollowerEntity;
import nuc.rwenjie.modules.sys.mapper.ChatFriendMapper;
import nuc.rwenjie.modules.sys.mapper.UserFollowerMapper;
import nuc.rwenjie.modules.sys.service.IChatFriendService;
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
public class ChatFriendServiceImpl extends ServiceImpl<ChatFriendMapper, ChatFriendEntity> implements IChatFriendService {

    @Autowired
    ChatFriendMapper chatFriendMapper;
    /**
     * 添加聊天对象
     *
     * @param userId 我的id
     * @param fid    聊天对象的id
     * @return nuc.rwenjie.modules.sys.entity.UserEntity
     **/
    @Override
    public int addChatFriend(String userId, String fid) {
        ChatFriendEntity chatFriend = new ChatFriendEntity();
        chatFriend.setFid(fid);
        chatFriend.setMid(userId);
        chatFriend.setCreateTime(Time.NowTime());;
        return chatFriendMapper.insert(chatFriend);
    }

    /**
     * 查询关注的用户
     *
     * @param userEntity
     * @return java.util.List<nuc.rwenjie.modules.sys.entity.UserFollowerEntity>
     **/
    @Override
    public List<ChatFriendEntity> getChatFriend(UserEntity userEntity) {
        return chatFriendMapper.selectList(new QueryWrapper<ChatFriendEntity>()
                .eq("mid", userEntity.getUserId()));
    }
}
