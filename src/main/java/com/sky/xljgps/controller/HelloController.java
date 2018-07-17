package com.sky.xljgps.controller;

import com.sky.xljgps.msg.ResultMsg;
import com.sky.xljgps.utils.ResultUtil;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.sky.xljgps.utils.Constant.ERROR_CODE_URL_ADDRESS_ERROR;

/**
*  description:
*  仅仅用于测试
*  author:  hefeng
*  create:  18-7-11 下午7:57
*/
@RestController
public class HelloController {

    @GetMapping(value = {"/hello", "/hi"})
    public ResultMsg sayHello()
    {
        return ResultUtil.success("Spring boot say hello to you!");
    }
}
