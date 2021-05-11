package nuc.rwenjie.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import nuc.rwenjie.modules.sys.dataobject.GoodsDO;
import nuc.rwenjie.modules.sys.entity.ArticleEntity;
import nuc.rwenjie.modules.sys.service.model.ArticleModel;

/**
 * @Author Rwenjie
 * @ClassName IArticleService
 * @Description TODO 文章
 * @Date 2021/5/11 19:43
 **/


public interface IArticleService {

    /**
     * GoodsModel 需要的文章信息
     * @return nuc.rwenjie.modules.sys.service.model.ArticleModel
     **/
    ArticleModel getArticleModelById(String id);

}
