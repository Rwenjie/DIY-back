package nuc.rwenjie.modules.sys.controller.vo;

import lombok.Data;
import nuc.rwenjie.modules.sys.entity.UserEntity;

/**
 * @Author Rwenjie
 * @ClassName ArticleStarVO
 * @Description TODO 用户点赞
 * @Date 2021/5/20 14:36
 **/

@Data
public class ArticleStarVO {

    private ArticleVO article;
    private UserEntity user;
}
