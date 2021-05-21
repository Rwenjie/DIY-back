package nuc.rwenjie.modules.sys.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import nuc.rwenjie.common.utils.RespBean;
import nuc.rwenjie.modules.sys.entity.AddressEntity;
import nuc.rwenjie.modules.sys.entity.UserEntity;
import nuc.rwenjie.modules.sys.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Rwenjie
 * @since 2021-05-20
 */
@RestController
@Api(tags = "AddressController")
@RequestMapping("/address")
public class AddressController {

    @Autowired
    IAddressService addressService;

    @ApiOperation(value = "根据用户id查询地址")
    @GetMapping("/user")
    public RespBean getAddressByUser(Authentication authentication){
        UserEntity userModel = (UserEntity) authentication.getPrincipal();
        return RespBean.success(addressService.getAddrByUserId(userModel.getUserId()));
    }

    @Transactional
    @ApiOperation(value = "新增地址")
    @PostMapping("/insert")
    public RespBean insertAddress(@RequestBody AddressEntity address, Authentication authentication) {
        UserEntity userModel = (UserEntity) authentication.getPrincipal();
        if (userModel==null) {
            return RespBean.error(401, "用户未登录！");
        }
        address.setUserId(userModel.getUserId());
        int row = addressService.insertAddress(address);
        if (row == 1) {
            return RespBean.success(201, "添加了新的地址O(∩_∩)O哈哈~", addressService.getAddrByUserId(userModel.getUserId()));
        } else  {
            return RespBean.error(410, "操作失败");
        }
    }

    @Transactional
    @ApiOperation(value = "新增地址")
    @GetMapping("/delete")
    public RespBean deleteAddress(String aid, Authentication authentication) {
        UserEntity userModel = (UserEntity) authentication.getPrincipal();
        if (userModel==null) {
            return RespBean.error(401, "用户未登录！");
        }
        int row = addressService.deleteAddress(aid);
        if (row!=0) {
            return RespBean.success("删除成功");
        }else{
            return RespBean.error("删除失败");
        }

    }

    @Transactional
    @ApiOperation(value = "新增地址")
    @GetMapping("/default")
    public RespBean updateDefault(String aid, Authentication authentication) {
        UserEntity userModel = (UserEntity) authentication.getPrincipal();
        if (userModel==null) {
            return RespBean.error(401, "用户未登录！");
        }
        int row = addressService.updateAddress(aid);
        if (row!=0) {
            return RespBean.success("修改成功");
        }else{
            return RespBean.error("修改失败");
        }
    }

}
