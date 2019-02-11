package com.zh.service.warning;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zh.dto.data.MapInfoDTO;
import com.zh.dto.warning.WarningRecordDTO;
import com.zh.entity.warning.WarningRecord;

import java.util.List;

/**
 * <p>
 * 预警记录表 服务类
 * </p>
 *
 * @author 赵梦霞
 * @since 2018-12-27
 */
public interface WarningRecordService extends IService<WarningRecord> {
    IPage<WarningRecordDTO> selectAllWarnRecord(Page<WarningRecordDTO> page, String areaCode, String keywords, Long startTime, Long endTime, Integer value);
    List<WarningRecordDTO> selectAllWarnRecord(String areaCode, String keywords, Long startTime, Long endTime, Integer value);
    boolean updateWarnSattus(Long warnRecordId, Integer status);
    MapInfoDTO getAll();
}
