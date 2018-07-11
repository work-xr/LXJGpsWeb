package com.sky.xljgps.controller;

import com.sky.xljgps.bean.BaseBean;
import com.sky.xljgps.msg.ResultMsg;
import com.sky.xljgps.service.PhoneService;
import com.sky.xljgps.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import static com.sky.xljgps.utils.Constant.ERROR_CODE_SAVE_PHONE;

/**
 * description:
 * author:  hefeng
 * create:  2018-07-11  下午1:43
 */

@Controller
public class PhoneController {
    private static final Logger logger = LoggerFactory.getLogger(PhoneController.class);

    @Autowired
    private PhoneService phoneGetService;

    /***
     *  description:
     *  查询所有从客户端上传的亲情号码信息
     *  author:  hefeng
     *  create:  18-7-11 下午1:41
     *  params:
     *  return:  java.util.List<com.sky.xljgps.bean.GpsBean>
     */
    @GetMapping(value = "/phone")
    public ResultMsg findPhoneInfoAll()
    {
        return ResultUtil.success(phoneGetService.findPhoneInfoAll());
    }

    /***
     *  description:
     *  把客户端发送过来的亲情号码信息,保存到数据库
     *  author:  hefeng
     *  create:  18-7-11 下午12:28
     *  params:   * @param baseBean
     *  return:  com.sky.xljgps.msg.ResultMsg
     */
    @PutMapping(value = "/phone")
    public ResultMsg savePhoneInfo(BaseBean baseBean, BindingResult result)
    {
        logger.info("savePhoneInfo start..........baseBean={}", baseBean);

        if (result.hasErrors())
        {
            return ResultUtil.error(ERROR_CODE_SAVE_PHONE, result.getFieldError().getDefaultMessage());
        }

        return phoneGetService.savePhoneInfo(baseBean);
    }
}
