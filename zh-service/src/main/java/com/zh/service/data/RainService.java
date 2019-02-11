package com.zh.service.data;

import com.zh.dto.data.RainListDTO;
import com.zh.dto.data.QueryDTO;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 雨情监控 service 接口
 *
 * @author  赵梦霞
 * @date 2019-01-04 14:27

 **/
public interface RainService {
    List<RainListDTO> list(QueryDTO queryDTO);

    List<List<RainListDTO>> strength(QueryDTO queryDTO);

    List<RainListDTO> polyline(QueryDTO queryDTO);

    Map<String, Double> histogram(QueryDTO queryDTO);

    void export(HttpServletResponse response,QueryDTO queryDTO) throws IOException;
}
