package com.sky.xljgps.utils;

import com.sky.xljgps.bean.BaseBean;
import com.sky.xljgps.enums.ResultEnum;
import com.sky.xljgps.exception.GpsException;
import com.sky.xljgps.msg.ResultMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.sky.xljgps.utils.Constant.SIGN_SECRET_CODE;

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
}
