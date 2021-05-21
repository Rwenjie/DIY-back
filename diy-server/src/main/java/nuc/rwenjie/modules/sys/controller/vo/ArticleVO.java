package nuc.rwenjie.modules.sys.controller.vo;

import lombok.Data;
import nuc.rwenjie.modules.sys.entity.ArticleEntity;
import nuc.rwenjie.modules.sys.service.model.CategoryModel;

/**
 * @Author Rwenjie
 * @ClassName ArticleVo
 * @Description TODO 文章返回
 * @Date 2021/5/20 9:06
 **/

@Data
public class ArticleVO extends ArticleEntity {

    private CategoryModel category;
}
