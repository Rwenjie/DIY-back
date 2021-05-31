package nuc.rwenjie.modules.sys.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import nuc.rwenjie.common.utils.RespBean;
import nuc.rwenjie.modules.sys.service.ITagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Rwenjie
 * @since 2021-05-22
 */

@Api(tags = "GoodsController")
@RestController
@RequestMapping("/tags")
public class TagsController {


    @Autowired
    ITagsService tagsService;

    @ApiOperation(value = "查询所有标签")
    @GetMapping("/ren/all")
    public RespBean getAllTags() {
        return RespBean.success(tagsService.getAllTags());
    }

    @ApiOperation(value = "根据id查询")
    @GetMapping("/ren/tid")
    public RespBean getTagById(String tid) {
        System.out.println("=============================tid"+tid);
        Long tiid = Long.valueOf(tid);
        return RespBean.success(tagsService.getTagById(tiid));
    }
}
