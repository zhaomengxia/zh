package com.zh.service.response.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.zh.api.Result;
import com.zh.dto.basic.KeyValueDTO;
import com.zh.dto.response.ResponseActionDTO;
import com.zh.entity.response.ResponseAction;
import com.zh.mapper.response.ResponseActionMapper;
import com.zh.service.response.ResponseActionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 响应行动 服务实现类
 * </p>
 *
 * @author 赵梦霞
 * @since 2019-01-04
 */
@Service
public class ResponseActionServiceImpl extends ServiceImpl<ResponseActionMapper, ResponseAction> implements ResponseActionService {
    @Resource
    private ResponseActionMapper responseActionMapper;
    @Override
    public IPage<ResponseAction> selectAllPage(Page<ResponseAction> page,String keywords) {
        return responseActionMapper.selectAllPage(page,keywords);
    }

    @Override
    public Result selectAllList() {
        List<ResponseActionDTO> responseActions = responseActionMapper.selectAllList();
        responseActions.forEach(a->{
            List<KeyValueDTO> list = Lists.newArrayList();
            if(StringUtils.isNotBlank(a.getFirstPath())){
                KeyValueDTO keyValueDTO = new KeyValueDTO();
                keyValueDTO.setName("I级响应行动");
                keyValueDTO.setPath(a.getFirstPath());
                list.add(keyValueDTO);
            }
            if(StringUtils.isNotBlank(a.getSecondPath())){
                KeyValueDTO keyValueDTO = new KeyValueDTO();
                keyValueDTO.setName("II级响应行动");
                keyValueDTO.setPath(a.getSecondPath());
                list.add(keyValueDTO);
            }
            if(StringUtils.isNotBlank(a.getThirdPath())){
                KeyValueDTO keyValueDTO = new KeyValueDTO();
                keyValueDTO.setName("III级响应行动");
                keyValueDTO.setPath(a.getFirstPath());
                list.add(keyValueDTO);
            }
            if(StringUtils.isNotBlank(a.getFourthPath())){
                KeyValueDTO keyValueDTO = new KeyValueDTO();
                keyValueDTO.setName("IV级响应行动");
                keyValueDTO.setPath(a.getFirstPath());
                list.add(keyValueDTO);
            }
            a.setKeyValueDTOS(list);
        });
        return Result.success(responseActions);
    }
}
