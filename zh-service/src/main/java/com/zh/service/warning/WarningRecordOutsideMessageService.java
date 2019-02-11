package com.zh.service.warning;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zh.dto.warning.WarningRecordOutsideMessageDTO;
import com.zh.entity.warning.WarningRecordOutsideMessage;

/**
 * <p>
 * 外部预警反馈信息 服务类
 * </p>
 *
 * @author 赵梦霞
 * @since 2019-01-13
 */
public interface WarningRecordOutsideMessageService extends IService<WarningRecordOutsideMessage> {
    WarningRecordOutsideMessageDTO selectAllWarnInsideMessageById(Long id);
}
