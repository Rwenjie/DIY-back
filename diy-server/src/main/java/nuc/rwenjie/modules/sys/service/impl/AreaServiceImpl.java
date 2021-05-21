package nuc.rwenjie.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.models.auth.In;
import nuc.rwenjie.modules.sys.entity.AreaEntity;
import nuc.rwenjie.modules.sys.mapper.AreaMapper;
import nuc.rwenjie.modules.sys.service.IAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 地址区域表 服务实现类
 * </p>
 *
 * @author Rwenjie
 * @since 2021-05-20
 */
@Service
public class AreaServiceImpl extends ServiceImpl<AreaMapper, AreaEntity> implements IAreaService {

    @Autowired
    AreaMapper areaMapper;

    /**
     * 根据id查询
     *
     * @return nuc.rwenjie.modules.sys.entity.AreaEntity
     **/
    @Override
    public AreaEntity getAreaById(Integer id) {
        return areaMapper.selectById(id);
    }

    /**
     * 查询到根节点
     *
     * @param fromAddr
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.String>>
     * @Param: fromAddr
     */
    @Override
    public List<Map<String, String>> getRootAddr(Integer fromAddr) {
        List<Map<String, String >> list = new ArrayList<>();
        while (fromAddr != 0) {
            Map<String, String> map = new HashMap<>();
            AreaEntity  areaEntity = getAreaById(fromAddr);
            map.put(fromAddr.toString(), areaEntity.getName());
            fromAddr = areaEntity.getParentId();
            list.add(map);

        }
        return list;
    }
}
