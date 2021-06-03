package nuc.rwenjie.modules.sys.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import nuc.rwenjie.common.utils.RespBean;
import nuc.rwenjie.modules.sys.entity.UserEntity;
import nuc.rwenjie.modules.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @ApiOperation(value = "获得聊天对象")
    @GetMapping("/friends")
    public RespBean getAllFriends(String keywords) {
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++");
        List<UserEntity> userEntities = userService.getAllFriends(keywords);
        return RespBean.success(userEntities);
    }



}
