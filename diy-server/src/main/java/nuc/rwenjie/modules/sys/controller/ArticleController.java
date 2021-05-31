package nuc.rwenjie.modules.sys.controller;


import io.swagger.annotations.ApiOperation;
import nuc.rwenjie.common.utils.RespBean;
import nuc.rwenjie.modules.sys.controller.vo.ArticleVO;
import nuc.rwenjie.modules.sys.entity.ArticleEntity;
import nuc.rwenjie.modules.sys.entity.UserEntity;
import nuc.rwenjie.modules.sys.service.CategoryService;
import nuc.rwenjie.modules.sys.service.IArticleService;
import nuc.rwenjie.modules.sys.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.swing.plaf.SeparatorUI;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Rwenjie
 * @since 2021-05-18
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    IArticleService articleService;
    @Autowired
    CategoryService categoryService;

    @ApiOperation(value = "查询所有文章")
    @GetMapping("/ren/all")
    @Transactional
    public RespBean getAllArticle() {
        System.out.println("获得所有文章");
        List<ArticleEntity> articles =  articleService.getAllArticle();
        List<ArticleVO> articleVOList = new ArrayList<>();
        articles.forEach( article -> {
            ArticleVO articleVO = new ArticleVO();
            BeanUtils.copyProperties(article, articleVO);
            articleVO.setCategory(categoryService.getCategoryById(article.getCategoryId()));
            articleVOList.add(articleVO);
        } );
        if (articles.size() ==0) {
            return RespBean.error(440, "没有查到信息ε=(´ο｀*)))唉！");
        }
        return RespBean.success(articleVOList);
    }

    @ApiOperation(value = "根据ID查询文章")
    @GetMapping("/ren/id/")
    public RespBean getArticleById(String id) {
        System.out.println("id===>"+id);
        ArticleEntity article = articleService.getArticleById(id);
        if (article!=null) {
            return RespBean.success(article);
        } else  {
            return RespBean.error("没有找到文章信息");
        }
    }

    @ApiOperation(value = "发布新的文章")
    @PostMapping("/submit")
    public RespBean submitArticle(@RequestBody ArticleEntity articleEntity,  Authentication authentication) {
        UserEntity userModel = (UserEntity) authentication.getPrincipal();
        if (userModel==null) {
            return RespBean.error("请进行登录");
        }
        articleEntity.setUserId(userModel.getUserId());
        int row = articleService.submitArticle(articleEntity);
        if (row ==1 ){
            return RespBean.success("发布成功");
        }
        else {
            return RespBean.error("发布失败");
        }
    }

    @ApiOperation(value = "查询没有发布商品的文章")
    @GetMapping("/goods/uid")
    public RespBean publicGoodArticle(Authentication authentication) {
        UserEntity userModel = (UserEntity) authentication.getPrincipal();
        if (userModel==null) {
            return RespBean.error("请进行登录");
        }
       List<ArticleEntity> articles = articleService.publicGoodArticle(userModel);
        System.out.println(articles);
        return RespBean.success(articles);
    }
}
