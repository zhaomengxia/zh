package com.zh.service.log;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zh.dto.log.operate.OperateLogQuery;
import com.zh.entity.log.OperateLog;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 操作日志表 服务类
 * </p>
 *
 * @author  赵梦霞
 * @since 2018-12-18
 */
public interface OperateLogService extends IService<OperateLog> {

    /**
     * @param page  分页对象
     * @param query 查询条件
     * @author  赵梦霞
     * @Description 根据查询条件查询操作日志(分页展示)
     * @since 2018/12/18 16:55
     **/
    IPage<OperateLog> queryPage(Page<OperateLog> page, OperateLogQuery query);

    /**
     * @param query 查询条件
     * @author  赵梦霞
     * @Description 根据查询条件查询操作日志
     * @since 2018/12/18 17:28
     **/
    List<OperateLog> queryList(OperateLogQuery query);

    /**
     * @author  赵梦霞
     * @Description 导出excel
     * @since 2018/12/24 10:25
     **/
    void export(HttpServletResponse response,OperateLogQuery query) throws IOException;
}
