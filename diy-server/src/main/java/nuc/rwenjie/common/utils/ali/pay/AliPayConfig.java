package nuc.rwenjie.common.utils.ali.pay;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AliPayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016102500760474";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCA5IEvQ3fruikPiiqkGaYI2TbqO9vBFZmFQlKkjasABIqV0x+f7lZnyYqHkdzldotCiej4JEaWVsJYHv7qV29e1FSsBJKLw02l57rIg8VcMe5dyqi/t6rMP8mqx6Vk0hpbgTYh6AQcrUs4x4wPGbJWe2VML4bYrCParVPqgxUV8RVpIV7ZCbf/PQq/F5pyptd9lpZ2TINTN4FXI/rwNy5g5c5SnzSNurnkveBH39Gnb8r16RJTcygn0cxKyPA/NWeOtvdYXm7RqQdj0AW+AbAa2uAz0wGovpsZWY5wO7v1wYrGjMQeLNJuand/YGy3RXKbvaEnWtCVfefbP8CIEWHPAgMBAAECggEAUp6mdD5iK1ccJNri9GeAd2GNAt41wfHvg2z8/n/U4eEHWHyJZAHpnwOgacBFAU1YfOFb1VujZ5DbaJMNwfcj63xdfho8OZyKNFNy9j8JDkzvR221d5bqw5wL0A3DZfoLbSZJJbjMkfk/I3k9vRwypZ7ZUtmQLWlCXlUGWmeF55bDCErkdt2mNY0DjCLECQnqKMZl9ZdSXI+3VauoVZEoz7JKhEuyVTk/MO9zTKzkPlgsAfIz9ykEDl6TBlbRoYzDUjXcs/5jL4gqAhxYxsKsHRYcEUUFDeT1KmnABKX74RMt2mykcyB+426gg/iQQ+ZNqRVze5Z92xYr4TesBbmhiQKBgQDT3Aw5fY950Mp55T/L+EqlHYL76cgb60g6S9XWlMH3Aa6QKI6bx/nJ1+SmRVFrsuJieTotSRpSxDBI2+trtG3XW0BIh5f0dyH28i4J0WkELFGIFVGduowxpam2ENP/wDwH27R3B6g+cHuKYRvIUeCe6D2nE2+jqOhrh0FJFAHOYwKBgQCbvz+ROK4zBbCgNny1EtSkY3k9a+aYgvkVUkgEgL5fJE4cr9Aq+oR6LiRSGTl5RYg4VpIIzCJYrKzPJQUdxZPWuezTkzOgEcg93DtTjeZoFVp+PZDP5zzR6v0CkhtR9gQsmWcQf1V3P05NfXkgtZ+DowDBqPIoC6UAQmXAT1v0pQKBgQDMHtwsW/xrqq4aibVQ6VXKBkgYPOOPer5pZH2QNF05hoZQ4LKeByt0vKzRQkIzWPPjWtehoChbjCjVWgZWDE40SvgBqZrPcCTqT+Xpkbjzy1t2mMu9A2plvy5ne4k2hVmRXK5gbKE+H5P3/J4PAExB7pLQMdrDPU9JotRU+5DN7wKBgCjVJTdPouwhi8MVRfC7NSC8K6ccyOInRGKApfz4O/D5tbjzpyH9eAoIWuFjV5whzKrol3TTABY7fzr3u7WofTAIorx9lUVcJCGDwdIQG94h6w252mOHfnySnS61W5BLbaC7vzemCqQu5rqOMNmQhHegnqwnPQWet+2vernGlJc5AoGAOBtC2/p3XGZzolX05R3+lZUwFXTRh0l7OyQecneiB3ssbPh28UvM6EpUGGlDddOVf6lUy1DOXxgfmV8cQzXiHiSP77mHGJ63yB58rDgVTWHotzhIXF8JhMM8nP16adg1BfMCbGTdbquoqrZ7gDkNZ20OsQUGWbagldXyeRRgdcQ=";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgOSBL0N367opD4oqpBmmCNk26jvbwRWZhUJSpI2rAASKldMfn+5WZ8mKh5Hc5XaLQono+CRGllbCWB7+6ldvXtRUrASSi8NNpee6yIPFXDHuXcqov7eqzD/JqselZNIaW4E2IegEHK1LOMeMDxmyVntlTC+G2Kwj2q1T6oMVFfEVaSFe2Qm3/z0KvxeacqbXfZaWdkyDUzeBVyP68DcuYOXOUp80jbq55L3gR9/Rp2/K9ekSU3MoJ9HMSsjwPzVnjrb3WF5u0akHY9AFvgGwGtrgM9MBqL6bGVmOcDu79cGKxozEHizSbmp3f2Bst0Vym72hJ1rQlX3n2z/AiBFhzwIDAQAB";


    //支付宝服务器异步通知页面路径,付款完毕后会异步调用本项目的方法,必须为公网地址
    public final static String NOTIFY_URL = "http://localhost:8088/ali/return_url";
    //支付宝同步通知路径,也就是当付款完毕后跳转本项目的页面,可以不是公网地址
    public final static String RETURN_URL = "http://localhost:8088/ali/pay_success";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关 沙箱环境
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

