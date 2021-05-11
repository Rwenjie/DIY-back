package nuc.rwenjie.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import nuc.rwenjie.common.utils.RespBean;
import nuc.rwenjie.common.utils.SpringBeanFactoryUtils;
import nuc.rwenjie.common.utils.Time;
import nuc.rwenjie.modules.sys.controller.vo.PayMethod;
import nuc.rwenjie.modules.sys.dataobject.GoodsDO;
import nuc.rwenjie.modules.sys.dataobject.SkuDO;
import nuc.rwenjie.modules.sys.mapper.ArticleMapper;
import nuc.rwenjie.modules.sys.mapper.GoodsMapper;
import nuc.rwenjie.modules.sys.mapper.SkuMapper;
import nuc.rwenjie.modules.sys.service.IGoodsService;
import nuc.rwenjie.modules.sys.service.ISkuService;
import nuc.rwenjie.modules.sys.service.model.GoodsModel;
import nuc.rwenjie.modules.sys.service.model.SkuModel;
import org.codehaus.jettison.json.JSONArray;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * spu表，该表描述的是一个抽象的商品，比如 手机 服务实现类
 * </p>
 *
 * @author Rwenjie
 * @since 2021-05-10
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, GoodsDO> implements IGoodsService {

    @Autowired
    GoodsMapper goodsMapper;

    @Autowired
    SkuMapper skuMapper;

    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    ISkuService skuService;


    /**
     * 插入商品
     * @Param: goodsModel
     * @return java.lang.Long
     */
    @Override
    public RespBean insertGoods(GoodsModel goodsModel, String id) {

        System.out.println("Service================>"+goodsModel);
        List<SkuModel> skuModelList = goodsModel.getSkus();
        System.out.println(goodsModel.toString());
        GoodsDO goodsDO = convertFromDataObject(goodsModel);
        goodsDO.setStatus(1);
        goodsDO.setExpressTemplateId(1L);
        goodsDO.setUserId(id);
        goodsDO.setCreateTime(Time.NowTime());
        goodsDO.setUpdateTime(Time.NowTime());


        int res = goodsMapper.insert(goodsDO);
        String gId = goodsDO.getId();
        if (res == 0) {
            return RespBean.error(3001, "发布失败");
        }

        int num = 0;
        for (SkuModel skuModel: skuModelList ) {
            SkuDO skuDO = convertFromDataObject(skuModel);
            skuDO.setGoodsId(gId);
            skuDO.setCreateTime(Time.NowTime());
            skuDO.setUpdateTime(Time.NowTime());
            num += skuMapper.insert(skuDO);
        }
        if (num != skuModelList.size()) {
            return RespBean.error(3001, "发布失败");
        }
        return RespBean.success("发布成功");
    }

    /**
     * 根据用户id查询商品
     *
     * @param uid
     * @return java.util.List<nuc.rwenjie.modules.sys.service.model.GoodsModel>
     * @Param: uid
     */
    @Override
    public List<GoodsModel> selectGoodsByUserId(String uid) {

        List<GoodsModel> goodsModelList = new ArrayList<>();
        List<GoodsDO> goodsDOList = goodsMapper.selectList(new QueryWrapper<GoodsDO>().eq("user_id", uid).ne("status", "-1"));
        for (GoodsDO goodsDO : goodsDOList) {
            GoodsModel goodsModel = convertFromModel(goodsDO);
            goodsModel.setSkus(skuService.selectModelsByGoodId(goodsDO.getId()));
            System.out.println("Article==============>"+articleMapper.getArticleModelById(goodsDO.getArticleId()));
            goodsModel.setArticle(articleMapper.getArticleModelById(goodsDO.getArticleId()));
            goodsModelList.add(goodsModel);
        }
        return goodsModelList;
    }

    /**
     * 查询所有商品
     *
     * @return nuc.rwenjie.common.utils.RespBean
     **/
    @Override
    public List<GoodsModel> selectAll() {
        List<GoodsModel> goodsModelList = new ArrayList<>();
        List<GoodsDO> goodsDOList = goodsMapper.selectList(new QueryWrapper<GoodsDO>().ne("status", "-1"));
        for (GoodsDO goodsDO : goodsDOList) {
            GoodsModel goodsModel = convertFromModel(goodsDO);
            goodsModel.setSkus(skuService.selectModelsByGoodId(goodsDO.getId()));
            goodsModel.setArticle(articleMapper.getArticleModelById(goodsDO.getArticleId()));
            goodsModelList.add(goodsModel);
        }
        return goodsModelList;
    }

    /**
     * 逻辑删除商品
     *
     * @return java.lang.Integer
     **/
    @Override
    public Integer deleteGoods(String gid) {
        GoodsDO goodsDO = goodsMapper.selectById(gid);
        if (goodsDO==null){
            return null;
        }
        goodsDO.setStatus(-1);
        return goodsMapper.updateById(goodsDO);
    }

    /**
     * 类型转换
     * @Param: goodsModel
     * @return nuc.rwenjie.modules.sys.dataobject.GoodsDO
     **/
    private GoodsDO convertFromDataObject(GoodsModel goodsModel){
        if (goodsModel == null){
            return null;
        }
        GoodsDO goodsDO = new GoodsDO();
        BeanUtils.copyProperties(goodsModel, goodsDO);

        goodsDO.setArticleId(goodsModel.getArticle().getId());

        return goodsDO;
    }

    private GoodsModel convertFromModel(GoodsDO goodsDO){
        if (goodsDO == null){
            return null;
        }
        GoodsModel goodsModel = new GoodsModel();
        BeanUtils.copyProperties(goodsDO, goodsModel);

        return goodsModel;
    }

    private SkuDO convertFromDataObject(SkuModel skuModel){
        if (skuModel == null){
            return null;
        }
        SkuDO skuDO = new SkuDO();
        BeanUtils.copyProperties(skuModel, skuDO);
        return skuDO;
    }


}
