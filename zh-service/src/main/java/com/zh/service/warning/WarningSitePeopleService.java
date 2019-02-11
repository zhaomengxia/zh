package com.zh.service.warning;

import com.zh.api.Result;
import com.zh.entity.warning.WarningSitePeople;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 赵梦霞
 * @since 2019-01-11
 */
public interface WarningSitePeopleService extends IService<WarningSitePeople> {

    Result getSiteWarningPeople(Long siteId);

    Result addOrUpdate(String headQuarters, String departments, Long siteId);
}
