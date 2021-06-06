package nuc.rwenjie.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.annotations.ApiOperation;
import nuc.rwenjie.modules.sys.entity.ExpressEntity;
import nuc.rwenjie.modules.sys.mapper.ExpressMapper;
import nuc.rwenjie.modules.sys.service.IExpressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Rwenjie
 * @since 2021-06-06
 */
@Service
public class ExpressServiceImpl extends ServiceImpl<ExpressMapper, ExpressEntity> implements IExpressService {
    @Autowired
    ExpressMapper expressMapper;

    /**
     * 查询快递公司
     *
     * @return java.util.List<nuc.rwenjie.modules.sys.entity.ExpressEntity>
     **/
    @Override
    public List<ExpressEntity> getAllExpressCom() {
        List<ExpressEntity> entityList = expressMapper.selectList(new QueryWrapper<ExpressEntity>()
                .orderBy(true, true, "sort_no"));
        return entityList;
    }

    /**
     * 根据eid 查询快递公司信息
     *
     * @param eid
     * @return nuc.rwenjie.modules.sys.entity.ExpressEntity
     **/
    @Override
    public ExpressEntity getExpressComById(String eid) {
        return expressMapper.selectById(eid);
    }
}
