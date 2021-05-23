package nuc.rwenjie.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.gson.Gson;
import nuc.rwenjie.common.utils.RespBean;
import nuc.rwenjie.common.utils.Time;
import nuc.rwenjie.modules.sys.dataobject.GoodsDO;
import nuc.rwenjie.modules.sys.dataobject.SkuDO;
import nuc.rwenjie.modules.sys.entity.AddressEntity;
import nuc.rwenjie.modules.sys.entity.ArticleEntity;
import nuc.rwenjie.modules.sys.mapper.ArticleMapper;
import nuc.rwenjie.modules.sys.mapper.GoodsMapper;
import nuc.rwenjie.modules.sys.mapper.SkuMapper;
import nuc.rwenjie.modules.sys.service.*;
import nuc.rwenjie.modules.sys.service.model.ArticleModel;
import nuc.rwenjie.modules.sys.service.model.GoodsModel;
import nuc.rwenjie.modules.sys.service.model.SkuModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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

    @Autowired
    IGoodsStarService goodsStarService;

    @Autowired
    IArticleService articleService;

    @Autowired
    IAreaService areaService;

    @Autowired
    IAddressService addressService;


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

            ArticleEntity articleEntity = articleMapper.selectById(goodsDO.getArticleId());
            ArticleModel articleModel = new ArticleModel();
            articleModel.setId(articleEntity.getId().toString());
            articleModel.setLabel(articleEntity.getTitle());
            goodsModel.setArticle(articleModel);

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
            ArticleEntity articleEntity = articleMapper.selectById(goodsDO.getArticleId());
            ArticleModel articleModel = new ArticleModel();
            articleModel.setId(articleEntity.getId().toString());
            articleModel.setLabel(articleEntity.getTitle());
            goodsModel.setArticle(articleModel);
            goodsModelList.add(goodsModel);
        }
        System.out.println(goodsModelList);
        return goodsModelList;
    }

    /**
     * 逻辑删除商品
     *
     * @return java.lang.Integer
     **/
    @Override
    public Integer changeStatus(String gid, Integer status) {
        GoodsDO goodsDO = goodsMapper.selectById(gid);
        if (goodsDO==null){
            return null;
        }
        goodsDO.setStatus(status);
        return goodsMapper.updateById(goodsDO);
    }

    /**
     * 根据商品的id查询商品
     *
     * @param gid
     * @return nuc.rwenjie.modules.sys.service.model.GoodsModel
     * @Param: gid
     */
    @Override
    public GoodsModel getGoodsById(String gid) {
        GoodsDO goodsDO = goodsMapper.selectById(gid);
        GoodsModel goodsModel = null;
        if (goodsDO!=null) {
            goodsModel = convertFromModel(goodsDO);
            ArticleModel articleModel = new ArticleModel();
            articleModel.setId(goodsDO.getArticleId());
            articleModel.setLabel(articleService.getArticleById(goodsDO.getArticleId()).getTitle());
            goodsModel.setArticle(articleModel);
        }
        return goodsModel;
    }

    /**
     * 更新商品的点赞数量
     *
     * @param gid
     * @return int
     * @Param: gid
     */
    @Override
    public int updateGoodsStar(String gid) {
        System.out.println("updateGoodsStar====>"+gid);
        Integer count = goodsStarService.getStarCount(gid);
        GoodsModel goodsModel = getGoodsById(gid);
        GoodsDO goodsDO = null;
        if (goodsModel != null) {
            goodsDO = convertFromDataObject(goodsModel);
        }
        goodsDO.setStar(count);
        int row = goodsMapper.update(goodsDO, new QueryWrapper<GoodsDO>().eq("id", gid));
        return 0;
    }

    /**
     * 根据文章查询商品
     *
     * @param aid
     * @return nuc.rwenjie.modules.sys.service.model.GoodsModel
     * @Param: aid
     */
    @Override
    public GoodsModel getItemByArticle(String aid) {
        GoodsDO goodsDO = goodsMapper.selectOne(new QueryWrapper<GoodsDO>()
                .eq("article_id", aid));
        GoodsModel goodsModel = null;
        if (goodsDO!=null) {
            goodsModel = convertFromModel(goodsDO);

/*            List<SkuModel> skus = skuService.selectModelsByGoodId(goodsModel.getId());
            goodsModel.setSkus(skus);
            String[] images = goodsDO.getImages().split(",");
            List<String> list = Arrays.asList(images);

            String[] afterService = goodsDO.getAfterService().split(",");
            List<String> li = Arrays.asList(afterService);

            List<Map<String, String>> fromAddr = areaService.getRootAddr(Integer.valueOf(goodsDO.getFromAddr()));

            goodsModel.setFromAddr(fromAddr);
            goodsModel.setAfterService(li);
            goodsModel.setImages(list);*/
        }
        return goodsModel;
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
        System.out.println("model+++++++++++   "+goodsModel);
        GoodsDO goodsDO = new GoodsDO();
        BeanUtils.copyProperties(goodsModel, goodsDO);
        System.out.println("model+++++++++++   "+goodsDO);
        goodsDO.setArticleId(goodsModel.getArticle().getId());

        return goodsDO;
    }

    private GoodsModel convertFromModel(GoodsDO goodsDO){
        if (goodsDO == null){
            return null;
        }
        GoodsModel goodsModel = new GoodsModel();
        BeanUtils.copyProperties(goodsDO, goodsModel);

        List<SkuModel> skus = skuService.selectModelsByGoodId(goodsModel.getId());
        goodsModel.setSkus(skus);
        String[] images = goodsDO.getImages().split(",");
        List<String> list = Arrays.asList(images);

        String[] afterService = goodsDO.getAfterService().split(",");
        List<String> li = Arrays.asList(afterService);

        List<Map<String, String>> fromAddr = areaService.getRootAddr(Integer.valueOf(goodsDO.getFromAddr()));

        goodsModel.setFromAddr(fromAddr);
        goodsModel.setAfterService(li);
        goodsModel.setImages(list);

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
