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
 * 角色-资源 关联表
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
@TableName("sys_roles_resources")
@ApiModel(value = "SysRolesResources对象", description = "角色-资源 关联表")
public class SysRolesResources implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "角色表主键")
    @TableField("sys_roles_id")
    private Long sysRolesId;

    @ApiModelProperty(value = "资源表主键")
    @TableField("sys_resources_id")
    private Long sysResourcesId;

    @ApiModelProperty(value = "是否具有权限")
    @TableField("has_persission")
    private Boolean hasPersission;
}
