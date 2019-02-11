package com.zh.entity.sys;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zh.entity.basic.BasicDivisionOrganization;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author  赵梦霞
 * @since 2018-12-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("sys_user")
@ApiModel(value = "SysUser对象", description = "用户表")
public class SysUser implements UserDetails, Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "用户名")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "用户密码")
    @TableField("password")
    @JsonIgnore
    private String password;

    @ApiModelProperty(value = "手机号")
    @TableField("mobile")
    private String mobile;

    @ApiModelProperty(value = "邮箱地址")
    @TableField("email")
    private String email;

    @ApiModelProperty(value = "生日")
    @TableField("birthday")
    private LocalDate birthday;

    @ApiModelProperty(value = "学历")
    @TableField("education")
    private String education;

    @ApiModelProperty(value = "性别1：男2：女3：不男不女")
    @TableField("gender")
    private Integer gender;

    @ApiModelProperty(value = "专业")
    @TableField("profession")
    private String profession;

    @ApiModelProperty(value = "头像链接")
    @TableField("photo")
    private String photo;

    @ApiModelProperty(value = "毕业学院")
    @TableField("college")
    private String college;

    @ApiModelProperty(value = "紧急联系人")
    @TableField("urgent_person")
    private String urgentPerson;

    @ApiModelProperty(value = "紧急联系人联系方式")
    @TableField("urgent_contact")
    private String urgentContact;

    @TableField(value = "is_deleted", fill = FieldFill.INSERT)
    @TableLogic
    @ApiModelProperty(hidden = true)
    @JsonIgnore
    private Boolean deleted;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @ApiModelProperty(hidden = true)
    @JsonIgnore
    private LocalDateTime createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(hidden = true)
    @JsonIgnore
    private LocalDateTime updateTime;

    @TableField(exist = false)
    @ApiModelProperty(hidden = true)
    private List<SysRoles> roles;

    @TableField(exist = false)
    @ApiModelProperty(hidden = true)
    private List<BasicDivisionOrganization> basicDivisionOrganizations;

    @Override
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils
                .createAuthorityList(this.roles.parallelStream().map(SysRoles::getRoleName)
                        .toArray(String[]::new));
    }

    @Override
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public String getUsername() {
        return name;
    }

    @Override
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public boolean isEnabled() {
        return !deleted;
    }
}
