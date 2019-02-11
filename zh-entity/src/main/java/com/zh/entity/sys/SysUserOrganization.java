package com.zh.entity.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author  赵梦霞
 * @since 2018-12-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("sys_user_organization")
@ApiModel(value = "SysUserOrganization对象", description = "用户-部门 关联关系表")
public class SysUserOrganization implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "用户表主键")
    @TableField("sys_user_id")
    private Long sysUserId;

    @ApiModelProperty(value = "部门表主键")
    @NotNull(message = "部门为必填项")
    @TableField("organization_id")
    private Long organizationId;

    @ApiModelProperty(value = "职务")
    @NotBlank(message = "职务为必填项")
    @TableField("position")
    private String position;

    @ApiModelProperty(value = "是否责任人")
    @NotNull(message = "是否责任人为必填项")
    @TableField("is_charge")
    private Boolean charge;

}
