package com.zh.service.warning.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zh.dto.warning.DepartmentUserDTO;
import com.zh.dto.warning.FloodDutyOwnerUserDTO;
import com.zh.dto.warning.WarningRecordOutsideMessageDTO;
import com.zh.entity.basic.BasicDivisionOrganization;
import com.zh.entity.flood.FloodDutyType;
import com.zh.entity.sys.SysUser;
import com.zh.entity.warning.WarningRecordOutsideMessage;
import com.zh.mapper.warning.WarningRecordOutsideMessageMapper;
import com.zh.service.basic.BasicDivisionOrganizationService;
import com.zh.service.flood.FloodDutyTypeService;
import com.zh.service.sys.SysUserService;
import com.zh.service.warning.WarningRecordOutsideMessageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 外部预警反馈信息 服务实现类
 * </p>
 *
 * @author 赵梦霞
 * @since 2019-01-13
 */
@Service
public class WarningRecordOutsideMessageServiceImpl extends ServiceImpl<WarningRecordOutsideMessageMapper, WarningRecordOutsideMessage> implements WarningRecordOutsideMessageService {
    @Resource
    private WarningRecordOutsideMessageMapper warningRecordOutsideMessageMapper;
    @Resource
    private BasicDivisionOrganizationService basicDivisionOrganizationService;
    @Resource
    private SysUserService sysUserService;
    @Resource
    private FloodDutyTypeService floodDutyTypeService;

    @Override
    public WarningRecordOutsideMessageDTO selectAllWarnInsideMessageById(Long id) {
        WarningRecordOutsideMessageDTO warningRecordOutsideMessageDTO = warningRecordOutsideMessageMapper.selectAllWarnInsideMessageById(id);
        String department = warningRecordOutsideMessageDTO.getDepartment();
        String chargeMan = warningRecordOutsideMessageDTO.getChargeMan();
        List<DepartmentUserDTO> departmentUserDTOS = this.getBydepartment(department);
        List<FloodDutyOwnerUserDTO> floodDutyOwnerUserDTOS = this.getByChargeMan(chargeMan);
        warningRecordOutsideMessageDTO.setDepartmentUserDTOS(departmentUserDTOS);
        warningRecordOutsideMessageDTO.setFloodDutyOwnerUserDTOS(floodDutyOwnerUserDTOS);
        return warningRecordOutsideMessageDTO;
    }

   public List<FloodDutyOwnerUserDTO> getByChargeMan(String chargeMan){
        List<FloodDutyOwnerUserDTO> floodDutyOwnerUserDTOS = new ArrayList<>();
        if (StrUtil.isNotBlank(chargeMan)) {
            List<String> flooduser = Arrays.asList(chargeMan.split(","));
            for (String f : flooduser) {
                FloodDutyOwnerUserDTO floodDutyOwnerUserDTO = new FloodDutyOwnerUserDTO();
                List<String> floodu = Arrays.asList(f.split("-"));
                FloodDutyType floodDutyType = floodDutyTypeService.getById(Long.valueOf(floodu.get(0)));
                SysUser sysUser = sysUserService.getById(Long.valueOf(floodu.get(1)));
                if (floodDutyType != null) {
                    floodDutyOwnerUserDTO.setDutyId(floodDutyType.getId());
                    floodDutyOwnerUserDTO.setDutyName(floodDutyType.getName());
                }
                if (sysUser != null) {
                    floodDutyOwnerUserDTO.setUserId(sysUser.getId());
                    floodDutyOwnerUserDTO.setUserName(sysUser.getName());
                }
                floodDutyOwnerUserDTOS.add(floodDutyOwnerUserDTO);

            }

        }
        return floodDutyOwnerUserDTOS;
    }

    public List<DepartmentUserDTO> getBydepartment(String department){
        List<DepartmentUserDTO> departmentUserDTOS = new ArrayList<>();
        if (StrUtil.isNotBlank(department)) {
            List<String> depart = Arrays.asList(department.split(","));
            for (String s : depart) {
                DepartmentUserDTO departmentUserDTO = new DepartmentUserDTO();
                List<String> depa = Arrays.asList(s.split("-"));
                BasicDivisionOrganization b = basicDivisionOrganizationService.getById(Long.valueOf(depa.get(0)));
                SysUser sysUser = sysUserService.getById(Long.valueOf(depa.get(1)));
                if (b != null) {
                    departmentUserDTO.setOrganizationId(b.getId());
                    departmentUserDTO.setOrganizationName(b.getDivisionName());
                }
                if (sysUser != null) {
                    departmentUserDTO.setUserId(sysUser.getId());
                    departmentUserDTO.setUserName(sysUser.getName());
                }
                departmentUserDTOS.add(departmentUserDTO);
            }
        }
        return departmentUserDTOS;
    }
}
