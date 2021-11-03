package com.travel.travel.config;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AlipayConfig {
    public static String app_id = "1234";
    // [沙箱环境]商户私钥，你的PKCS8格式RSA2私钥
    public static String merchant_private_key ="";
    // [沙箱环境]支付宝公钥
    public static String alipay_public_key ="";

    // [沙箱环境]服务器异步通知页面路径
    public static String notify_url="";
    // [沙箱环境]页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url ="";

    // [沙箱环境]
    public static String gatewayUrl ="";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";
    @Bean
    public AlipayClient initAlipayClient(){

        return new DefaultAlipayClient("https://openapi.alipaydev.com/gateway.do",
                app_id,merchant_private_key,
                "JSON","utf-8",
                alipay_public_key,"RSA2");
    }
}
