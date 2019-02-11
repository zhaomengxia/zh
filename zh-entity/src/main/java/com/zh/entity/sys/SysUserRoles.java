package com.zh.entity.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * <p>
 * 用户-角色 关联表
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
@TableName("sys_user_roles")
@ApiModel(value="SysUserRoles对象", description="用户-角色 关联表")
public class SysUserRoles implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "用户表主键")
    @TableField("sys_user_id")
    private Long sysUserId;

    @ApiModelProperty(value = "角色表主键")
    @TableField("sys_roles_id")
    private Long sysRolesId;


}
