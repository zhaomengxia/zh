package com.zh.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * 用户新增/修改数据传输类
 **/
@Data
@ApiModel
public class SysUserInertOrUpdateDTO implements Serializable {

    private static final long serialVersionUID = -6428628881514294089L;

    private Long id;

    @NotBlank(message = "用户名为必填项")
    @ApiModelProperty(value = "用户名")
    private String name;

    @ApiModelProperty(value = "用户密码")
    @JsonIgnore
    private String password;

    @ApiModelProperty(value = "手机号")
    @NotBlank(message = "手机号为必填项")
    private String mobile;

    @ApiModelProperty(value = "邮箱地址")
    private String email;

    @ApiModelProperty(value = "生日")
    private LocalDate birthday;

    @ApiModelProperty(value = "学历")
    private String education;

    @ApiModelProperty(value = "性别1：男2：女3：不男不女")
    @NotNull(message = "性别为必填项")
    private Integer gender;

    @ApiModelProperty(value = "专业")
    private String profession;

    @ApiModelProperty(value = "头像链接")
    private String photo;

    @ApiModelProperty(value = "毕业学院")
    private String college;

    @ApiModelProperty(value = "紧急联系人")
    private String urgentPerson;

    @ApiModelProperty(value = "紧急联系人联系方式")
    private String urgentContact;

    @ApiModelProperty(value = "角色主键字符串用','分割")
    @NotBlank(message = "角色为必填项")
    private String roles;

}
