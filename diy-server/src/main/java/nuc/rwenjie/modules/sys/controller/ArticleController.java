package nuc.rwenjie.modules.sys.controller;


import io.swagger.annotations.ApiOperation;
import nuc.rwenjie.common.utils.RespBean;
import nuc.rwenjie.modules.sys.controller.vo.ArticleVO;
import nuc.rwenjie.modules.sys.entity.ArticleEntity;
import nuc.rwenjie.modules.sys.service.CategoryService;
import nuc.rwenjie.modules.sys.service.IArticleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/all")
    @Transactional
    public RespBean getAllArticle() {
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
    @GetMapping("/id")
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
    public RespBean submitArticle(@RequestBody ArticleEntity articleEntity) {

        s
        return RespBean.success();
    }

}
