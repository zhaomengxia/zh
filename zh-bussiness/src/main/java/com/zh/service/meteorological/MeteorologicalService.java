package com.zh.service.meteorological;

/**
 * 气象预警接口
 *
 * @author  hahaha
 * @since 2018-12-22 15:15

 **/
public interface MeteorologicalService {

    /**
     * @author  hahaha
     * @param cityName 城市名称
     * @since 2018/12/22 15:17
     * @Description 雨水预警
     **/
    String getRainStormWarn(String cityName);

}
