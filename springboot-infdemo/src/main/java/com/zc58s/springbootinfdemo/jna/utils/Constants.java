package com.zc58s.springbootinfdemo.jna.utils;

/**
 * @author
 */
public class Constants {
    /**软网关管理员ID，只有该用户能登录软网关系统*/
    public static final String SUPER_ADMIN = "2";
	/**station_id，站点ID*/
    public static final String LI_RESOURCE_TYPE_STATION_ID = "40bf04d6f2e2ec3bfbb43aa51a7fac42";
    /**设备类型，安检机ip*/
    public static final String LI_RESOURCE_TYPE_AJJ_IP = "10.20.72.33";
    /**本地ip*/
    public static final String LI_RESOURCE_AJJ_IP = "127.0.0.1";

    /**安检机、门禁、摄像头、照明灯、声光报警器、炸探、液探、安检门、振动光纤*/
    /**设备类型，安检机*/
    public static final String LI_RESOURCE_TYPE_AJJ = "303310efddb34a2e9bf269bdff8a7dc5";
    /**设备类型，门禁*/
    public static final String LI_RESOURCE_TYPE_MJ = "0a18ad66953647d792e4a28a40050e92";
    /**设备类型，摄像头-枪机/球机*/
    public static final String LI_RESOURCE_TYPE_SXT_QIANGJI = "b6af764f2a6e454490a6b1b3c9057e57";
    public static final String LI_RESOURCE_TYPE_SXT_QIUJI = "ece0b8b2db27411886254e81134988a3";
    /**设备类型，照明灯*/
    public static final String LI_RESOURCE_TYPE_ZMD = "c788ce98c1f248f590434394da485ce4";
    /**设备类型，声光报警器*/
    public static final String LI_RESOURCE_TYPE_SGBJQ = "b45827c068254695864ee3c1d42573cb";
    /**设备类型，炸探*/
    public static final String LI_RESOURCE_TYPE_ZT = "f4c840711eae4bcb9536a890cdfda493";
    /**设备类型，液探*/
    public static final String LI_RESOURCE_TYPE_YT = "b47661ca1d454f9792ba5369f3cb2bc5";
    /**设备类型，安检门*/
    public static final String LI_RESOURCE_TYPE_AJM = "683393c31cf0497bb0f62d457cb1e81f";
    /**设备类型，振动光纤*/
    public static final String LI_RESOURCE_TYPE_ZDGX = "e670524ecb9e4a03b8ddbc7d91a63b1b";

    /**黑名单 设备id*/
    public static final String LI_RESOURCE_ID_HMD = "6d5103e6a4224acf86ae5e65b7a05c52";
    /**黑名单 报警类型id*/
    public static final String LINK_EVENT_ID = "acaeef7ce23649a896b73d2289203204";
    
    /**视频/图片-展示路径前缀目录*/
    public static final String CAMERA_BEFORE_PATH_IMAGE = "/anyRouter/";
    public static final String CAMERA_BEFORE_PATH_VIDEO = "/anyRouter/";
    /**视频/图片存放目录*/
    public static final String CAMERA_VIDEO = "/usr/local/nginx/html/dist/anyRouter/";
    public static final String CAMERA_IMAGE = "/usr/local/nginx/html/dist/anyRouter/";
    /**视频回放临时文件存放目录*/
    public static final String CAMERA_BACK_VIDEO = "/usr/local/nginx/html/backVideo/";
    /**资源点类型（字典项）*/
    public static final String RESOURCE_TYPE = "ddd00fe9c1d149babd9af4606807a437";
    /**子系统（字典项）*/
    public static final String SYSTEM_TYPE = "55045b14859c0d61eff02bc6ff3cb121";
    /**品牌（字典项）*/
    public static final String BRAND_TYPE = "bd7b4a4fa9fc4d6a9f576b80b9d2fd02";
    
    /** 直播\回放,流服务器播放地址 */
    public static final String CAMERAS_PLAY_PATH = "ws://"+LI_RESOURCE_TYPE_AJJ_IP+":8000/live/";
    /** 直播\回放,流服务器推流地址 */
    public static final String CAMERAS_PUSH_PATH = "rtmp://"+LI_RESOURCE_TYPE_AJJ_IP+":1936/live/";

    /**---SDK地址---*/
    public static final String  CAMERA_SDK_PATH = "/usr/local/hcsdk";

    /**---rabbitmq 配置项---*/
    /**报警队列名称*/
    public static final String RABBITMQ_QUEUE_NAME = "alarm_message_";

    /**资源点队列名称*/
    public static final String RABBITMQ_ZYD_QUEUE_NAME = "zyd_message_";

