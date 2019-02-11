package com.zh.service.response;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.api.Result;
import com.zh.entity.response.ResponseAction;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 响应行动 服务类
 * </p>
 *
 * @author 赵梦霞
 * @since 2019-01-04
 */
public interface ResponseActionService extends IService<ResponseAction> {
    IPage<ResponseAction> selectAllPage(Page<ResponseAction> page,String keywords);

    Result selectAllList();
}
