package com.zh.entity.warning;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author 赵梦霞
 * @since 2019-01-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("outer_warning_record")
@ApiModel(value="OuterWarningRecord对象", description="")
public class OuterWarningRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("id")
    private Long id;

    @ApiModelProperty(value = "预警记录id")
    @TableField("warning_record_id")
    private Long warningRecordId;

    @ApiModelProperty(value = "发布人")
    @TableField("publish_person")
    private String publishPerson;

    @ApiModelProperty(value = "发布内容")
    @TableField("publish_content")
    private String publishContent;

    @TableField(value = "is_deleted", fill = FieldFill.INSERT)
    @TableLogic
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    private Boolean deleted;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    private LocalDateTime createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "防汛指挥部")
    @TableField("head_quarters")
    private String headQuarters;

    @ApiModelProperty(value = "防汛负责人")
    @TableField("charge_man")
    private String chargeMan;


}
