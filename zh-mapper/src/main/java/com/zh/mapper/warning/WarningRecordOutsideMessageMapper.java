package com.zh.mapper.warning;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zh.dto.warning.WarningRecordOutsideMessageDTO;
import com.zh.entity.warning.WarningRecordOutsideMessage;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 外部预警反馈信息 Mapper 接口
 * </p>
 *
 * @author 赵梦霞
 * @since 2019-01-13
 */
public interface WarningRecordOutsideMessageMapper extends BaseMapper<WarningRecordOutsideMessage> {
    WarningRecordOutsideMessageDTO selectAllWarnInsideMessageById(@Param("id") Long id);
}
