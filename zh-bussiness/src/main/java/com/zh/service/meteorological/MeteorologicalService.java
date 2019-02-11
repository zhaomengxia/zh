package com.zh.service.meteorological;

/**
 * 气象预警接口
 *
 * @author  赵梦霞
 * @since 2018-12-22 15:15

 **/
public interface MeteorologicalService {

    /**
     * @author  赵梦霞
     * @param cityName 城市名称
     * @since 2018/12/22 15:17
     * @Description 雨水预警
     **/
    String getRainStormWarn(String cityName);

}
