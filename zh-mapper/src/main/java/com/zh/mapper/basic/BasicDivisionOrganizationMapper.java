package com.zh.mapper.basic;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.dto.basic.BasicDivisionOrganizationDTO;
import com.zh.dto.basic.BasicDivisionOrganizationPeopleDTO;
import com.zh.dto.basic.DepartmentDTO;
import com.zh.entity.basic.BasicDivisionOrganization;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author 赵梦霞
 * @since 2018-12-19
 */
public interface BasicDivisionOrganizationMapper extends BaseMapper<BasicDivisionOrganization> {

    List<DepartmentDTO> selectAllCommandDepartmentAndUser(@Param("id") Long id);

    BasicDivisionOrganizationDTO selectByDepartmentId(@Param("id") Long id);
    /**
     * 获得所有的部门信息
     * @return
     */
    IPage<BasicDivisionOrganizationDTO> selectAll(Page<BasicDivisionOrganizationDTO> page, @Param("id") Long id, @Param("keywords") String keywords);

    List<BasicDivisionOrganizationDTO> selectAll(@Param("id") Long id, @Param("keywords") String keywords);

    /**
     * 通过行政区 查找 该行政区下的所有部门
     */
    List<BasicDivisionOrganizationDTO> selectAdministrativeDivisionId(@Param("administrativeDivisionId") Long administrativeDivisionId);

    /**
     * 根据 部门名称查找  防汛部门表 用来验证重名
     */
    BasicDivisionOrganizationDTO checkNameIsSame(@Param("name") String name);
    /**
     * 根据 部门id和 人员名称  获得 某个部门下的 人员信息
     */
    List<BasicDivisionOrganizationPeopleDTO> selectByIdAndName(@Param("divisionOrganizationId")Long divisionOrganizationId, @Param("userName")String userName);
}
