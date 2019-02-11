package com.zh.service.basic;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zh.dto.basic.BasicDivisionOrganizationPeopleDTO;
import com.zh.entity.basic.UserDepartment;

/**
 * <p>
 * 人员部门表 服务类
 * </p>
 *
 * @author 赵梦霞
 * @since 2018-12-27
 */
public interface UserDepartmentService extends IService<UserDepartment> {
    /**
     * 根据 部门id和 人员名称  获得 某个部门下的 人员信息
     * @return
     */
    IPage<BasicDivisionOrganizationPeopleDTO> selectByIdAndName(Page<BasicDivisionOrganizationPeopleDTO> page, Long divisionOrganizationId, String keywords);

    /**
     * 判断该部门下 是否已经有该成员
     * @return
     */
    boolean checkUser(UserDepartment userDepartment);
}