    /**---软网关和其他系统对接接口地址---*/
    /**通知*/
    public static final String HTTP_NOTICE = ":8091/sysStationTakeover/anyRouter";
    /**监测服务状态*/
    public static final String HEART_HTTP_NOTICE = ":8092/eWarning/getControlResultsInfo";


    /** 字符串string=0 */
    public static final String STRING_0 = "0";
    /** 字符串string=1 */
    public static final String STRING_1 = "1";
    /** 字符串string=2 */
    public static final String STRING_2 = "2";
    /** 字符串string=3 */
    public static final String STRING_3 = "3";
    /** 字符串string=4 */
    public static final String STRING_4 = "4";
    /** 字符串string=5 */
    public static final String STRING_5 = "5";
    /** 字符串string=6 */
    public static final String STRING_6 = "6";
    /** 字符串string=7 */
    public static final String STRING_7 = "7";
    /** 字符串string=8 */
    public static final String STRING_8 = "8";
    /** 字符串string=9 */
    public static final String STRING_9 = "9";
    /** 字符串string=01 */
    public static final String STRING_01 = "01";
    /** 字符串string=02 */
    public static final String STRING_02 = "02";
    /** 字符串string=03 */
    public static final String STRING_03 = "03";
    /** 字符串string=04 */
    public static final String STRING_04 = "04";
    /** 字符串string=05 */
    public static final String STRING_05 = "05";
    /** 字符串string=06 */
    public static final String STRING_06 = "06";
    /** 字符串string=07 */
    public static final String STRING_07 = "07";
    /** 字符串string=08 */
    public static final String STRING_08 = "08";
    /** 字符串string=09 */
    public static final String STRING_09 = "09";
    /** 字符串string=10 */
    public static final String STRING_10 = "10";
    /** 字符串string=11 */
    public static final String STRING_11 = "11";
    /** 字符串string=12 */
    public static final String STRING_12 = "12";
    /** 字符串string=13 */
    public static final String STRING_13 = "13";
    /** 字符串string=14 */
    public static final String STRING_14 = "14";
    /** 字符串string=15 */
    public static final String STRING_15 = "15";
    /** 字符串string=16 */
    public static final String STRING_16 = "16";
    /** 字符串string=17 */
    public static final String STRING_17 = "17";
    /** 字符串string=18 */
    public static final String STRING_18 = "18";
    /** 字符串string=19 */
    public static final String STRING_19 = "19";
    /** 字符串string=20 */
    public static final String STRING_20 = "20";

    /** 字符串string=200 */
    public static final String STRING_200 = "200";
    /** 字符串string=1001 */
    public static final String STRING_1001 = "1001";
    /** 字符串string=1002 */
    public static final String STRING_1002 = "1002";




    /** 字符串int=0 */
    public static final int INT_0 = 0;
    /** 整数int=1 */
    public static final int INT_1 = 1;
    /** 整数int=2 */
    public static final int INT_2 = 2;
    /** 整数int=3 */
    public static final int INT_3 = 3;
    /** 整数int=4 */
    public static final int INT_4 = 4;
    /** 整数int=5 */
    public static final int INT_5 = 5;
    /** 整数int=6 */
    public static final int INT_6 = 6;
    /** 整数int=7 */
    public static final int INT_7 = 7;
    /** 整数int=8 */
    public static final int INT_8 = 8;
    /** 整数int=9 */
    public static final int INT_9 = 9;
    /** 整数int=10 */
    public static final int INT_10 = 10;
    /** 整数int=11 */
    public static final int INT_11 = 11;
    /** 整数int=12 */
    public static final int INT_12 = 12;
    /** 整数int=13 */
    public static final int INT_13 = 13;
    /** 整数int=14 */
    public static final int INT_14 = 14;
    /** 整数int=15 */
    public static final int INT_15 = 15;
    /** 整数int=16 */
    public static final int INT_16 = 16;
    /** 整数int=17 */
    public static final int INT_17 = 17;
    /** 整数int=18 */
    public static final int INT_18 = 18;
    /** 整数int=19 */
    public static final int INT_19 = 19;
    /** 整数int=20 */
    public static final int INT_20 = 20;
    /** 整数int=21 */
    public static final int INT_21 = 21;
    /** 整数int=22 */
    public static final int INT_22 = 22;
    /** 整数int=23 */
    public static final int INT_23 = 23;
    /** 整数int=24 */
    public static final int INT_24 = 24;
    /** 整数int=25 */
    public static final int INT_25 = 25;
    /** 整数int=26 */
    public static final int INT_26 = 26;
    /** 整数int=27 */
    public static final int INT_27 = 27;
    /** 整数int=28 */
    public static final int INT_28 = 28;
    /** 整数int=29 */
    public static final int INT_29 = 29;
    /** 整数int=30 */
    public static final int INT_30 = 30;
    /** 整数int=31 */
    public static final int INT_31 = 31;
    /** 整数int=31 */
    public static final int INT_32 = 32;

