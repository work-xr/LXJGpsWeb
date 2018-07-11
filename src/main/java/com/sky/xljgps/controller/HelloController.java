package com.sky.xljgps.controller;

import com.sky.xljgps.msg.ResultMsg;
import com.sky.xljgps.utils.ResultUtil;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.sky.xljgps.utils.Constant.ERROR_CODE_URL_ADDRESS_ERROR;

@RestController
public class HelloController {

    @GetMapping(value = {"/hello", "/hi"})
    public ResultMsg sayHello(BindingResult result)
    {
        if (result.hasErrors())
        {
            return ResultUtil.error(ERROR_CODE_URL_ADDRESS_ERROR, result.getFieldError().getDefaultMessage());
        }

        return ResultUtil.success("Spring boot say hello to you!");
    }
}
