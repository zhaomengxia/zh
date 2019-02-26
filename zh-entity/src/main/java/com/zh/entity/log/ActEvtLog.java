package com.zh.entity.log;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

/**
 * <p>
 * 
 * </p>
 *
 * @author  hahaha 
 * @since 2019-02-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("act_evt_log")
@ApiModel(value="ActEvtLog对象", description="")
public class ActEvtLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("operator")
    private String operator;

    @TableField("operator_role")
    private String operatorRole;

    @TableField("operate_time")
    private Long operateTime;

    @TableField("operate_active")
    private String operateActive;
    @TableField("operator_ip")
    private String operatorIp;
    @TableField(value = "is_deleted", fill = FieldFill.INSERT)
    @TableLogic
    private Boolean deleted;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField(value = "execute_time")
    private Long executeTime;

}
