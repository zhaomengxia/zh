package com.zh.mapper.warning;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.dto.warning.WarningRecordDTO;
import com.zh.entity.warning.WarningRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 预警记录表 Mapper 接口
 * </p>
 *
 * @author 赵梦霞
 * @since 2018-12-27
 */
public interface WarningRecordMapper extends BaseMapper<WarningRecord> {
    IPage<WarningRecordDTO> selectAllWarnRecord(Page<WarningRecordDTO> page, @Param("areaCode") String areaCode, @Param("keywords") String keywords, @Param("startTime") Long startTime, @Param("endTime") Long endTime,@Param("value") Integer value);
    List<WarningRecordDTO> selectAllWarnRecord(@Param("areaCode") String areaCode, @Param("keywords") String keywords, @Param("startTime") Long startTime, @Param("endTime") Long endTime, @Param("value") Integer value);

}
