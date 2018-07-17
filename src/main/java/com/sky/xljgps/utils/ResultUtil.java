package com.sky.xljgps.utils;

import com.sky.xljgps.controller.PhoneController;
import com.sky.xljgps.enums.ResultEnum;
import com.sky.xljgps.msg.ResultMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * description:
 * author:  hefeng
 * create:  2018-07-11  下午1:35
 */

public class ResultUtil {
    private static final Logger logger = LoggerFactory.getLogger(ResultUtil.class);

    public static ResultMsg success(Object object)
    {
        ResultMsg resultMsg = new ResultMsg();
        resultMsg.setCode(0);
        resultMsg.setMessage("success");
        resultMsg.setData(object);

        logger.info("ResultUtil...................resultMsg = {}", resultMsg);
        return resultMsg;
    }

    public static ResultMsg success()
    {
        return success(null);
    }

    public static ResultMsg error(Integer code, String msg)
    {
        ResultMsg resultMsg = new ResultMsg();
        resultMsg.setCode(code);
        resultMsg.setMessage(msg);
        resultMsg.setData(null);

        logger.info("ResultUtil...................resultMsg = {}", resultMsg);
        return resultMsg;
    }

    public static ResultMsg error(ResultEnum resultEnum)
    {
        ResultMsg resultMsg = new ResultMsg();
        resultMsg.setCode(resultEnum.getCode());
        resultMsg.setMessage(resultEnum.getMessage());
        resultMsg.setData(null);

        logger.info("ResultUtil...................resultMsg = {}", resultMsg);
        return resultMsg;
    }
}
