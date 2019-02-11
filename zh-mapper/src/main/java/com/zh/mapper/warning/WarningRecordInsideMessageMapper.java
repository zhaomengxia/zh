package com.zh.mapper.warning;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zh.dto.warning.WarningRecordInsideMessageDTO;
import com.zh.entity.warning.WarningRecordInsideMessage;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 内部预警预警反馈信息表 Mapper 接口
 * </p>
 *
 * @author 赵梦霞
 * @since 2019-01-13
 */
public interface WarningRecordInsideMessageMapper extends BaseMapper<WarningRecordInsideMessage> {
    WarningRecordInsideMessageDTO selectAllWarnInsideMessageById(@Param("id") Long id);
}
