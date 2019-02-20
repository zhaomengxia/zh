package com.zh.entity.test;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
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
@TableName("product")
@ApiModel(value="Product对象", description="")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("name")
    private String name;

    @TableField("sort")
    private Integer sort;

    @TableField("price")
    private Double price;

    @TableField("oneprice")
    private Double oneprice;

    @TableField("img")
    private String img;

    @TableField("date")
    private String date;

    @TableField("sale")
    private Integer sale;

    @TableField("face")
    private String face;

    @TableField("body")
    private String body;

    @TableField("length")
    private String length;

    @TableField("quantity")
    private String quantity;

    @TableField("source")
    private String source;


}
