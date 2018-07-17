package com.sky.xljgps.utils;

import com.sky.xljgps.enums.ResultEnum;
import com.sky.xljgps.exception.GpsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;

import static com.sky.xljgps.enums.ResultEnum.PARAM_ERROR;
import static com.sky.xljgps.utils.Constant.URL_DATA_DECODE_TYPE;

/***
*  description:
*  参数解析(已废弃,使用gson进行参数解析)
*  author:  hefeng
*  create:  18-7-11 下午12:30
*/

@Deprecated
public class ParamUtil {
    private static final Logger logger = LoggerFactory.getLogger(VerifySignUtil.class);
    private static int  sParamPosition = 0;

    /***
    *  description:
    *  把客户端发送过来的参数信息进行解析,把参数名和值以key-value的形式返回
    *  author:  hefeng
    *  create:  18-7-11 下午12:30
    *  params:   * @param params
    *  return:  java.util.HashMap<java.lang.String,java.lang.String>
    */
    public static HashMap<String, String> getOneParam(String params) {
        int keyStart = 0;
        int keyEnd = 0;
        int valueStart = 0;
        int valueEnd = 0;
        HashMap<String, String> hashMap = new HashMap<>();
        String key = null;
        String value = null;

        keyStart = params.indexOf("\"", sParamPosition);
        keyEnd = params.indexOf("\"", keyStart + 1);
        valueStart = params.indexOf("\"", keyEnd + 1);
        valueEnd = params.indexOf("\"", valueStart + 1);
        //logger.info("getOneParam .......... keyStart = {}", keyStart);
        //logger.info("getOneParam .......... keyEnd = {}", keyEnd);
        //logger.info("getOneParam .......... valueStart = {}", valueStart);
        //logger.info("getOneParam .......... valueEnd = {}", valueEnd);

        try
        {
            key = params.substring(keyStart + 1, keyEnd);
            value = params.substring(valueStart + 1, valueEnd);
        }
        catch (StringIndexOutOfBoundsException e)
        {
            throw new GpsException(PARAM_ERROR);
        }
        sParamPosition += valueEnd - keyStart + 2;
        logger.info("getOneParam .......... key = {}", key);
        logger.info("getOneParam .......... value = {}", value);

        hashMap.put(key, value);

        return hashMap;
    }

    public static void resetParamPosition()
    {
        sParamPosition = 0;
    }
}
