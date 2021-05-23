package nuc.rwenjie.modules.sys.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import nuc.rwenjie.common.utils.JSONResult;
import nuc.rwenjie.common.utils.ServiceResult;
import nuc.rwenjie.modules.sys.entity.CommentEntity;
import nuc.rwenjie.modules.sys.entity.UserEntity;
import nuc.rwenjie.modules.sys.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Rwenjie
 */
@RestController
@Api(tags = "CommentController")
@RequestMapping("/comment")
public class CommentController extends BaseController {

    @Resource
    private CommentService commentService;

    @Autowired
    JSONResult result;

    @ApiOperation(value="根据文章查询评论")
    @GetMapping("/selCommentReviewer")
    public JSONResult selCommentReviewer(@RequestParam String articleId, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "8") Integer pageSize) {
        // 获取数据
        return result.ok(commentService.selCommentReviewer(articleId,page,pageSize));
    }

    @ApiOperation(value = "添加新的评论")
    @PostMapping("/insComment")
    public JSONResult insComment(@RequestBody CommentEntity comment, Authentication authentication){
        UserEntity userModel = (UserEntity) authentication.getPrincipal();

        ServiceResult<Boolean> serviceResult = commentService.insComment(comment, userModel);
        return serviceResult.isSuccess() ? result.ok() : result.error(serviceResult.getCode(),serviceResult.getMsg());
    }

}
