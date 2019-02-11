package com.zh.service.basic;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zh.dto.basic.BasicAdministrativeDivisionDTO;
import com.zh.entity.basic.BasicAdministrativeDivision;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 赵梦霞
 * @since 2018-12-19
 */
public interface BasicAdministrativeDivisionService extends IService<BasicAdministrativeDivision> {
    Boolean checkGrade(BasicAdministrativeDivision basicAdministrativeDivision);

    List<BasicAdministrativeDivision> checkAreaName(String name);

    List<BasicAdministrativeDivisionDTO> selectTree();

    IPage<BasicAdministrativeDivisionDTO> queryPage(Page<BasicAdministrativeDivisionDTO> page, String  keywords,Long id);

    List<BasicAdministrativeDivisionDTO> selectByParentId(Long parentId);

    boolean checkNameIsSame(BasicAdministrativeDivision basicAdministrativeDivision);

    boolean checkCodeIsSame(BasicAdministrativeDivision basicAdministrativeDivision);
}
