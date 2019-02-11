package com.zh.service.data;

import com.zh.dto.data.QueryDTO;
import com.zh.dto.data.WaterListDTO;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 水情service 接口
 *
 * @author  赵梦霞
 * @date 2019-01-07 11:01

 **/
public interface WaterRegimeService {

    List<WaterListDTO> list(QueryDTO queryDTO);

    List<WaterListDTO> section(QueryDTO queryDTO);

    void export(HttpServletResponse response, QueryDTO queryDTO) throws IOException;
}
