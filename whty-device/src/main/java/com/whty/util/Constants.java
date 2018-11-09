package com.whty.util;

public interface Constants {

    /**
     * 开机状态0表示开机，1表示关机
     */
    public static final Integer DEVICE_STATUS_OPEN = 0;
    /**
     * 开机状态0表示开机，1表示关机
     */
    public static final Integer DEVICE_STATUS_CLOSE = 1;
    /**
     * 一体机ProductKey
     * 正式：a12o3MUDtnf
     * 测试：a1dMRuabwK9
     */
    public static final String AIO_PRODUCT_KEY = "a12o3MUDtnf";
    /**
     *平板ProductKey
     * 正式：a1zTLhHC2cL
     * 测试：a1rPQHVWDju
     */
    public static final String EBOOK_PRODUCT_KEY = "a1zTLhHC2cL";

    /**
     * 主题
     * 正式：IOT_MQ_ZS_GW
     * 测试：IOT_MQ_GW2
     */
    public static final  String TOPIC = "IOT_MQ_ZS_GW";

    /**
     * 消费者
     * 正式：CID_IOT_MQ_ZS_GW
     * 测试：CID_IOT_MQ_GW
     */
    public static final  String ConsumerId = "CID_IOT_MQ_ZS_GW";
}
