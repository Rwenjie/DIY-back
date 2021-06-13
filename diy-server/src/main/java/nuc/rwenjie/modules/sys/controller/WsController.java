package nuc.rwenjie.modules.sys.controller;

import nuc.rwenjie.modules.sys.entity.ChatMsg;
import nuc.rwenjie.modules.sys.entity.UserEntity;
import nuc.rwenjie.modules.sys.service.IChatFriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * @Author Rwenjie
 * @ClassName WsController
 * @Description TODO WebSocket
 * @Date 2021/6/1 16:24
 **/

@Controller
public class WsController {

    @Autowired
    IChatFriendService chatFriendService;
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;


    @MessageMapping("/ws/chat")
    public void handleMsg(Authentication authentication, ChatMsg chatMsg) {
        UserEntity userEntity = (UserEntity) authentication.getPrincipal();
        chatMsg.setFromNickName(userEntity.getUsername());
        chatMsg.setFrom(userEntity.getUsername());
        chatMsg.setDate(LocalDateTime.now());
        System.out.println("==========================>chatMsg"+chatMsg);
        simpMessagingTemplate.convertAndSendToUser(chatMsg.getTo(), "/queue/chat", chatMsg);
    }

}
