package com.sky.xljgps.service;

import com.sky.xljgps.bean.BaseBean;
import com.sky.xljgps.bean.GpsBean;
import com.sky.xljgps.enums.ResultEnum;
import com.sky.xljgps.exception.GpsException;
import com.sky.xljgps.msg.ResultMsg;
import com.sky.xljgps.repository.GpsRepository;
import com.sky.xljgps.utils.MD5Util;
import com.sky.xljgps.utils.ParamUtil;
import com.sky.xljgps.utils.ResultUtil;
import com.sky.xljgps.utils.VerifySignUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;

import static com.sky.xljgps.utils.Constant.*;

/***
*  description:
*
*  author:  hefeng
*  create:  18-7-11 下午12:20
*/
@Service
public class GpsService {

    private static final Logger logger = LoggerFactory.getLogger(GpsService.class);

    @Autowired
    private GpsRepository gpsRepository;

    /***
    *  description:
    *  查询所有从客户端上传的GPS定位信息
    *  author:  hefeng
    *  create:  18-7-11 下午1:41
    *  params:
    *  return:  java.util.List<com.sky.xljgps.bean.GpsBean>
    */
    @Transactional
    public List<GpsBean> findGpsInfoAll()
    {
        return gpsRepository.findAll();
    }

    /***
    *  description:
    *  把客户端发送过来的GPS信息,保存到数据库
    *  author:  hefeng
    *  create:  18-7-11 下午12:28
    *  params:   * @param baseBean
    *  return:  com.sky.xljgps.msg.ResultMsg
    */
    @Transactional
    public ResultMsg saveGpsInfo(BaseBean baseBean)
    {
        logger.info("saveGpsInfo start..........baseBean={}", baseBean);

        VerifySignUtil.vertificationSign(baseBean);

        GpsBean gpsBean = convertToGson(ParamUtil.getDecodedString(baseBean.getData()));
        gpsBean.setManufactory(gpsBean.getManufactory());
        gpsBean.setModel(gpsBean.getModel());
        gpsBean.setImei(gpsBean.getImei());
        gpsBean.setCompany(gpsBean.getCompany());
        gpsBean.setType(gpsBean.getType());
        gpsBean.setTime(gpsBean.getTime());
        gpsBean.setPosition_type(gpsBean.getPosition_type());
        gpsBean.setLoc_type(gpsBean.getLoc_type());
        gpsBean.setLongitude(gpsBean.getLongitude());
        gpsBean.setLatitude(gpsBean.getLatitude());
        gpsBean.setPower(gpsBean.getPower());

        return ResultUtil.success(gpsRepository.save(gpsBean));
    }


    /***
    *  description:
    *  把客户端发送过来的GPS信息,转换为GSON格式
    *  author:  hefeng
    *  create:  18-7-11 上午11:37
    *  params:  * @param data
    *  return:  com.sky.xljgps.bean.GpsBean
    */
    private GpsBean convertToGson(String data)
    {
        GpsBean gpsBean = new GpsBean();
        int length = 0;
        HashMap<String, String> hashMap = null;

        if (data == null || data.length() == 0)
        {
            return null;
        }

        data = data.substring(1);  // delete {
        String[] strSplits = data.split(":");
        length = strSplits.length - 1;

        logger.info("convertToGson.......... length = {}", length);

        for (int i=0; i<length; ++i)
        {
            hashMap = ParamUtil.getOneParam(data);
            String key = hashMap.keySet().toString();                       //  [company]

            logger.info("convertToGson.......... hashMap.key = {}", key);

            if (key.contains(PARAM_MANUFACTORY))
            {
                gpsBean.setManufactory(hashMap.get(PARAM_MANUFACTORY));
            }
            else if (key.contains(PARAM_MODEL))
            {
                gpsBean.setModel(hashMap.get(PARAM_MODEL));
            }
            else if (key.contains(PARAM_IMEI))
            {
                gpsBean.setImei(hashMap.get(PARAM_IMEI));
            }
            else if (key.contains(PARAM_COMPANY))
            {
                gpsBean.setCompany(hashMap.get(PARAM_COMPANY));
            }
            else if (key.contains(PARAM_TIME))
            {
                gpsBean.setTime(hashMap.get(PARAM_TIME));
            }
            else if (key.contains(PARAM_TYPE))
            {
                gpsBean.setType(hashMap.get(PARAM_TYPE));
            }
            else if (key.contains(PARAM_POWER))
            {
                gpsBean.setPower(hashMap.get(PARAM_POWER));
            }
            else if (key.contains(PARAM_LATITUDE))
            {
                gpsBean.setLatitude(hashMap.get(PARAM_LATITUDE));
            }

            else if (key.contains(PARAM_LOC_TYPE))
            {
                logger.info("convertToGson loc_type = {}", hashMap.get(PARAM_LOC_TYPE));
                gpsBean.setLoc_type(hashMap.get(PARAM_LOC_TYPE));
            }

            else if (key.contains(PARAM_LONGITUDE))
            {
                logger.info("convertToGson longitude = {}", hashMap.get(PARAM_LONGITUDE));
                gpsBean.setLongitude(hashMap.get(PARAM_LONGITUDE));
            }

            else if (key.contains(PARAM_POSITION_TYPE))
            {
                logger.info("convertToGson position_type = {}", hashMap.get(PARAM_POSITION_TYPE));
                gpsBean.setPosition_type(hashMap.get(PARAM_POSITION_TYPE));
            }
            else
            {
                throw new GpsException(ResultEnum.PARAM_ERROR);
            }
        }
        ParamUtil.resetParamPosition();

        return gpsBean;
    }
}
