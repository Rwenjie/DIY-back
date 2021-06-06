package nuc.rwenjie.modules.sys.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import nuc.rwenjie.common.utils.RespBean;
import nuc.rwenjie.modules.sys.entity.UserEntity;
import nuc.rwenjie.modules.sys.service.IChatFriendService;
import nuc.rwenjie.modules.sys.service.IUserFollowerService;
import nuc.rwenjie.modules.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author Rwenjie
 * @ClassName ChatController
 * @Description TODO 聊天
 * @Date 2021/6/1 16:34
 **/

@Api(tags = "CartController")
@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    UserService userService;
    @Autowired
    IUserFollowerService userFollowerService;

    @Autowired
    IChatFriendService chatFriendService;


    @ApiOperation(value = "获得聊天对象")
    @GetMapping("/friends")
    public RespBean getAllFriends(String keywords, Authentication authentication) {
        UserEntity userModel = (UserEntity) authentication.getPrincipal();
        if (userModel==null) {
            return RespBean.error("请进行登录");
        }
        List<UserEntity> userEntities = userService.getChatFriends(keywords, userModel);
        return RespBean.success(userEntities);
    }

    @ApiOperation(value = "添加聊天对象")
    @GetMapping("/friend/add")
    public RespBean addChatFriend(String fid, Authentication authentication) {
        UserEntity userModel = (UserEntity) authentication.getPrincipal();
        if (userModel==null) {
            return RespBean.error("请进行登录");
        }
        int row = chatFriendService.addChatFriend(userModel.getUserId(), fid);
        if (row == 0) {
            return RespBean.success("关注成功");
        } else {
            return RespBean.success("关注失败");
        }
    }




}
