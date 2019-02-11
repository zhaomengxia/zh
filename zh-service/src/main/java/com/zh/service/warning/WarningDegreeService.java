package com.zh.service.warning;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.entity.warning.WarningDegree;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 预警等级表 服务类
 * </p>
 *
 * @author 赵梦霞
 * @since 2018-12-24
 */
public interface WarningDegreeService extends IService<WarningDegree> {

    IPage<WarningDegree> queryPage(Page<WarningDegree> page);

    WarningDegree getById(Long id);
}
