package com.zh.entity.test;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2019-02-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("spt_fallback_db")
@ApiModel(value="SptFallbackDb对象", description="")
public class SptFallbackDb implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("xserver_name")
    private String xserverName;

    @TableField("xdttm_ins")
    private LocalDateTime xdttmIns;

    @TableField("xdttm_last_ins_upd")
    private LocalDateTime xdttmLastInsUpd;

    @TableField("xfallback_dbid")
    private Integer xfallbackDbid;

    @TableField("name")
    private String name;

    @TableField("dbid")
    private Integer dbid;

    @TableField("status")
    private Integer status;

    @TableField("version")
    @Version
    private Integer version;


}
