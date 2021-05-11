package nuc.rwenjie.modules.sys.controller;

import nuc.rwenjie.common.error.BusinessException;
import nuc.rwenjie.common.error.EmBusinessError;
import nuc.rwenjie.common.utils.RespBean;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class BaseController {

    public static final String CONTENT_TYPE_FORMED="application/x-www-form-urlencoded";

    /**
     * 定义exceptionhandler解决未被controller层吸收的exception
     * @Param: httpServletRequest
     * @Param: httpServletResponse
     * @Param: ex
     * @return nuc.rwenjie.common.utils.RespBean
     **/
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public RespBean doError(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Exception ex) {
        ex.printStackTrace();
        Map<String,Object> responseData = new HashMap<>();
        if( ex instanceof BusinessException){
            BusinessException businessException = (BusinessException)ex;
            return RespBean.error(businessException.getErrCode(), businessException.getErrMsg());
        }else if(ex instanceof ServletRequestBindingException){
            return RespBean.error(EmBusinessError.UNKNOWN_ERROR.getErrCode(), "url绑定路由问题");
        }else if(ex instanceof NoHandlerFoundException){
            return RespBean.error(EmBusinessError.UNKNOWN_ERROR.getErrCode(), "没有找到对应的访问路径");
        }else{
            return RespBean.error(EmBusinessError.UNKNOWN_ERROR.getErrCode(), EmBusinessError.UNKNOWN_ERROR.getErrMsg());
        }
    }
}