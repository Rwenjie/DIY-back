package nuc.rwenjie.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import nuc.rwenjie.modules.sys.entity.AreaEntity;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 地址区域表 服务类
 * </p>
 *
 * @author Rwenjie
 * @since 2021-05-20
 */
public interface IAreaService extends IService<AreaEntity> {

    /**
     * 根据id查询
     * @return nuc.rwenjie.modules.sys.entity.AreaEntity
     **/
    AreaEntity getAreaById(Integer id);

    /**
     * 查询到根节点
     * @Param: fromAddr
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.String>>
     **/
    List<Map<String, String> > getRootAddr(Integer fromAddr);
}
