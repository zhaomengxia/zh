package com.zh.controller.data;

import com.zh.dto.data.QueryDTO;
import com.zh.exceptions.UnifiedException;

/**
 * @author  赵梦霞
 * @since 2019-01-16 16:44

 **/
public class DataController {

    protected static void parameterCheck(QueryDTO queryDTO) {
        if (queryDTO.getId() == null) {
            throw new UnifiedException("站点主键参数");
        }
        if (queryDTO.getStart() == null) {
            throw new UnifiedException("缺少开始时间参数");
        }
        if (queryDTO.getEnd() == null) {
            throw new UnifiedException("缺少结束时间点参数");
        }
    }
}
