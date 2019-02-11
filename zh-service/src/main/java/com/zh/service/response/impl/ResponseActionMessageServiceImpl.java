package com.zh.service.response.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zh.dto.response.ReponseActionMessageDTO;
import com.zh.dto.response.ResponseChargemanIdDTO;
import com.zh.dto.response.ResponseDepartmentIdDTO;
import com.zh.dto.response.ResponseStartMapDTO;
import com.zh.dto.warning.DepartmentUserDTO;
import com.zh.dto.warning.FloodDutyOwnerUserDTO;
import com.zh.entity.basic.BasicDivisionOrganization;
import com.zh.entity.response.ResponseActionMessage;
import com.zh.entity.sys.SysUser;
import com.zh.mapper.response.ResponseActionMessageMapper;
import com.zh.service.basic.BasicDivisionOrganizationService;
import com.zh.service.response.ResponseActionMessageService;
import com.zh.service.sys.SysUserService;
import com.zh.service.warning.impl.WarningRecordOutsideMessageServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 响应启动消息表 服务实现类
 * </p>
 *
 * @author 赵梦霞
 * @since 2019-01-11
 */
@Service
public class ResponseActionMessageServiceImpl extends ServiceImpl<ResponseActionMessageMapper, ResponseActionMessage> implements ResponseActionMessageService {
    @Resource
    private ResponseActionMessageMapper responseActionMessageMapper;
    @Resource
    private WarningRecordOutsideMessageServiceImpl warningRecordOutsideMessageService;
    @Override
    public IPage<ReponseActionMessageDTO> selectAllActionPage(Page<ReponseActionMessageDTO> page, Long id, Boolean f) {
        return responseActionMessageMapper.selectAllActionPage(page,id,f);
    }

    @Override
    public  List<ResponseStartMapDTO> selectAllStartPage( Long id) {
        List<ResponseStartMapDTO> responseStartMapDTOS=new ArrayList<>();
        List<ResponseStartMapDTO> responseStartMapDTOS1=responseActionMessageMapper.selectAllStartPage(id);
        for (ResponseStartMapDTO responseStartMapDTO:responseStartMapDTOS1){
            String department=responseStartMapDTO.getDepartment();
            String chargeMan=responseStartMapDTO.getChargeMan();
            List<FloodDutyOwnerUserDTO> responseChargemanIdDTOS=warningRecordOutsideMessageService.getByChargeMan(chargeMan);
            List<DepartmentUserDTO> responseDepartmentIdDTOS=warningRecordOutsideMessageService.getBydepartment(department);
            responseStartMapDTO.setResponseDepartmentIdDTOS(responseDepartmentIdDTOS);
            responseStartMapDTO.setResponseChargemanIdDTOS(responseChargemanIdDTOS);
            responseStartMapDTOS.add(responseStartMapDTO);
        }
        return responseStartMapDTOS;
    }

    @Override
    public List<ResponseStartMapDTO> selectAllStartMapPage(Long id, Boolean f) {
        return responseActionMessageMapper.selectAllStartMapPage(id,f);
    }
}
