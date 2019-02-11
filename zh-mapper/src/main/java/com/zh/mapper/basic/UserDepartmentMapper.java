package com.zh.mapper.basic;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.dto.basic.BasicDivisionOrganizationPeopleDTO;
import com.zh.entity.basic.UserDepartment;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 人员部门表 Mapper 接口
 * </p>
 *
 * @author 赵梦霞
 * @since 2018-12-27
 */
public interface UserDepartmentMapper extends BaseMapper<UserDepartment> {
    IPage<BasicDivisionOrganizationPeopleDTO> selectByIdAndName(Page<BasicDivisionOrganizationPeopleDTO> page, @Param("divisionOrganizationId") Long divisionOrganizationId,
                                                                @Param("keywords") String keywords);
}
