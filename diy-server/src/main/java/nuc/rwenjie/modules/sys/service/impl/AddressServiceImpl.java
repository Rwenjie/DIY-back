package nuc.rwenjie.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import nuc.rwenjie.common.utils.Time;
import nuc.rwenjie.modules.sys.controller.vo.AddressVO;
import nuc.rwenjie.modules.sys.entity.AddressEntity;
import nuc.rwenjie.modules.sys.entity.AreaEntity;
import nuc.rwenjie.modules.sys.mapper.AddressMapper;
import nuc.rwenjie.modules.sys.service.IAddressService;
import nuc.rwenjie.modules.sys.service.IAreaService;
import nuc.rwenjie.modules.sys.service.IArticleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Rwenjie
 * @since 2021-05-20
 */
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, AddressEntity> implements IAddressService {

    @Autowired
    AddressMapper addressMapper;

    @Autowired
    IAreaService areaService;

    /**
     * 根据用户id获得地址列表
     *
     * @param uid
     * @return java.util.List<nuc.rwenjie.modules.sys.controller.vo.AddressVO>
     */
    @Override
    public List<AddressVO> getAddrByUserId(String uid) {
        List<AddressVO> addressVOList = new ArrayList<>();
        List<AddressEntity> addressEntityList = addressMapper.selectList(new QueryWrapper<AddressEntity>()
                .eq("user_id", uid));

        addressEntityList.forEach( addressEntity -> {
            System.out.println("addressEntity================>"+addressEntity);
            AddressVO addressVO = new AddressVO();
            //其他变量
            BeanUtils.copyProperties(addressEntity, addressVO);
            Map<String, String> pmap = new HashMap<>();
            Map<String, String> cmap = new HashMap<>();
            Map<String, String> amap = new HashMap<>();
            //地址
            List<Map<String, String> > addrList = new ArrayList<>();
            //省
            pmap.put("code", addressEntity.getProv().toString());
            AreaEntity area = areaService.getAreaById(addressEntity.getProv());
            pmap.put("label", area.getName());
            addrList.add(pmap);

            //市
            cmap.put("code", addressEntity.getCity().toString());
            area = areaService.getAreaById(addressEntity.getCity());
            cmap.put("label", area.getName());
            addrList.add(cmap);

            //区
            amap.put("code", addressEntity.getArea().toString());
            area = areaService.getAreaById(addressEntity.getArea());
            amap.put("label", area.getName());
            addrList.add(amap);
            addressVO.setAddrList(addrList);
            addressVOList.add(addressVO);
        });
        return addressVOList;
    }

    /**
     * 用户新增地址
     *
     * @param addressEntity
     * @return int
     * @Param: addressEntity
     */
    @Override
    public int insertAddress(AddressEntity addressEntity) {
        if (addressEntity.getDefaultAddr().equals("1")) {
            updateAddress(addressEntity);
        }
        addressEntity.setCreatedAt(new Date());
        addressEntity.setDeletedAt(new Date());
        addressEntity.setUpdatedAt(new Date());
        return addressMapper.insert(addressEntity);
    }

    /**
     * 更新地址信息
     *
     * @param addressEntity
     * @return int
     * @Param: addressEntity
     */
    @Override
    public int updateAddress(AddressEntity addressEntity) {
        List<AddressEntity> addressEntityList = addressMapper.selectList(new QueryWrapper<AddressEntity>()
                .eq("user_id", addressEntity.getUserId()));
        addressEntityList.forEach( address ->  {
            address.setDefaultAddr("0");
            addressMapper.updateById(address);
        });
        return 0;
    }

    @Override
    public int updateAddress(String aid) {
        AddressEntity addressEntity = addressMapper.selectById(aid);
        updateAddress(addressEntity);
        addressEntity.setDefaultAddr("1");
        return addressMapper.updateById(addressEntity);
    }

    /**
     * 删除地址
     *
     * @param aid
     * @return java.lang.String
     * @Param: aid
     */
    @Override
    public int deleteAddress(String aid) {
        return addressMapper.deleteById(aid);
    }

    /**
     * 获得用户的默认地址
     *
     * @param uid
     * @return nuc.rwenjie.modules.sys.entity.AddressEntity
     * @param: uid
     */
    @Override
    public AddressEntity getDefaultOrder(String uid) {
        return addressMapper.selectOne(new QueryWrapper<AddressEntity>()
                .eq("default_addr", "1")
                .eq("user_id", uid));
    }

    /**
     * 根据id 获得
     *
     * @param aid id
     * @return java.lang.String
     **/
    @Override
    public AddressEntity getAddrById(String aid) {
        return addressMapper.selectById(aid);
    }


}
