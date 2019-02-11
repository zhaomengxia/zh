package com.zh.dto.sys.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 更改用户密码数据传输类
 *
 * @author  赵梦霞
 * @since 2018-12-20 19:38

 **/
@Data
@ApiModel
public class ChangePasswordDTO {

    @ApiModelProperty(value = "用户id")
    @NotNull(message = "用户id为必填项")
    private Long id;

    @ApiModelProperty(value = "旧密码")
    @NotBlank(message = "旧密码为必填项")
    private String oldPass;

    @ApiModelProperty(value = "新密码")
    @NotBlank(message = "新密码为必填项")
    private String newPass;

}
