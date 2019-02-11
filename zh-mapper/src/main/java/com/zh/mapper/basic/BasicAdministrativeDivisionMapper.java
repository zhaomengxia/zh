package com.zh.mapper.basic;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.dto.basic.BasicAdministrativeDivisionDTO;
import com.zh.entity.basic.BasicAdministrativeDivision;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 赵梦霞
 * @since 2018-12-19
 */
public interface BasicAdministrativeDivisionMapper extends BaseMapper<BasicAdministrativeDivision> {
    /**
     * 这里 是 删除 行政区时 将 传过来的 主键 ID作为父节点 去查库，如果 存在将其作为父节点的数据 则提示请先删其下级
     * @param parentId
     * @return
     */
    List<BasicAdministrativeDivisionDTO> selectByParentId(@Param("parentId") Long parentId);

    /**
     * 这里是为了 得到 所有未删除的数据 并将这些数据存在指定的dto里
     * @return
     */
    List<BasicAdministrativeDivisionDTO> selectAll();

    /**
     * 这里  是 判断 行政区重名时用到的
     * @param name
     * @return
     */
    BasicAdministrativeDivisionDTO selectByName(@Param("name") String name);

    /**
     * 此方法 是用来 通过 行政区划名称搜索 并进行分页
     * @param page
     * @return
     */
    IPage<BasicAdministrativeDivisionDTO> selectAdministrativeDivisionPage(Page<BasicAdministrativeDivisionDTO> page);

    /**
     * 此方法  是 用来验证编码是否已存在
     * @param code
     * @return
     */
    BasicAdministrativeDivisionDTO  checkCodeIsSame(@Param("code") String code);
}
