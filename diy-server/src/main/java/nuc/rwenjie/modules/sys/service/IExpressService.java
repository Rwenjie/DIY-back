package nuc.rwenjie.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import nuc.rwenjie.modules.sys.entity.ExpressEntity;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Rwenjie
 * @since 2021-06-06
 */
public interface IExpressService extends IService<ExpressEntity> {

    /**
     * 查询快递公司
     * @return java.util.List<nuc.rwenjie.modules.sys.entity.ExpressEntity>
     **/
    List<ExpressEntity> getAllExpressCom();

    /**
     * 根据eid 查询快递公司信息
     * @param eid
     * @return nuc.rwenjie.modules.sys.entity.ExpressEntity
     **/
    ExpressEntity getExpressComById(String eid);
}
