package com.sky.xljgps.utils;

import com.sky.xljgps.bean.BaseBean;
import com.sky.xljgps.enums.ResultEnum;
import com.sky.xljgps.exception.GpsException;
import com.sky.xljgps.msg.ResultMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import static com.sky.xljgps.utils.Constant.SIGN_SECRET_CODE;
import static com.sky.xljgps.utils.Constant.URL_DATA_DECODE_TYPE;

/**
 * description:
 * author:  hefeng
 * create:  2018-07-11  下午1:47
 */

public class VerifySignUtil {
    private static final Logger logger = LoggerFactory.getLogger(VerifySignUtil.class);

    /***
     *  description:
     *  本地收到客户端的参数解码前,先和SIGN_SECRET_CODE拼接,形成服务器端的sign,再和客户端传输过来的sign对比
     *  author:  hefeng
     *  create:  18-7-11 下午12:20
     *  params:  * @param baseBean
     *  return:  com.sky.xljgps.msg.ResultMsg
     */
    public static ResultMsg vertificationSign(BaseBean baseBean)
    {
        String encodedString = baseBean.getData();
        String md5Sign = baseBean.getSign();
        String mySign = MD5Util.encrypt(encodedString + SIGN_SECRET_CODE);
        logger.info("vertificationSign ..........encodedString={}", encodedString);
        logger.info("vertificationSign ..........md5Sign={}", md5Sign);
        logger.info("vertificationSign ..........mySign={}", mySign);

        if (!md5Sign.equals(mySign))
        {
            throw new GpsException(ResultEnum.VERTIFICATION_ERROR);
        }

        return ResultUtil.success();
    }


    /***
     *  description:
     *  解码从客户端收到的参数
     *  author:  hefeng
     *  create:  18-7-11 下午12:26
     *  params:
     *  return:  java.lang.String
     */
    public static String getDecodedString(String encodedString) {
        String decodedString = null;

        if (encodedString == null)
        {
            throw new GpsException(ResultEnum.PARAM_EMPTY);
        }
        try {
            decodedString = URLDecoder.decode(encodedString, URL_DATA_DECODE_TYPE);
            logger.info("getDecodedString ..........data_size={}", decodedString.length());
            logger.info("getDecodedString ..........decodedString={}", decodedString);
        } catch (UnsupportedEncodingException e) {
            throw new GpsException(ResultEnum.DECODE_ERROR);
        }

        return decodedString;
    }
}
