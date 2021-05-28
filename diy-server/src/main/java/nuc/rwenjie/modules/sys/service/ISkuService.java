package nuc.rwenjie.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import nuc.rwenjie.common.utils.RespBean;
import nuc.rwenjie.modules.sys.dataobject.SkuDO;
import nuc.rwenjie.modules.sys.service.model.SkuModel;

import java.util.List;

/**
 * <p>
 * sku表,该表表示具体的商品实体,如黑色的64GB的iphone 8 服务类
 * </p>
 *
 * @author Rwenjie
 * @since 2021-04-28
 */
public interface ISkuService extends IService<SkuDO> {


    /**
     * 插入skus
     * @Param: List<SkuModel> skuModelList list
     * @Param: skuModelList
     * @return nuc.rwenjie.common.utils.RespBean
     **/
    int insertSkus(List<SkuModel> skuModelList);

    /**
     * 根据商品的编号查询对应的sku
     * @return java.util.List<nuc.rwenjie.modules.sys.service.model.SkuModel>
     **/
    List<SkuModel> selectModelsByGoodId(String gid);
    
    /**
     * 根据id查询sku
     * @Param: sid
     * @return nuc.rwenjie.modules.sys.service.model.SkuModel
     **/
    SkuModel getSkuModelBySkuId(Long sid);

}
