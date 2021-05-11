package nuc.rwenjie.common.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Rwenjie
 * @ClassName RespBean
 * @Description TODO 公共返回对象
 * @Date 2021/3/18 13:41
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespBean {
    private int code;
    private String message;
    private Object data;

    /**
     * 成功返回结果
     * @Param: message
     * @Param: obj
     * @return com.rwenjie.server.pojo.RespBean
     **/
    public static RespBean success(String message, Object data) {
        return new RespBean(200, message, data);
    }

    public static RespBean success(String message) {
        return new RespBean(200, message, null);
    }
    public static RespBean success(Object data) {
        return new RespBean(200, "请求成功", data);
    }


    /**
     * 失败返回结果
     * @Param: message
     * @return com.rwenjie.server.pojo.RespBean
     **/
    public static RespBean error(String message) {
        return new RespBean(500, message, null);
    }

    /**
     * 失败返回结果
     * @Param: message
     * @Param: obj
     * @return com.rwenjie.server.pojo.RespBean
     **/
    public static RespBean error(String message, Object data) {
        return new RespBean(500, message, data);
    }

    /**
     * 失败返回结果
     * @Param: message
     * @Param: obj
     * @return com.rwenjie.server.pojo.RespBean
     **/
    public static RespBean error(int code, String message) {
        return new RespBean(code, message, null);
    }

}
