package com.zh.service.warning;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zh.dto.warning.WarningRecordInsideMessageDTO;
import com.zh.entity.warning.WarningRecordInsideMessage;

/**
 * <p>
 * 内部预警预警反馈信息表 服务类
 * </p>
 *
 * @author 赵梦霞
 * @since 2019-01-13
 */
public interface WarningRecordInsideMessageService extends IService<WarningRecordInsideMessage> {
    WarningRecordInsideMessageDTO selectAllWarnInsideMessageById( Long id);
}
