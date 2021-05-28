package nuc.rwenjie.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import nuc.rwenjie.common.utils.RespBean;
import nuc.rwenjie.common.utils.SpringBeanFactoryUtils;
import nuc.rwenjie.modules.sys.dataobject.SkuDO;
import nuc.rwenjie.modules.sys.mapper.SkuMapper;
import nuc.rwenjie.modules.sys.service.ISkuService;
import nuc.rwenjie.modules.sys.service.model.SkuModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * sku表,该表表示具体的商品实体,如黑色的64GB的iphone 8 服务实现类
 * </p>
 *
 * @author Rwenjie
 * @since 2021-04-28
 */
@Service
public class SkuServiceImpl extends ServiceImpl<SkuMapper, SkuDO> implements ISkuService {

    @Autowired
    SkuMapper skuMapper;
    /**
     * 插入skus
     *
     * @param skuModelList
     * @return nuc.rwenjie.common.utils.RespBean
     * @Param: List<SkuModel> skuModelList list
     * @Param: skuModelList
     */
    @Override
    public int insertSkus(List<SkuModel> skuModelList) {
        int num = 0;
        for (SkuModel skuModel: skuModelList ) {
            SkuDO skuDO = convertFromDataObject(skuModel);
            num += skuMapper.insert(skuDO);
        }
        return num;
    }

    /**
     * 根据商品的编号查询对应的sku
     *
     * @return java.util.List<nuc.rwenjie.modules.sys.service.model.SkuModel>
     **/
    @Override
    public List<SkuModel> selectModelsByGoodId(String gId) {
        List<SkuModel> skuModelList = new ArrayList<>();
        List<SkuDO> skuDOList = skuMapper.selectList(new QueryWrapper<SkuDO>().eq("goods_id", gId));
        for (SkuDO skuDO : skuDOList) {
            SkuModel skuModel = convertFromModel(skuDO);
            String[] indexes = skuDO.getIndexes().split("_");
            int[] array = Arrays.asList(indexes).stream().mapToInt(Integer::parseInt).toArray();
            skuModel.setIndexes(array);
            skuModelList.add(skuModel);
        }
        return skuModelList;
    }

    /**
     * 根据id查询sku
     *
     * @param sid
     * @return nuc.rwenjie.modules.sys.service.model.SkuModel
     * @Param: sid
     */
    @Override
    public SkuModel getSkuModelBySkuId(Long sid) {
        List<SkuModel> skuModelList = new ArrayList<>();
        SkuDO skuDO = skuMapper.selectById(sid);
        SkuModel skuModel = convertFromModel(skuDO);
        String[] indexes = skuDO.getIndexes().split("_");
        int[] array = Arrays.asList(indexes).stream().mapToInt(Integer::parseInt).toArray();
        skuModel.setIndexes(array);

        return skuModel;
    }


    private SkuDO convertFromDataObject(SkuModel skuModel){
        if (skuModel == null){
            return null;
        }
        SkuDO skuDO = SpringBeanFactoryUtils.getBean(SkuDO.class);
        BeanUtils.copyProperties(skuModel, skuDO);
        return skuDO;
    }

    private SkuModel convertFromModel(SkuDO skuDO){
        if (skuDO == null){
            return null;
        }
        SkuModel skuModel = new SkuModel();
        BeanUtils.copyProperties(skuDO, skuModel);
        return skuModel;
    }
}
