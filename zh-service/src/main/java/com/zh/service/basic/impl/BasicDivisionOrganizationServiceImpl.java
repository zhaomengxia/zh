package com.zh.service.basic.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zh.dto.basic.*;
import com.zh.entity.basic.BasicCommandDepartment;
import com.zh.entity.basic.BasicDivisionOrganization;
import com.zh.entity.basic.UserDepartment;
import com.zh.entity.sys.SysUser;
import com.zh.mapper.basic.BasicDivisionOrganizationMapper;
import com.zh.service.basic.BasicCommandDepartmentService;
import com.zh.service.basic.BasicDivisionOrganizationService;
import com.zh.service.basic.UserDepartmentService;
import com.zh.util.file.excel.ExcelHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 赵梦霞
 * @since 2018-12-19
 */
@Service
public class BasicDivisionOrganizationServiceImpl extends ServiceImpl<BasicDivisionOrganizationMapper,
        BasicDivisionOrganization> implements BasicDivisionOrganizationService {
    @Resource
    private BasicDivisionOrganizationMapper basicDivisionOrganizationMapper;
    @Resource
    private BasicCommandDepartmentService basicCommandDepartmentService;

    @Override
    public List<OrganizationAndDepartmentDTO> selectAllOrganizationAndDepertment() {
        List<BasicCommandDepartment> basicCommandDepartments = basicCommandDepartmentService.list();
        List<BasicDivisionOrganization> basicDivisionOrganizations = this.list(new LambdaQueryWrapper<BasicDivisionOrganization>()
                .select(BasicDivisionOrganization::getId, BasicDivisionOrganization::getDivisionName, BasicDivisionOrganization::getCommandId));
        Map<Long,List<BasicDivisionOrganization>> groupMap=basicDivisionOrganizations.stream().collect(Collectors.groupingBy(BasicDivisionOrganization::getCommandId));
        List<OrganizationAndDepartmentDTO> organizationAndDepartmentDTOS=new ArrayList<>();
        for (BasicCommandDepartment basicCommandDepartment:basicCommandDepartments){
            OrganizationAndDepartmentDTO organizationAndDepartmentDTO=new OrganizationAndDepartmentDTO();
            organizationAndDepartmentDTO.setId(basicCommandDepartment.getId());
            organizationAndDepartmentDTO.setName(basicCommandDepartment.getName());
            List<DepartmentDTO> departmentDTOS=new ArrayList<>();
            if (groupMap.containsKey(basicCommandDepartment.getId())){
                List<BasicDivisionOrganization> basicDivisionOrganizations1=groupMap.get(basicCommandDepartment.getId());
                for (BasicDivisionOrganization basicDivisionOrganization:basicDivisionOrganizations1){
                    DepartmentDTO departmentDTO=new DepartmentDTO();
                    departmentDTO.setId(basicDivisionOrganization.getId());
                    departmentDTO.setName(basicDivisionOrganization.getDivisionName());
                    departmentDTOS.add(departmentDTO);
                }
                organizationAndDepartmentDTO.setDepartmentDTOS(departmentDTOS);
            }
            organizationAndDepartmentDTOS.add(organizationAndDepartmentDTO);
        }
        return organizationAndDepartmentDTOS;
    }


    @Override
    public List<DepartmentDTO> selectAllOrganizationAndDepertment1(Long id) {
        List<DepartmentDTO> organizationAndDepartmentDTOS=basicDivisionOrganizationMapper.selectAllCommandDepartmentAndUser(id);
        return organizationAndDepartmentDTOS;
    }

    @Override
    public BasicDivisionOrganizationDTO selectByDepartmentId(Long id) {
        return basicDivisionOrganizationMapper.selectByDepartmentId(id);
    }

    @Override
    public IPage<BasicDivisionOrganizationDTO> selectTree(Page<BasicDivisionOrganizationDTO> page, Long id, String keywords) {
        return basicDivisionOrganizationMapper.selectAll(page, id, keywords);
    }

    @Override
    public void exportExcel(HttpServletResponse response, Long id, String keywords) throws IOException {
        List<BasicDivisionOrganizationDTO> basicDivisionOrganizationDTOS = basicDivisionOrganizationMapper.selectAll(id, keywords);
        ExcelHelper.exportExcel(response, "部门信息", "部门信息", BasicDivisionOrganizationDTO.class, basicDivisionOrganizationDTOS);

    }

    @Override
    public List<BasicDivisionOrganizationDTO> selectAdministrativeDivisionId(Long administrativeDivisionId) {
        return basicDivisionOrganizationMapper.selectAdministrativeDivisionId(administrativeDivisionId);
    }

    @Override
    public boolean checkNameIsSame(BasicDivisionOrganization basicDivisionOrganization) {
        boolean f = false;
        String name = basicDivisionOrganization.getDivisionName();
        Long id = basicDivisionOrganization.getId();
        BasicDivisionOrganizationDTO basicDivisionOrganizationDTO = basicDivisionOrganizationMapper.checkNameIsSame(name);
        if (null != basicDivisionOrganizationDTO) {
            if (id != null && id.equals(basicDivisionOrganizationDTO.getId())) {
                f = true;
            }
        } else {
            f = true;
        }
        return f;
    }

    @Override
    public List<BasicDivisionOrganizationPeopleDTO> selectByIdAndName(Long divisionOrganizationId, String userName) {
        List<BasicDivisionOrganizationPeopleDTO> basicDivisionOrganizationPeopleDTOS = basicDivisionOrganizationMapper.selectByIdAndName(divisionOrganizationId, userName);
        return basicDivisionOrganizationMapper.selectByIdAndName(divisionOrganizationId, userName);
    }

    @Override
    public List<IdAndNameDTO> selectAll() {
        List<BasicDivisionOrganization> basicDivisionOrganizations = this.list(new LambdaQueryWrapper<BasicDivisionOrganization>().select(BasicDivisionOrganization::getId, BasicDivisionOrganization::getDivisionName));
        List<IdAndNameDTO> idAndNameDTOS = new ArrayList<>();
        for (BasicDivisionOrganization basicDivisionOrganization : basicDivisionOrganizations) {
            IdAndNameDTO idAndNameDTO = new IdAndNameDTO();
            idAndNameDTO.setId(basicDivisionOrganization.getId());
            idAndNameDTO.setName(basicDivisionOrganization.getDivisionName());
            idAndNameDTOS.add(idAndNameDTO);
        }
        return idAndNameDTOS;
    }
}
