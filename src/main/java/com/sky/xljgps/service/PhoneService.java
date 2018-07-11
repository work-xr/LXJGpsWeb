package com.sky.xljgps.service;


import com.sky.xljgps.bean.BaseBean;
import com.sky.xljgps.bean.PhoneBean;
import com.sky.xljgps.enums.ResultEnum;
import com.sky.xljgps.exception.GpsException;
import com.sky.xljgps.msg.ResultMsg;
import com.sky.xljgps.repository.PhoneRepository;
import com.sky.xljgps.utils.ParamUtil;
import com.sky.xljgps.utils.ResultUtil;
import com.sky.xljgps.utils.VerifySignUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

import static com.sky.xljgps.utils.Constant.*;

/**
 * description:
 * author:  hefeng
 * create:  2018-07-11  下午1:35
 */

@Service
public class PhoneService {

    private static final Logger logger = LoggerFactory.getLogger(PhoneService.class);

    @Autowired
    private PhoneRepository phoneGetRepository;

    /***
    *  description:
    *  查询所有从客户端上传的亲情电话号码信息
    *  author:  hefeng
    *  create:  18-7-11 下午1:40
    *  params:
    *  return:  java.util.List<com.sky.xljgps.bean.PhoneBean>
    */
    @Transactional
    public List<PhoneBean> findPhoneInfoAll()
    {
        return phoneGetRepository.findAll();
    }

    /***
    *  description:
    *  把客户端发送过来的亲情号码信息,保存到数据库
    *  author:  hefeng
    *  create:  18-7-11 下午2:03
    *  params:   * @param baseBean
    *  return:  com.sky.xljgps.msg.ResultMsg
    */
    @Transactional
    public ResultMsg savePhoneInfo(BaseBean baseBean)
    {
        logger.info("savePhoneInfo start..........baseBean={}", baseBean);

        VerifySignUtil.vertificationSign(baseBean);

        PhoneBean phoneGetBean = convertToGson(ParamUtil.getDecodedString(baseBean.getData()));
        phoneGetBean.setManufactory(phoneGetBean.getManufactory());
        phoneGetBean.setModel(phoneGetBean.getModel());
        phoneGetBean.setImei(phoneGetBean.getImei());
        phoneGetBean.setCompany(phoneGetBean.getCompany());
        phoneGetBean.setType(phoneGetBean.getType());
        phoneGetBean.setTime(phoneGetBean.getTime());
        phoneGetBean.setName(phoneGetBean.getName());
        phoneGetBean.setSosPhone(phoneGetBean.getSosPhone());

        return ResultUtil.success(phoneGetRepository.save(phoneGetBean));
    }

    /***
     *  description:
     *  把客户端发送过来的Phone信息,转换为GSON格式
     *  author:  hefeng
     *  create:  18-7-11 上午11:37
     *  params:  * @param data
     *  return:  com.sky.xljgps.bean.PhoneBean
     */
    private PhoneBean convertToGson(String data)
    {
        PhoneBean phoneGetBean = new PhoneBean();
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

            //logger.info("convertToGson.......... hashMap.key = {}", key);

            if (key.contains(PARAM_MANUFACTORY))
            {
                phoneGetBean.setManufactory(hashMap.get(PARAM_MANUFACTORY));
            }
            else if (key.contains(PARAM_MODEL))
            {
                phoneGetBean.setModel(hashMap.get(PARAM_MODEL));
            }
            else if (key.contains(PARAM_IMEI))
            {
                phoneGetBean.setImei(hashMap.get(PARAM_IMEI));
            }
            else if (key.contains(PARAM_COMPANY))
            {
                phoneGetBean.setCompany(hashMap.get(PARAM_COMPANY));
            }
            else if (key.contains(PARAM_TIME))
            {
                phoneGetBean.setTime(hashMap.get(PARAM_TIME));
            }
            else if (key.contains(PARAM_TYPE))
            {
                phoneGetBean.setType(hashMap.get(PARAM_TYPE));
            }
            else if (key.contains(PARAM_NAME))
            {
                phoneGetBean.setName(hashMap.get(PARAM_NAME));
            }
            else if (key.contains(PARAM_SOS_PHONE))
            {
                phoneGetBean.setSosPhone(hashMap.get(PARAM_SOS_PHONE));
            }
            else
            {
                throw new GpsException(ResultEnum.PARAM_ERROR);
            }
        }
        ParamUtil.resetParamPosition();

        return phoneGetBean;
    }
}
