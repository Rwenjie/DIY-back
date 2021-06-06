package nuc.rwenjie.modules.sys.controller;


import io.swagger.annotations.ApiOperation;
import nuc.rwenjie.common.utils.RespBean;
import nuc.rwenjie.modules.sys.entity.UserEntity;
import nuc.rwenjie.modules.sys.service.IUserFollowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Rwenjie
 * @since 2021-06-06
 */
@RestController
@RequestMapping("/user")
public class UserFollowerController {

    @Autowired
    IUserFollowerService userFollowerService;

    @ApiOperation(value = "关注用户")
    @GetMapping("/follow")
    public RespBean followUser(String fuid, Authentication authentication) {
        UserEntity userModel = (UserEntity) authentication.getPrincipal();
        if (userModel==null) {
            return RespBean.error("请进行登录");
        }
        int row = userFollowerService.followUser(fuid, userModel.getUserId());
        if (row == 1) {
            return RespBean.success("关注成功");
        } else {
            return RespBean.error("关注失败");
        }
    }

    @ApiOperation(value = "取消关注用户")
    @GetMapping("/follow/cancel")
    public RespBean cancelFollow(String fuid, Authentication authentication) {
        UserEntity userModel = (UserEntity) authentication.getPrincipal();
        if (userModel==null) {
            return RespBean.error("请进行登录");
        }
        int row = userFollowerService.cancelFollow(fuid, userModel.getUserId());
        System.out.println("row=====>"+row);
        if (row == 1) {
            return RespBean.success("取消关注成功");
        } else {
            return RespBean.error("取消关注失败");
        }

    }

    @ApiOperation(value = "查询关注状态")
    @GetMapping("/follow/state")
    public RespBean getFollowState(String fuid, Authentication authentication) {
        UserEntity userModel = (UserEntity) authentication.getPrincipal();
        if (userModel==null) {
            return RespBean.error("请进行登录");
        }
        int row = userFollowerService.getFollowState(fuid, userModel.getUserId());
        if (row == 1) {
            return RespBean.success(true);
        } else {
            return RespBean.success(false);
        }
    }
}
