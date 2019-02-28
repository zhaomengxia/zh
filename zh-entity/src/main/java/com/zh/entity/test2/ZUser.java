package com.zh.entity.test2;


import java.time.LocalDate;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * <p>
 * 
 * </p>
 *
 * @author  hahaha 
 * @since 2019-02-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("z_user")
@ApiModel(value="ZUser对象", description="")
public class ZUser implements UserDetails,Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER)
    protected Long id;

    @TableField("name")
    private String name;

    @TableField("password")
    private String password;

    @TableField("mobile")
    private String mobile;

    @TableField("email")
    private String email;

    @TableField("birthday")
    private LocalDate birthday;

    @TableField("education")
    private String education;

    @TableField("gender")
    private Integer gender;

    @TableField("profession")
    private String profession;

    @TableField("photo")
    private String photo;

    @TableField("college")
    private String college;

    @TableField("urgent_person")
    private String urgentPerson;

    @TableField("urgent_contact")
    private String urgentContact;
    @TableField(exist = false)
    @ApiModelProperty(hidden = true)
    private List<ZRoles> roles;

    @JsonIgnore
    @TableField(value = "is_deleted", fill = FieldFill.INSERT)
    @TableLogic
    @ApiModelProperty(hidden = true)
    private Boolean deleted;

    @JsonIgnore
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @ApiModelProperty(hidden = true)
    private LocalDateTime createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    private LocalDateTime updateTime;

    @TableField("position")
    private String position;

    @TableField("division_id")
    private Integer divisionId;

    @TableField("organization_id")
    private Integer organizationId;

    @Override
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils
                .createAuthorityList(this.roles.parallelStream().map(ZRoles::getRoleName)
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
        return true;
    }
}
