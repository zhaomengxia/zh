package com.zh.enums;

import lombok.Getter;

/**
 * 异常枚举类
 *
 * @author  hahaha
 * @since 2018-08-23 16:19

 **/
@Getter
public enum ExceptionEnum implements IEnum {

    /**
     * 登陆相关
     */
    LOGIN_SUCCESS(200, "登陆成功"),
    LOGOUT_SUCCESS(200, "登出成功"),
    NEED_LOGIN(201, "请先登陆"),
    TOKEN_ALLOW(202, "token可继续使用"),
    user_not_exist(203, "用户不存在"),
    ACCESS_FORBIDDEN(403, "无权限访问此页面，请联系那个最帅的开发"),
    ACCESS_NOT_FOUND(404, "有没有这个路径心里没点数吗？"),
    TOKEN_INVALID(405, "token无效/过期"),
    CHANGE_PASSWORD_SUCCESS(406, "密码修改成功"),
    CHANGE_PASSWORD_FAIL(407, "密码修改失败"),
    USER_ALREADY_EXIST(408, "用户名已存在"),
    OLD_PASSWORD_NOT_MATCH(409, "旧密码不匹配"),
    RESET_PASSWORD_SUCCESS(410, "重置用户密码成功"),
    RESET_PASSWORD_FAIL(411, "重置密码失败"),
    /**
     * 手机号验证码登陆
     */
    CODE_SEND_SUCCESS(605, "验证码发送成功"),
    MOBILE_IS_BLANK(600, "手机号为必填项"),
    code_not_exist(601, "验证码不存在"),
    CODE_VALUE_IS_BLANK(602, "验证码的值不能为空"),
    CODE_VALUE_IS_EXPRIED(603, "验证码已过期,请重新获取"),
    CODE_VALUE_NOT_MATCH(604, "验证码不匹配"),

    /**
     * 文件上传
     */
    FILE_UPLOAD_SUCCESS(100, "文件上传成功"),
    FILE_IS_BLANK(101, "文件不能为空!"),
    FILE_NOT_RESOLVE(102, "无法解析文件类型!"),
    FILE_IS_EMPTY(103, "至少上传一个文件!"),
    FILE_UPLOAD_FAIL(104, "文件上传失败!"),
    FILE_NOT_FOUND(105, "文件不存在!"),
    FOLDER_GENERATOR_FAIL(106, "文件夹生成失败!"),

    /**
     * CRUD
     */
    SAVE_OR_UPDATE_SUCCESS(301, "新增修改成功"),
    SAVE_OR_UPDATE_FAIL(302, "新增修改失败"),
    SAVE_SUCCESS(303, "新增修改成功"),
    SAVE_FAIL(304, "新增修改失败"),
    UPDATE_SUCCESS(305, "新增修改成功"),
    UPDATE_FAIL(306, "新增修改失败"),
    DELETE_SUCCESS(307, "删除成功"),
    DELETE_FAIL(308, "删除失败"),
    DELETE_BATCH_SUCCESS(309, "删除成功"),
    DELETE_BATCH_FAIL(310, "删除失败"),
    LACK_PARAMETER(311, "缺少必要参数"),

    /**
     * excel
     */
    EXCEL_IMPORT_SUCCESS(701, "excel导入成功"),
    EXCEL_IMPORT_FAIL(702, "excel导入失败"),
    EXCEL_EXPORT_SUCCESS(703, "excel导出失败"),
    EXCEL_EXPORT_FAIL(704, "excel导出失败");

    private Integer code;

    private String message;

    ExceptionEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
