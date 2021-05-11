package nuc.rwenjie.common.config.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import nuc.rwenjie.common.utils.RespBean;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author Rwenjie
 * @ClassName RestfulAccessDeniedHandler
 * @Description TODO 当访问接口没有权限时，自定义返回结果
 * @Date 2021/3/24 21:28
 **/

@Component
public class RestfulAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException e) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        RespBean bean = RespBean.error("尚未登录，请登录！");
        bean.setCode(403);
        out.write(new ObjectMapper().writeValueAsString(bean));

    }
}
