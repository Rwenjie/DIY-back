package nuc.rwenjie.modules.sys.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import nuc.rwenjie.common.utils.ali.pay.AliPayConfig;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @Author Rwenjie
 * @ClassName AliPayController
 * @Description TODO 支付宝支付接口
 * @Date 2021/5/30 20:11
 **/

@RestController
@RequestMapping("/ali")
@Api(tags = "AliPayController")
public class AliPayController {

    /**
     * 支付接口
     *
     * @param orderId 订单id
     * @param amount  支付金额
     * @param product 商品名称
     * @param body    商品描述
     * @return
     * @throws AlipayApiException
     */
    @ApiOperation(value = "aliPay")
    @GetMapping("/pay")
    public String aliPay(String data) throws AlipayApiException {

        System.out.println(data);
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AliPayConfig.gatewayUrl,
                AliPayConfig.app_id,
                AliPayConfig.merchant_private_key,
                "json",
                AliPayConfig.charset,
                AliPayConfig.alipay_public_key,
                AliPayConfig.sign_type);
        //  page
        AlipayTradePagePayRequest alipayPageRequest = new AlipayTradePagePayRequest();
        alipayPageRequest.setReturnUrl(AliPayConfig.NOTIFY_URL);
        alipayPageRequest.setNotifyUrl(AliPayConfig.RETURN_URL);


        //拼接参数
        alipayPageRequest.setBizContent(data);
       /* alipayPageRequest.setBizContent("{\"out_trade_no\":\"" + orderId + "\","
                + "\"total_amount\":\"" + amount + "\","
                + "\"subject\":\"" + product + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");*/
        //请求
        return alipayClient.pageExecute(alipayPageRequest).getBody();
    }
    /**
     * 支付宝同步通知页面,成功返回首页
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "alipayReturnNotice")
    @RequestMapping("/return_url")
    public String alipayReturnNotice( HttpServletRequest request, HttpServletRequest response) throws Exception {

        System.out.println("支付成功, 进入同步通知接口...");

        //获取支付宝GET过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        boolean signVerified = AlipaySignature.rsaCheckV1(params, AliPayConfig.alipay_public_key, AliPayConfig.charset, AliPayConfig.sign_type); //调用SDK验证签名
        //——请在这里编写您的程序（以下代码仅作参考）——
        if(signVerified) {
            System.out.println(request);
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //付款金额，这里获取到三个参数就可以了，后面逻辑代码自己创作
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");

            int result=0;
            //这里根据自身的业务写代码，我这里删掉了

            if(result==0){
                System.out.println("resultinfo" + "更新订单失败！请业务员联系后台管理员！" );
            }else {
                System.out.println("resultinfo"+"出租工具成功！");
            }
            System.out.println("********************** 支付成功(支付宝同步通知) **********************");
            System.out.println("* 订单号: {}" + out_trade_no);
            System.out.println("* 支付宝交易号: {}" + trade_no);
            System.out.println("* 实付金额: {}" + total_amount);
            System.out.println("***************************************************************");
        }else {
            System.out.println("支付, 验签失败...");
        }
        return "/business/index";//成功返回首页
    }

    /**
     * 支付宝异步 通知页面
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "alipayNotifyNotice")
    @RequestMapping(value = "/alipayNotifyNotice")
    public String alipayNotifyNotice(HttpServletRequest request, HttpServletRequest response) throws Exception {

        System.out.println("支付成功, 进入异步通知接口...");

        //获取支付宝POST过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        boolean signVerified = AlipaySignature.rsaCheckV1(params, AliPayConfig.alipay_public_key, AliPayConfig.charset, AliPayConfig.sign_type); //调用SDK验证签名
        //——请在这里编写您的程序（以下代码仅作参考）——
        /* 实际验证过程建议商户务必添加以下校验：
        1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
        2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
        3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
        4、验证app_id是否为该商户本身。
        */
        if(signVerified) {//验证成功
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //交易状态
            String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");

            //付款金额 到这里获取到这些信息就可以了，下面的不用看
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");

            if(trade_status.equals("TRADE_FINISHED")){
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序

                //注意： 尚自习的订单没有退款功能, 这个条件判断是进不来的, 所以此处不必写代码
                //退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
            }else if (trade_status.equals("TRADE_SUCCESS")){
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序

                //注意：
                //付款完成后，支付宝系统发送该交易状态通知

                int result=0;
                //这里根据自身的业务写代码，我这里删掉了

                if(result==0){
                    System.out.println("resultinfo" + "更新订单失败！请业务员联系后台管理员！" );
                }else {
                    System.out.println("resultinfo"+"出租工具成功！");
                }


                System.out.println("********************** 支付成功(支付宝同步通知) **********************");
                System.out.println("* 支付宝交易号: {}" + out_trade_no);
                System.out.println("* 订单号: {}" + trade_no);
                System.out.println("* 实付金额: {}" + total_amount);
                System.out.println("***************************************************************");
            }
            System.out.println("支付成功...");
        }else {//验证失败
            System.out.println("支付, 验签失败...");
        }
        return "success";
    }
}
