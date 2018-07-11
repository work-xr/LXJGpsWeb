package com.sky.xljgps.controller;

import com.sky.xljgps.bean.BaseBean;
import com.sky.xljgps.msg.ResultMsg;
import com.sky.xljgps.service.GpsService;
import com.sky.xljgps.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.sky.xljgps.utils.Constant.ERROR_CODE_SAVE_GPS;

@RestController
public class GpsController {

    private static final Logger logger = LoggerFactory.getLogger(GpsController.class);

    @Autowired
    private GpsService gpsService;

    /***
     *  description:
     *  查询所有从客户端上传的GPS定位信息
     *  author:  hefeng
     *  create:  18-7-11 下午1:41
     *  params:
     *  return:  java.util.List<com.sky.xljgps.bean.GpsBean>
     */
    @GetMapping(value = "/gps")
    public ResultMsg findGpsInfoAll()
    {
        return ResultUtil.success(gpsService.findGpsInfoAll());
    }

    /***
     *  description:
     *  把客户端发送过来的GPS信息,保存到数据库
     *  author:  hefeng
     *  create:  18-7-11 下午12:28
     *  params:   * @param baseBean
     *  return:  com.sky.xljgps.msg.ResultMsg
     */
    @PostMapping(value = "/gps")
    public ResultMsg saveGpsInfo(BaseBean baseBean, BindingResult result
                              /*@RequestParam("imei") String imei,
                              @RequestParam("company") String company,
                              @RequestParam("type") String type,
                              @RequestParam("time") String time,
                              @RequestParam("position_type") String position_type,
                              @RequestParam("loc_type") String loc_type,
                              @RequestParam("longitude") String longitude,
                              @RequestParam("latitude") String latitude,
                              @RequestParam("power") String power,
                              @RequestParam("data") String data,
                              @RequestParam("sign") String sign*/)
    {
        logger.info("saveGpsInfo start..........baseBean={}", baseBean);

        if (result.hasErrors())
        {
            return ResultUtil.error(ERROR_CODE_SAVE_GPS, result.getFieldError().getDefaultMessage());
        }

        return gpsService.saveGpsInfo(baseBean);
    }
}
