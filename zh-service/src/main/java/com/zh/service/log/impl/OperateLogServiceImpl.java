package com.zh.service.log.impl;

import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import cn.afterturn.easypoi.excel.entity.vo.BaseEntityTypeConstants;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.zh.dto.log.operate.OperateLogQuery;
import com.zh.entity.log.OperateLog;
import com.zh.mapper.log.OperateLogMapper;
import com.zh.service.log.OperateLogService;
import com.zh.util.DateUtil;
import com.zh.util.file.excel.ExcelHelper;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 操作日志表 服务实现类
 * </p>
 *
 * @author  赵梦霞
 * @since 2018-12-18
 */
@Service
public class OperateLogServiceImpl extends ServiceImpl<OperateLogMapper, OperateLog> implements OperateLogService {

    @Override
    public IPage<OperateLog> queryPage(Page<OperateLog> page, OperateLogQuery query) {
        return this.page(page, assembleCondition(query));
    }

    @Override
    public List<OperateLog> queryList(OperateLogQuery query) {
        return this.list(assembleCondition(query));
    }

    @Override
    public void export(HttpServletResponse response, OperateLogQuery query) throws IOException {
        //构造excel
        List<ExcelExportEntity> entity = Lists.newArrayList();
        ExcelExportEntity excelentity = new ExcelExportEntity("序号", "serial", 20);
        excelentity.setType(BaseEntityTypeConstants.DOUBLE_TYPE);
        entity.add(excelentity);
        entity.add(new ExcelExportEntity("角色", "roles", 20));
        entity.add(new ExcelExportEntity("操作时间", "operateTime", 30));
        entity.add(new ExcelExportEntity("登陆ip", "ip", 20));
        entity.add(new ExcelExportEntity("操作动作", "operateActive", 30));
        //查询数据
        List<OperateLog> logs = this.queryList(query);
        List<Map<String, Object>> data = Lists.newArrayList();
        for (int i = 0; i < logs.size(); i++) {
            HashMap<String, Object> map = new HashMap<>(6);
            map.put("serial", i + 1);
            map.put("roles", logs.get(i).getOperateRole());
            map.put("operateTime", DateUtil.parse("yyyy-MM-dd HH:mm:ss", DateUtil.timestamp2LocalDateTime(logs.get(i).getOperateTime())));
            map.put("ip", logs.get(i).getOperatorIp());
            map.put("operateActive", logs.get(i).getOperateActive());
            data.add(map);
        }
        ExcelHelper.exportExcel(response, entity, data, "操作日志", "操作日志");

    }

    private LambdaQueryWrapper<OperateLog> assembleCondition(OperateLogQuery query) {
        LambdaQueryWrapper<OperateLog> queryWrapper = new LambdaQueryWrapper<>();
        if (StrUtil.isNotBlank(query.getOperator())) {
            queryWrapper.like(OperateLog::getOperator, query.getOperator());
        }
        if (StrUtil.isNotBlank(query.getOperateRole())) {
            queryWrapper.like(OperateLog::getOperateRole, query.getOperateRole());
        }
        if (query.getStart() != null) {
            queryWrapper.ge(OperateLog::getOperateTime, query.getStart());
        }
        if (query.getEnd() != null) {
            queryWrapper.le(OperateLog::getOperateTime, query.getEnd());
        }

        queryWrapper.orderByDesc(OperateLog::getOperateTime);

        return queryWrapper;
    }
}
