package com.yhcrt.iHealthCloud.util;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.4
 *修改日期：2016-03-08
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 合作身份者ID，签约账号，以2088开头由16位纯数字组成的字符串，查看地址：https://b.alipay.com/order/pidAndKey.htm
	public static String partner = "2088721997575363";
	
	//appId
	public static String seller_id = "2017101609332779";

	//商户的私钥,需要PKCS8格式，RSA公私钥生成：https://doc.open.alipay.com/doc2/detail.htm?spm=a219a.7629140.0.0.nBDxfy&treeId=58&articleId=103242&docType=1
	public static String merchant_private_key = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCyGeBkxeZacHjxpc5+03OM2+I3LXjzbLlw8yWnJjZzvM2YOZVGGEm12CIHApyK0dlivedrHsnPQyyU+YrfjRhwshGMfW6WQNYYBBEpAkSVSYo+A5pxeACykPTJn+bioG7IqFvuOL+2GfWHHTuyOf0Mgh5gCUjBx3LivxQihKjZ1ZrhX6I9rOTj8T1BRJ1eyiwUC7/SWXMhMGYgY0x1pOoxQPxZ3gMVAM7A1WEcuIMBhHijFIuIGf83nEBkClHmlYnEw9oVs7QB4M/OL/KI9fHJDJrN39NFcFWkXPV5JzdZ7TD29xTAZqqu6txMnQWkCPOdseM3c9oxYhYksdIuj3/3AgMBAAECggEBAJi0lzHQfTIOPaT0BZm4s9qSreOhmZMeJeIymJY4rXeO6rkapmLzcD/6kTJxisd8JmgDa+js/Rw4/IC/ddM3pzz5cRKEec1ayjbNReljiGp4I5AIhuPDgNWadEhsUgr+g/6IXEplXftFRsAS0JyAG/gdmO2/CCMeX+MK8rimBiC/F1wl4Q74owrqgyKCG6kFs0lccCw2BB1Q6gWtB0k9hdSpaf6hNzgMdt+L1N6hWoBFWSG74xYcgVR60bFoQXmOLsxNWAR2MGOZOXuMiOQN41WAudzgrdIdP/SHT8abhwUatQP8x/kAz+eiJXMDvq5z4A3z5vlZKFHpZwwkJ9OgJAECgYEA2BDuLMZpFhbY4U2DS/oaoo5WEZsG3UMRZqmytVsCy8xh2ugMzX21BFbsUli/HENLhY36aQg7WPaqkkmmGqXo+LRA4e4EKrxdCknwn1x6epfYEzRPHIccUv5Q9Oe4BmALSAcqs41vnp1kxFq7Sd0mdNVuWuT4Y2CTUHDlduavZ/0CgYEA0wSlqbVAqgwDTVBzegRQpbx0Gzl7L3AaTKCSNS4ljIZ6i2Tv3uEMA6cgzPfrJUcdt7cexyfRIV2JH51Vsm72cSmatnhi51Tsmto7zkmJIsRI3qUbdAig5yjt82JLRBI4iJQEmTo23FulhhAEfua6MTdC//O4inJB+aZVvd6U6AMCgYEAql8nP50wLd/UIkw5tMZBDF8KFRXWItCIvxget4xNNyAxJSkHN13hR8u2yvP5tjKGMYiTpRB9o5fVRkbWuHqMqtePtdc15/DhuIQ3PaHxXguvsOz1WLxdJEAu2/5/iF9hZelozW5ATqUzWPVma0b71PKa8wdLqSj04GA1QD2EoI0CgYEArj9nINU3+XMEAwLM6FPJ2KHeamCz1P9H6ECrmthpTzn41URbQvsotZd7YWXTOawY4QyIkMHTUi7vvThpfHdebyGAzO5qFUYuPsy4l+ZnJFtbJObH3bAGVfuZSeG/7wzwu+8mQPUuOhY/f42YeMXDuEI+xCYYG9Kvohv1L10uLssCgYABIx3kmHD+j8qbozvhZVbDC8mcCcUNEHzQfP2x7lqlFd0NWJx2BMNrnQQmL1wNc8w5BJoFp8T3vMly/om7lQqkxKcfUgdTHoDbNMwA/EwxOmTXACyq2yY9hypUxjWgktMuqeZB6bFAP+rkTOx7EudC58anG8MUpYspjyf6wvWTMw==";
	
	// 支付宝的公钥,查看地址：https://b.alipay.com/order/pidAndKey.htm 
	public static String alipay_public_key  = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAyX9tDlpNVsTnalGX0IXfrufVAUgQth8QXtkZQqw5ky0+Hoy36xWSDKNPFU2dAwv8Sxqt/OzDijTYpcGXfXdGBb5HEPpdubW61wsxT0W55/peKYgf5iuXay0dIn8LbMEO4d6xdOoDrE7Jh7F+WJTNpv2g2ZVmn77wRMIoZKZQjztaDfQU++7o052LimL9uZ+M5iIbrQCs+UGPAej/PzStSrco8FG4x0jHD5/HZPo7d7+0hgodhIEdjUJjnJOvlS09G2XXlo33EZK4+gJuXMTpP5ycxbUIRV0lLtqxhLZku64gtcUBfVTzOVWBmY+aq39hAYDw/YTYKzbF3GX6rdKetwIDAQAB";
	
	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 
	//public static String notify_url = "http://hcnet.ejyhealth.com/h5/pay/notify_url";
	public static String notify_url = "https://healthcloud.ejyhealth.com/api/h5/pay/notify_url";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问   即(付款成功后,页面重定向通知)
	//public static String return_url = "http://hcnet.ejyhealth.com/h5/pay/return_url";
	public static String return_url = "https://healthcloud.ejyhealth.com/api/h5/pay/return_url";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipay.com/gateway.do";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 调试用，创建TXT日志文件夹路径，见AlipayCore.java类中的logResult(String sWord)打印方法。
	public static String log_path = "C:\\";
		
	// 字符编码格式 目前支持 gbk 或 utf-8
	public static String input_charset = "utf-8";
		
	// 支付类型 ，无需修改
	public static String payment_type = "1";
		
	// 调用的接口名，无需修改
	public static String service = "create_direct_pay_by_user";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
	
//↓↓↓↓↓↓↓↓↓↓ 请在这里配置防钓鱼信息，如果没开通防钓鱼功能，为空即可 ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	
	// 防钓鱼时间戳  若要使用请调用类文件submit中的query_timestamp函数
	public static String anti_phishing_key = "";
	
	// 客户端的IP地址 非局域网的外网IP地址，如：221.0.0.1
	public static String exter_invoke_ip = "";
		
//↑↑↑↑↑↑↑↑↑↑请在这里配置防钓鱼信息，如果没开通防钓鱼功能，为空即可 ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

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
