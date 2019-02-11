package com.zh.service.basic;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zh.dto.basic.*;
import com.zh.entity.basic.BasicDivisionOrganization;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 赵梦霞
 * @since 2018-12-19
 */
public interface BasicDivisionOrganizationService extends IService<BasicDivisionOrganization> {
    List<OrganizationAndDepartmentDTO> selectAllOrganizationAndDepertment();

    List<DepartmentDTO> selectAllOrganizationAndDepertment1(Long id);

    BasicDivisionOrganizationDTO selectByDepartmentId(Long id);

    /**
     * 获得 某防汛指挥部下的部门信息并分页
     *
     * @param keywords
     * @return
     */
    IPage<BasicDivisionOrganizationDTO> selectTree(Page<BasicDivisionOrganizationDTO> page, Long id, String keywords);

    void exportExcel(HttpServletResponse response, Long id, String keywords) throws IOException;

    /**
     * 根据行政区划 id 获得该行政区划下的所有部门信息
     *
     * @param administrativeDivisionId
     * @return
     */
    List<BasicDivisionOrganizationDTO> selectAdministrativeDivisionId(Long administrativeDivisionId);

    /**
     * 检查重名
     *
     * @param basicDivisionOrganization
     * @return
     */
    boolean checkNameIsSame(BasicDivisionOrganization basicDivisionOrganization);

    /**
     * 根据 部门id和 人员名称  获得 某个部门下的 人员信息
     *
     * @return
     */
    List<BasicDivisionOrganizationPeopleDTO> selectByIdAndName(Long divisionOrganizationId, String userName);

    List<IdAndNameDTO> selectAll();
}
