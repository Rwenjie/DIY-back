package nuc.rwenjie.modules.sys.service;

import nuc.rwenjie.modules.sys.entity.UserEntity;
import nuc.rwenjie.modules.sys.service.model.UserModel;

/**
 * @Author Rwenjie
 * @ClassName RegisterService
 * @Description TODO
 * @Date 2021/3/20 21:21
 **/


public interface RegisterService {

    int userRegister(UserEntity user);

    int findByPhone(String phone);

    int findByUsername(String username);
}
