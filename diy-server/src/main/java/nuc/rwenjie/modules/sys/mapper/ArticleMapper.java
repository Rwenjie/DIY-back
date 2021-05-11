package nuc.rwenjie.modules.sys.mapper;

import nuc.rwenjie.modules.sys.service.model.ArticleModel;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author Rwenjie
 * @ClassName ArticleMapper
 * @Description TODO 查询文章相关
 * @Date 2021/5/11 19:49
 **/


@Mapper
public interface ArticleMapper {

   ArticleModel getArticleModelById(String id);
}
