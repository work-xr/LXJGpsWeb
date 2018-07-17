package com.sky.xljgps.service;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sky.xljgps.bean.BaseBean;
import com.sky.xljgps.bean.PhoneBean;
import com.sky.xljgps.enums.ResultEnum;
import com.sky.xljgps.exception.GpsException;
import com.sky.xljgps.msg.PhoneMsg;
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
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    /**
    *  description:
    *  根据id查询上报的亲情号码信息
    *  author:  hefeng
    *  create:  18-7-13 下午7:48
    *  params:   * @param PhoneBean
    *  return:
    */
    @Transactional
    public PhoneBean findPhoneInfoById(Integer id)
    {
        Optional<PhoneBean> phoneBean = phoneGetRepository.findById(id);
        return phoneBean.get();
    }

    /**
    *  description:
    *  获取最新的亲情号码信息, 根据IMEI查询, 然后取ID最小的记录
    *  author:  hefeng
    *  create:  18-7-13 下午3:06
    *  params:   * @param
    *  return:  com.sky.xljgps.bean.PhoneBean
    */
    @Transactional
    public PhoneBean findPhoneInfoLatest(BaseBean baseBean)
    {
        VerifySignUtil.vertificationSign(baseBean);
        String gsonString = VerifySignUtil.getDecodedString(baseBean.getData());

        PhoneBean phoneGetBean = convertToGson2(gsonString);

        Optional<PhoneBean> phoneBean = phoneGetRepository.findById(10);
        //PhoneBean phoneBean1 = phoneGetRepository.findPhoneInfoByIMEI(phoneGetBean.getImei());
        //logger.info("findPhoneInfoLatest phoneBean={}", phoneBean);
        //logger.info("findPhoneInfoLatest phoneBean.get={}", phoneBean.get());
        return phoneBean.get();
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
        String gsonString = VerifySignUtil.getDecodedString(baseBean.getData());

        PhoneBean phoneGetBean = convertToGson2(gsonString);

        phoneGetBean.setManufactory(phoneGetBean.getManufactory());
        phoneGetBean.setModel(phoneGetBean.getModel());
        phoneGetBean.setImei(phoneGetBean.getImei());
        phoneGetBean.setCompany(phoneGetBean.getCompany());
        phoneGetBean.setType(phoneGetBean.getType());
        phoneGetBean.setTime(phoneGetBean.getTime());
        phoneGetBean.setName(phoneGetBean.getName());
        phoneGetBean.setSosPhone(phoneGetBean.getSosPhone());

        //savePhoneInfoFirst(phoneGetBean);

        return ResultUtil.success(phoneGetRepository.save(phoneGetBean));
    }

    /**
    *  description:
    *  调用savePhoneInfo, POST之前将亲情号码先保存起来, 以便后来PUSH到客户端
    *  author:  hefeng
    *  create:  18-7-13 下午3:23
    *  params:   * @param
    *  return:  com.sky.xljgps.msg.ResultMsg
    */
    private ResultMsg savePhoneInfoFirst(PhoneBean phoneBean)
    {
        phoneBean.setId(0);
        return ResultUtil.success(phoneGetRepository.save(phoneBean));
    }

    /***
    *  description:
    *  把客户端发送过来的Phone信息,转换为GSON格式
    *  author:  hefeng
    *  create:  18-7-13 下午2:32
    *  params:   * @param gsonString
    *  return:  com.sky.xljgps.bean.PhoneBean
    */
    private PhoneBean convertToGson2(String gsonString)
    {
        PhoneBean phoneGetBean = new PhoneBean();

        if (gsonString == null || gsonString.length() == 0)
        {
            return null;
        }

        Gson gson = new Gson();
        Map<String, String> retMap = gson.fromJson(gsonString, new TypeToken<Map<String, String>>(){}.getType());

        logger.info("convertToGson2.......... length = {}", retMap.entrySet().size());

        for (Map.Entry<String, String> entry : retMap.entrySet())
        {
            String key = entry.getKey();
            String value = entry.getValue();
            //logger.info("key = {}", key);
            //logger.info("value = {}", value);

            if (key.contains(PARAM_MANUFACTORY))
            {
                phoneGetBean.setManufactory(value);
            }
            else if (key.contains(PARAM_MODEL))
            {
                phoneGetBean.setModel(value);
            }
            else if (key.contains(PARAM_IMEI))
            {
                phoneGetBean.setImei(value);
            }
            else if (key.contains(PARAM_COMPANY))
            {
                phoneGetBean.setCompany(value);
            }
            else if (key.contains(PARAM_TIME))
            {
                phoneGetBean.setTime(value);
            }
            else if (key.contains(PARAM_TYPE))
            {
                phoneGetBean.setType(value);
            }
            else if (key.contains(PARAM_NAME))
            {
                phoneGetBean.setName(value);
            }
            else if (key.contains(PARAM_SOS_PHONE))
            {
                phoneGetBean.setSosPhone(value);
            }
            else
            {
                throw new GpsException(ResultEnum.PARAM_ERROR);
            }
        }

        return phoneGetBean;
    }

    /***
     *  description:
     *  把客户端发送过来的Phone信息,转换为GSON格式(已废弃,使用gson进行参数解析)
     *  author:  hefeng
     *  create:  18-7-11 上午11:37
     *  params:  * @param data
     *  return:  com.sky.xljgps.bean.PhoneBean
     */
    @Deprecated
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
