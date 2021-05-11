package nuc.rwenjie.modules.sys.service.impl;

import nuc.rwenjie.modules.sys.mapper.ArticleMapper;
import nuc.rwenjie.modules.sys.service.IArticleService;
import nuc.rwenjie.modules.sys.service.model.ArticleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author Rwenjie
 * @ClassName ArticleServiceImpl
 * @Description TODO 文章
 * @Date 2021/5/11 19:47
 **/

@Service
public class ArticleServiceImpl implements IArticleService {

    @Autowired
    ArticleMapper articleMapper;
    /**
     * GoodsModel 需要的文章信息
     *
     * @param id
     * @return nuc.rwenjie.modules.sys.service.model.ArticleModel
     */
    @Override
    public ArticleModel getArticleModelById(String id) {
        ArticleModel articleModel = articleMapper.getArticleModelById(id);
        if (articleModel!=null) {
            return articleModel;
        }
        return null;
    }
}
