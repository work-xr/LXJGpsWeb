package com.sky.xljgps.controller;

import com.sky.xljgps.bean.BaseBean;
import com.sky.xljgps.bean.PhoneBean;
import com.sky.xljgps.enums.ResultEnum;
import com.sky.xljgps.msg.PhoneMsg;
import com.sky.xljgps.msg.ResultMsg;
import com.sky.xljgps.service.PhoneService;
import com.sky.xljgps.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import static com.sky.xljgps.utils.Constant.ERROR_CODE_FIND_LASTEST_PHONE;
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
    public @ResponseBody ResultMsg findPhoneInfoAll()
    {
        return ResultUtil.success(phoneGetService.findPhoneInfoAll());
    }

    /**
    *  description:
    *  根据id查询上报的亲情号码信息
    *  author:  hefeng
    *  create:  18-7-13 下午7:53
    *  params:   * @param id
    *  return:  com.sky.xljgps.msg.ResultMsg<com.sky.xljgps.msg.PhoneMsg>
    */
    @GetMapping(value = "phone/{id}")
    public @ResponseBody ResultMsg<PhoneMsg> findPhoneInfoById(@PathVariable("id") Integer id)
    {
        PhoneBean phoneBean = phoneGetService.findPhoneInfoById(id);
        logger.info("findPhoneInfoById phoneBean={}", phoneBean);
        if (phoneBean == null)
        {
            return ResultUtil.error(ResultEnum.PHONEINFO_FIND_ERROR);
        }

        PhoneMsg phoneMsg = new PhoneMsg();
        phoneMsg.setPhone(phoneBean.getSosPhone());
        phoneMsg.setRelationship(phoneBean.getName());

        return ResultUtil.success(phoneMsg);
    }

    /**
    *  description:
    *  获取最新的亲情号码信息
    *  author:  hefeng
    *  create:  18-7-13 下午3:04
    *  params:   * @param null
    *  return:  ResultMsg
    *   @ResponseBody 是必须的,防止异常: javax.servlet.ServletException: Circular view path [phonelatest]: would dispatch back to the current handler URL
    *   [/api/test/phonelatest] again. Check your ViewResolver setup! (Hint: This may be the result of an unspecified view, due to default view name generation.)
    */
    @GetMapping(value = "/phonelatest")
    public @ResponseBody ResultMsg<PhoneMsg> findPhoneInfoLatest(BaseBean baseBean, BindingResult result)
    {
        if (result.hasErrors())
        {
            return ResultUtil.error(ResultEnum.PHONEINFO_GET_LATEST);
        }

        PhoneBean phoneBean = phoneGetService.findPhoneInfoLatest(baseBean);
        logger.info("findPhoneInfoLatest phoneBean={}", phoneBean);
        if (phoneBean == null)
        {
            return ResultUtil.error(ResultEnum.PHONEINFO_GET_LATEST);
        }

        PhoneMsg phoneMsg = new PhoneMsg();
        phoneMsg.setPhone(phoneBean.getSosPhone());
        phoneMsg.setRelationship(phoneBean.getName());

        return ResultUtil.success(phoneMsg);
    }

    /***
     *  description:
     *  把客户端发送过来的亲情号码信息,保存到数据库
     *  author:  hefeng
     *  create:  18-7-11 下午12:28
     *  params:   * @param baseBean
     *  return:  com.sky.xljgps.msg.ResultMsg
     *   @ResponseBody 是必须的,防止异常: javax.servlet.ServletException: Circular view path [phonelatest]: would dispatch back to the current handler URL
     *   [/api/test/phone] again. Check your ViewResolver setup! (Hint: This may be the result of an unspecified view, due to default view name generation.)
     */
    @PutMapping(value = "/phone")
    public @ResponseBody ResultMsg savePhoneInfo(BaseBean baseBean, BindingResult result)
    {
        logger.info("savePhoneInfo start..........baseBean={}", baseBean);

        if (result.hasErrors())
        {
            return ResultUtil.error(ERROR_CODE_SAVE_PHONE, result.getFieldError().getDefaultMessage());
        }

        return phoneGetService.savePhoneInfo(baseBean);
    }
}
