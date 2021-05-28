package nuc.rwenjie.modules.sys.controller;

import io.swagger.annotations.ApiOperation;
import nuc.rwenjie.common.utils.RespBean;
import nuc.rwenjie.common.utils.ali.oss.AliOSSFiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
import java.security.Principal;

/**
 * @Author Rwenjie
 * @ClassName FIleController
 * @Description TODO 文件上传
 * @Date 2021/4/25 22:18
 **/

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    AliOSSFiles aliOSSFiles;

    @ApiOperation(value = "上传图片")
    @PostMapping("/import")
    public RespBean importData(MultipartFile file, Principal principal) throws IOException {

        System.out.println();
        System.out.println("开始文件上传");
        String username = principal.getName();
        String filetype = "image";
        String url = aliOSSFiles.uploadFile(username, filetype, file);
        if (url!="") {
            return RespBean.success("上传成功!", url);
        } else {
            return RespBean.error("上传失败！");
        }

        //return RespBean.success("上传成功!", "文件名");
    }
}
