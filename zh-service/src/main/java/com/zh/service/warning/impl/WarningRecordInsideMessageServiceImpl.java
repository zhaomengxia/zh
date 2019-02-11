package com.zh.service.warning.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zh.dto.warning.DepartmentUserDTO;
import com.zh.dto.warning.FloodDutyOwnerUserDTO;
import com.zh.dto.warning.WarningRecordInsideMessageDTO;
import com.zh.entity.basic.BasicDivisionOrganization;
import com.zh.entity.flood.FloodDutyType;
import com.zh.entity.sys.SysUser;
import com.zh.entity.warning.WarningRecordInsideMessage;
import com.zh.mapper.warning.WarningRecordInsideMessageMapper;
import com.zh.service.basic.BasicDivisionOrganizationService;
import com.zh.service.flood.FloodDutyTypeService;
import com.zh.service.sys.SysUserService;
import com.zh.service.warning.WarningRecordInsideMessageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 内部预警预警反馈信息表 服务实现类
 * </p>
 *
 * @author 赵梦霞
 * @since 2019-01-13
 */
@Service
public class WarningRecordInsideMessageServiceImpl extends ServiceImpl<WarningRecordInsideMessageMapper, WarningRecordInsideMessage> implements WarningRecordInsideMessageService {
    @Resource
    private WarningRecordInsideMessageMapper warningRecordInsideMessageMapper;
    @Resource
    private WarningRecordOutsideMessageServiceImpl warningRecordOutsideMessageService;
    @Override
    public WarningRecordInsideMessageDTO selectAllWarnInsideMessageById(Long id) {
        WarningRecordInsideMessageDTO warningRecordInsideMessageDTO= warningRecordInsideMessageMapper.selectAllWarnInsideMessageById(id);
        String department=warningRecordInsideMessageDTO.getDepartment();
        String chargeMan=warningRecordInsideMessageDTO.getChargeMan();
        List<DepartmentUserDTO> departmentUserDTOS=warningRecordOutsideMessageService.getBydepartment(department);
        List<FloodDutyOwnerUserDTO> floodDutyOwnerUserDTOS=warningRecordOutsideMessageService.getByChargeMan(chargeMan);
        warningRecordInsideMessageDTO.setDepartmentUserDTOS(departmentUserDTOS);
        warningRecordInsideMessageDTO.setFloodDutyOwnerUserDTOS(floodDutyOwnerUserDTOS);
        return warningRecordInsideMessageDTO;
    }
}