    /** 整数int=59 */
    public static final int INT_39 = 39;

    /** 整数int=99 */
    public static final int INT_99 = 99;
    /** 整数int=100 */
    public static final int INT_100 = 100;

    /** 整数int=400 */
    public static final int INT_400 = 400;

    /** 整数int=774 */
    public static final int INT_774 = 774;
    /** 整数int=1900 */
    public static final int INT_1900 = 1900;

    /** 整数int=8192 */
    public static final int INT_8192 = 8192;

    /** 整数int=1900 */
    public static final int INT_9999 = 9999;

    /** YYYY */
    public static final String DATE_YYYY = "YYYY";
    /** MM */
    public static final String DATE_MM = "MM";
    /** DD */
    public static final String DATE_DD = "DD";
    /** HH24 */
    public static final String DATE_HH24 = "HH24";
    /** HH12 */
    public static final String DATE_HH12 = "HH12";
    /** HH */
    public static final String DATE_HH = "HH";
    /** MI */
    public static final String DATE_MI = "MI";
    /** SS */
    public static final String DATE_SS = "SS";
    /** blob */
    public static final String DATE_YYYY_SM = "yyyy";
    /** blob */
    public static final String DATE_YY_SM = "yy";
    /** blob */
    public static final String DATE_MM_SM = "mm";
    /** blob */
    public static final String DATE_DD_SM = "dd";
    /** blob */
    public static final String DATE_HH24_SM = "hh24";
    /** blob */
    public static final String DATE_MI_SM = "mi";
    /** blob */
    public static final String DATE_SS_SM = "ss";
    /** blob */
    public static final String DATE_YEAR = "年";
    /** blob */
    public static final String DATE_MONTH = "月";
    /** blob */
    public static final String DATE_DAY = "日";
    /** blob */
    public static final String DATE_HOUR = "时";
    /** blob */
    public static final String DATE_MINUTE = "分";
    /** blob */
    public static final String DATE_SECOND = "秒";
    /** blob */
    public static final String DATE_NULL = "null";
    /** blob */
    public static final String DATE_YEAR_FMT_0000 = "0000-00-00 00:00:00";
    /** blob */
    public static final String DATE_YEAR_FMT_1800 = "1800-01-01 00:00:00";

    /** - */
    public static final String SYMBOL_STROKE = "-";
    /** left_Slash */
    public static final String SYMBOL_LEFT_SLASH = "/";
    /** Space */
    public static final String SYMBOL_SPACE = " ";
    /** colon */
    public static final String SYMBOL_COLON = ":";
    /** , */
    public static final String SYMBOL_COMMA = ",";
    /** semicolon */
    public static final String SYMBOL_SEMICOLON = ";";
    /** @ */
    public static final String SYMBOL_AT = "@";
    /** . */
    public static final String SYMBOL_SPOT = ".";

    /** \t	*/
    public static final String SYMBOL_RIGHT_T = "\t";

    /**
     * 验证码
     */
    public static final String CAPTCHA = "qweqwe";
    /**
     * TTL
     */
    public static final String TTL = "TTL";
    /** limit */
    public static final String LIMIT = "limit";

    /** page */
    public static final String PAGE = "page";

    /** Windows */
    public static final String WINDOWS = "Windows";
    /** TTL= */
    public static final String TTL_EQUALS = "TTL=";
    /** ttl= */
    public static final String TTL_EQUALS_SM = "ttl=";
    /** code */
    public static final String CODE = "code";

    /** sort */
    public static final String SORT = "sort";
    /** resource_type_name */
    public static final String RESOURCE_TYPE_NAME = "resource_type_name";
    /** sub_system */
    public static final String SUB_SYSTEM = "sub_system";
    /** brand_name */
    public static final String BRAND_NAME = "brand_name";
    /** user_name */
    public static final String USER_NAME = "user_name";
    /** soffice.bin */
    public static final String SOFFICE_BIN = "soffice.bin";
    /** 正则表达式匹配数字 */
    public static final String REGEX_NUM = "^\\d+$";

    /** D */
    public static final String D = "D";
    /** M */
    public static final String M = "M";
    /** Y */
    public static final String Y = "Y";
    /** B */
    public static final String B = "B";
    /** K */
    public static final String K = "K";
    /** G */
    public static final String G = "G";

    /**
     * 参数有误
     */
    public static final String CSYW = "参数有误！";
}
