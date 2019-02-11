package com.zh.service.meteorological.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONException;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.zh.exceptions.UnifiedException;
import com.zh.service.meteorological.MeteorologicalService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author  hahaha
 * @since 2018-12-22 15:18

 **/
@Service
public class MeteorologicalServiceImpl implements MeteorologicalService {

    @Value("${meteorological.warning.url}")
    private String url;

    @Override
    public String getRainStormWarn(String cityName) {
        String result;
        try {
            JSONObject jsonObject = JSONUtil.parseFromXml(HttpUtil.get("http://www.weather.com.cn/data/alarm_xml/alarminfo.xml"));
            JSONArray roadIcings = jsonObject.getJSONObject("AlermInfo").getJSONObject("RainStorm").getJSONArray("Station");
            result = null;
            if (CollUtil.isNotEmpty(roadIcings)) {
                for (Object roadIcing : roadIcings) {
                    JSONObject roadIc = (JSONObject) roadIcing;
                    if (StrUtil.equals(roadIc.get("stationName").toString(), cityName)) {
                        result = roadIc.toString();
                        break;
                    }
                }            }
            if (StrUtil.isBlank(result)) {
                throw new UnifiedException("该城市无暴雨预警");
            }
        } catch (JSONException e) {
            throw new UnifiedException("该城市无暴雨预警");
        }
        return result;
    }
}
