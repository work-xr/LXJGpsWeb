package com.sky.xljgps.utils;

import com.sky.xljgps.enums.ResultEnum;
import com.sky.xljgps.msg.ResultMsg;

public class ResultUtil {

    public static ResultMsg success(Object object)
    {
        ResultMsg resultMsg = new ResultMsg();
        resultMsg.setCode(0);
        resultMsg.setMessage("success");
        resultMsg.setData(object);

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

        return resultMsg;
    }

    public static ResultMsg error(ResultEnum resultEnum)
    {
        ResultMsg resultMsg = new ResultMsg();
        resultMsg.setCode(resultEnum.getCode());
        resultMsg.setMessage(resultEnum.getMessage());
        resultMsg.setData(null);

        return resultMsg;
    }
}
