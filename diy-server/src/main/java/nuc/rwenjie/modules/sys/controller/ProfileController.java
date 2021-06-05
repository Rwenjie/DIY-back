package nuc.rwenjie.modules.sys.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author Rwenjie
 * @ClassName ProfileController
 * @Description TODO Profile
 * @Date 2021/6/5 18:09
 **/

@Controller
public class ProfileController {

    @ApiOperation(value = "alipayReturnNotice")
    @RequestMapping("/profile")
    public String alipayReturnNotice(HttpServletRequest request, HttpServletRequest response) throws Exception {
        System.out.println(request);
        return "";

    }

}
