package com.zh.service.excel;

import java.io.InputStream;

/**
 * @author  赵梦霞
 * @since 2018-12-25 11:46

 **/
public interface ImportService {

    /**
     * @param inputStream excel流对象
     * @author  赵梦霞
     * @Description 堤防信息导入
     * @since 2018/12/25 13:46
     **/
    boolean importDike(InputStream inputStream);

    /**
     * @param inputStream excel流对象
     * @author  赵梦霞
     * @Description 水库信息导入
     * @since 2018/12/25 14:33
     **/
    boolean importWater(InputStream inputStream);

    /**
     * @param inputStream excel流对象
     * @author  赵梦霞
     * @Description 塘坝信息导入
     * @since 2018/12/25 14:33
     **/
    boolean importPond(InputStream inputStream);

    /**
     * @param inputStream excel流对象
     * @author  赵梦霞
     * @Description 路涵信息导入
     * @since 2018/12/25 14:33
     **/
    boolean importRoad(InputStream inputStream);

    /**
     * @param inputStream excel流对象
     * @author  赵梦霞
     * @Description 桥梁信息导入
     * @since 2018/12/25 14:33
     **/
    boolean importBridge(InputStream inputStream);

    /**
     * @param inputStream excel流对象
     * @author  赵梦霞
     * @Description 泵站信息导入
     * @since 2018/12/25 14:57
     **/
    boolean importStation(InputStream inputStream);

    /**
     * 整体情况导入
     * @param inputStream excel流对象
     * @return
     */
    boolean importWhole(InputStream inputStream);

    /**
     * 防汛责任人导入
     * @param inputStream excel流对象
     * @return
     */
    boolean importPreventionOwner(InputStream inputStream);

    /**
     * 基础信息-中小河流现状防洪能力导入
     * @param inputStream excel流对象
     * @return
     */
    boolean importRiverFlood(InputStream inputStream);

    /**
     * 基础信息-圩区 导入
     * @param inputStream excel流对象
     * @return
     */
    boolean importPolder(InputStream inputStream);

    /**
     * 基础信息-自动监测站 导入
     * @param inputStream excel流对象
     * @return
     */
    boolean importAutoStation(InputStream inputStream);

    /**
     * 基础信息-历史洪涝灾害 导入
     * @param inputStream excel流对象
     * @return
     */
    boolean importhistoryFloodRecord(InputStream inputStream);

    /**
     * 基础信息-企事业单位 导入
     * @param inputStream excel流对象
     * @return
     */
    boolean importEnterprise(InputStream inputStream);

    /**
     * 县(市、区)社会经济 导入
     * @param inputStream excel流对象
     * @return
     */
    boolean importCountry(InputStream inputStream);
}
