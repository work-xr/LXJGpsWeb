package com.sky.xljgps.utils;

public class Constant {
    public static final int ERROR_CODE_URL_ADDRESS_ERROR = 1000;
    public static final int ERROR_CODE_SAVE_GPS = 1001;
    public static final int ERROR_CODE_SAVE_PHONE = 1002;
    public static final int ERROR_CODE_FIND_LASTEST_PHONE = 1003;

    public static final String URL_DATA_DECODE_TYPE = "UTF-8";
    /* 客户端和服务器端共同持有的公钥,用于对称加密,验证数据一致性 */
    public static final String SIGN_SECRET_CODE = "iETOECQ9kiJw75AZ";//ab2342145acdf;    // iETOECQ9kiJw75AZ

    /* 需要通知客户端的信息, 通用参数信息 */
    public static final String PARAM_MANUFACTORY = "manufactory";
    public static final String PARAM_MODEL = "model";
    public static final String PARAM_COMPANY = "company";
    public static final String PARAM_IMEI = "imei";
    public static final String PARAM_TYPE = "type";
    public static final String PARAM_TIME = "time";
    public static final String PARAM_DATA = "data";
    public static final String PARAM_SIGN = "sign";

    /* 需要通知客户端的信息, GPS专用参数信息 */
    public static final String PARAM_LONGITUDE = "longitude";
    public static final String PARAM_LATITUDE = "latitude";
    public static final String PARAM_POWER = "power";
    public static final String PARAM_POSITION_TYPE = "position_type";
    public static final String PARAM_LOC_TYPE = "loc_type";

    /* 需要通知客户端的信息, 上传亲情号码专用参数信息 */
    public static final String PARAM_NAME = "name";
    public static final String PARAM_SOS_PHONE = "sos_phone";

    /* 需要通知客户端的信息, 服务器端地址和各个功能模块API */
    public static final String API_SERVER_ADDRESS = "http://127.0.0.1:8081/api/";
    public static final String API_SERVER_ADDRESS_TEST = "http://127.0.0.1:8081/api/test/";
    public static final String API_REPORT_POSITION = "position";
    public static final String API_REPORT_SOSPOSITION = "sosposition";
    public static final String API_DOWNLOAD_PHONE_FROM_SERVER = "phone";

    /* 需要通知客户端的信息, 本次执行动作的类型 */
    public static final String API_TYPE_DOWNLOAD = "001";   // 下载数据
    public static final String API_TYPE_UPLOAD = "002";     // 上传数据
    public static final String API_TYPE_REPORT = "003";     // 上报数据
    public static final String API_TYPE_CURRENT = "004";    // 实时定位数据
    public static final String API_TYPE_POWERON = "005";    // 开机定位
    public static final String API_TYPE_TIMING = "006";     // 定时定位数据

    /* 需要通知客户端的信息, GPS定位方式 */
    public static final String API_TGPS_LOCATION_TYPE_GPS = "gps";
    public static final String API_TGPS_LOCATION_TYPE_LBS = "lbs";
    public static final String API_TGPS_LOCATION_TYPE_WIFI = "wifi";
}
