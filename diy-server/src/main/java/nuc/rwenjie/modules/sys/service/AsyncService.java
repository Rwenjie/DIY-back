package nuc.rwenjie.modules.sys.service;

/**
 * @Author Rwenjie
 * @ClassName AsyncService
 * @Description TODO 异步操作(数据库和redis保持一致)
 * @Date 2021/3/20 21:30
 **/


public interface AsyncService {

    /**
     * 存入数据库中手机号
     */
    public void insUserPhone();

    /**
     * 存入数据库中用户名
     */
    void insUsername();
}
