package com.zh.service.warning.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zh.api.Result;
import com.zh.dto.warning.DepartmentUserDTO;
import com.zh.dto.warning.WarningSitePeopleDTO;
import com.zh.entity.basic.BasicDivisionOrganization;
import com.zh.entity.flood.FloodDutyType;
import com.zh.entity.sys.SysUser;
import com.zh.entity.warning.WarningSitePeople;
import com.zh.mapper.basic.BasicDivisionOrganizationMapper;
import com.zh.mapper.flood.FloodDutyTypeMapper;
import com.zh.mapper.sys.SysUserMapper;
import com.zh.mapper.warning.WarningSitePeopleMapper;
import com.zh.service.warning.WarningSitePeopleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 赵梦霞
 * @since 2019-01-11
 */
@Service
public class WarningSitePeopleServiceImpl extends ServiceImpl<WarningSitePeopleMapper, WarningSitePeople> implements WarningSitePeopleService {

    @Resource
    WarningSitePeopleMapper warningSitePeopleMapper;

    @Resource
    BasicDivisionOrganizationMapper basicDivisionOrganizationMapper;

    @Resource
    SysUserMapper sysUserMapper;

    @Resource
    FloodDutyTypeMapper floodDutyTypeMapper;

    @Override
    public Result getSiteWarningPeople(Long siteId) {
        List<WarningSitePeople> list = warningSitePeopleMapper.getSitePeople(siteId);
        WarningSitePeopleDTO warningSitePeopleDTO = new WarningSitePeopleDTO();
        if(list.size() > 0){
            WarningSitePeople warningSitePeople = list.get(0);
            List<String> headQuarters = new ArrayList<>(Arrays.asList(warningSitePeople.getHeadQuarters().split(",")));
            List<String> dutyPeople = new ArrayList<>(Arrays.asList(warningSitePeople.getChargeMan().split(",")));
            List<BasicDivisionOrganization> basicCommandDepartments = basicDivisionOrganizationMapper.selectList(null);
            List<SysUser> users = sysUserMapper.selectList(null);
            List<FloodDutyType> floodDutyTypes = floodDutyTypeMapper.selectList(null);
            Map<Long, SysUser> userMap = Maps.newHashMap();
            Map<Long, FloodDutyType> floodDutyTypeMap = Maps.newHashMap();
            Map<Long, BasicDivisionOrganization> commandDepartmentMap = Maps.newHashMap();
            basicCommandDepartments.forEach(a->commandDepartmentMap.put(a.getId(), a));
            users.forEach(a->userMap.put(a.getId(), a));
            floodDutyTypes.forEach(a->floodDutyTypeMap.put(a.getId(), a));

            List<DepartmentUserDTO> commands = Lists.newArrayList();
            headQuarters.forEach(a->{
                String[] strings = a.split("-");
                Long commandId = Long.valueOf(strings[0]);
                String commandName = commandDepartmentMap.get(commandId) == null ? null : commandDepartmentMap.get(commandId).getDivisionName();
                Long userId = Long.valueOf(strings[1]);
                String userName = userMap.get(userId) == null ? null : userMap.get(userId).getName();
                DepartmentUserDTO departmentUserDTO = new DepartmentUserDTO();
                departmentUserDTO.setOrganizationId(commandId);
                departmentUserDTO.setOrganizationName(commandName);
                departmentUserDTO.setUserId(userId);
                departmentUserDTO.setUserName(userName);
                commands.add(departmentUserDTO);
            });
            List<DepartmentUserDTO> dutyPeoples = Lists.newArrayList();
            dutyPeople.forEach(a->{
                String[] strings = a.split("-");
                Long dutyType = Long.valueOf(strings[0]);
                String dutyTypeName = floodDutyTypeMap.get(dutyType) == null ? null : floodDutyTypeMap.get(dutyType).getName();
                Long userId = Long.valueOf(strings[1]);
                String userName = userMap.get(userId) == null ? null : userMap.get(userId).getName();
                DepartmentUserDTO departmentUserDTO = new DepartmentUserDTO();
                departmentUserDTO.setOrganizationName(dutyTypeName);
                departmentUserDTO.setOrganizationId(dutyType);
                departmentUserDTO.setUserId(userId);
                departmentUserDTO.setUserName(userName);
                dutyPeoples.add(departmentUserDTO);
            });
            Map<Long, List<DepartmentUserDTO>> commondMap = commands.stream().collect(Collectors.groupingBy(DepartmentUserDTO::getOrganizationId));
            List<DepartmentUserDTO> commondList = Lists.newArrayList();
            for(Long id : commondMap.keySet()){
                DepartmentUserDTO departmentUserDTO = new DepartmentUserDTO();
                departmentUserDTO.setOrganizationId(id);
                departmentUserDTO.setOrganizationName(commandDepartmentMap.get(id) == null ? null : commandDepartmentMap.get(id).getDivisionName());
                departmentUserDTO.setList(commondMap.get(id));
                commondList.add(departmentUserDTO);
            }
            Map<Long, List<DepartmentUserDTO>> dutyMap = dutyPeoples.stream().collect(Collectors.groupingBy(DepartmentUserDTO::getOrganizationId));
            List<DepartmentUserDTO> dutyList = Lists.newArrayList();
            for(Long id : dutyMap.keySet()){
                DepartmentUserDTO departmentUserDTO = new DepartmentUserDTO();
                departmentUserDTO.setList(dutyMap.get(id));
                departmentUserDTO.setOrganizationId(id);
                departmentUserDTO.setOrganizationName(floodDutyTypeMap.get(id) == null ? null : floodDutyTypeMap.get(id).getName());
                dutyList.add(departmentUserDTO);
            }
            warningSitePeopleDTO.setDepartmentList(commondList);
            warningSitePeopleDTO.setChargeManList(dutyList);
        }
        return Result.success(warningSitePeopleDTO);
    }

    @Override
    public Result addOrUpdate(String headQuarters, String departments, Long siteId) {
        List<WarningSitePeople> list = warningSitePeopleMapper.getSitePeople(siteId);
        if(list.size() == 0){
            WarningSitePeople warningSitePeople = new WarningSitePeople();
            warningSitePeople.setChargeMan(departments);
            warningSitePeople.setHeadQuarters(headQuarters);
            warningSitePeople.setSiteId(siteId);
            warningSitePeopleMapper.insert(warningSitePeople);
            return Result.success("新增成功!");
        }else {
            WarningSitePeople warningSitePeople = list.get(0);
            warningSitePeople.setChargeMan(departments);
            warningSitePeople.setHeadQuarters(headQuarters);
            warningSitePeopleMapper.updateById(warningSitePeople);
            return Result.success("修改成功!");
        }
    }
}
