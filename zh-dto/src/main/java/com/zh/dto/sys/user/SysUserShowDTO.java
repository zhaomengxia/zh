package com.zh.dto.sys.user;

import com.zh.entity.sys.SysRoles;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

/**
 * 展示用户信息数据传输类
 *
 * @author  赵梦霞
 * @since 2018-12-20 14:20

 **/
@Data
public class SysUserShowDTO {

    private Long id;

    @ApiModelProperty(value = "用户名")
    private String name;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "邮箱地址")
    private String email;

    @ApiModelProperty(value = "生日")
    private LocalDate birthday;

    @ApiModelProperty(value = "学历")
    private String education;

    @ApiModelProperty(value = "性别1：男2：女3：不男不女")
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

    /**
     * 用户角色
     */
    private List<SysRoles> roles;

    /**
     * 用户组织
     */
    private List<OrganizationDTO> organizations;

}
