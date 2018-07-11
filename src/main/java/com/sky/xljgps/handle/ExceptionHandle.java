package com.sky.xljgps.handle;

import com.sky.xljgps.exception.PersonException;
import com.sky.xljgps.msg.ResultMsg;
import com.sky.xljgps.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandle {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultMsg handle(Exception e)
    {
        if (e instanceof PersonException)
        {
            PersonException personException = (PersonException)e;
            return ResultUtil.error(personException.getCode(), personException.getMessage());
        }

        logger.error("system internal erro={}", e);
        return ResultUtil.error(-1, "unknown error");

    }
}
