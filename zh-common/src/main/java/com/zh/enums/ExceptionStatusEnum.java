package com.zh.enums;

import lombok.Getter;

/**
 * 异常记录状态枚举类
 *
 * @author  hahaha
 * @date 2019-01-14 10:15

 **/
@Getter
public enum ExceptionStatusEnum {
    /**
     *
     */

    REPORT(1, "新上报"),
    HANDLE(2, "处置中"),
    REVIEW(3, "待审核"),
    COMPLETE(4, "完成"),


    NEW_TASK(1, "待办任务"),
    COMPLETE_TASK(2, "完成任务"),

    ;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 描述
     */
    private String desc;

    ExceptionStatusEnum(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
    }
}
