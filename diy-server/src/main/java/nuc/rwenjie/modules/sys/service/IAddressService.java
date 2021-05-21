package nuc.rwenjie.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import nuc.rwenjie.modules.sys.controller.vo.AddressVO;
import nuc.rwenjie.modules.sys.entity.AddressEntity;

import java.util.List;
import java.util.Map;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Rwenjie
 * @since 2021-05-20
 */
public interface IAddressService extends IService<AddressEntity> {

    /**
     * 根据用户id获得地址列表
     *
     * @return java.util.List<nuc.rwenjie.modules.sys.controller.vo.AddressVO>
     **/
    List<AddressVO> getAddrByUserId(String uid);

    /**
     * 用户新增地址
     *
     * @return int
     * @Param: addressEntity
     **/
    int insertAddress(AddressEntity addressEntity);

    /**
     * 更新地址信息
     *
     * @return int
     * @Param: addressEntity
     **/
    int updateAddress(AddressEntity addressEntity);


    int updateAddress(String aid);
    /**
     * 删除地址
     *
     * @return java.lang.String
     * @Param: aid
     **/
    int deleteAddress(String aid);
}
