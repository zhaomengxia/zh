package com.zh.api;

import com.zh.enums.ApiEnum;
import com.zh.enums.IEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 返回结果
 *
 * @author  赵梦霞
 * @since 2018-08-23 16:59

 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "Result对象",description = "统一返回消息实体")
@SuppressWarnings("unused")
public class Result<T> {

    /**
     * 状态码
     */
    @ApiModelProperty(value = "状态码1:成功0:失败")
    private Integer code;

    /**
     * 业务码
     */
    @ApiModelProperty(value = "业务码")
    private Integer subCode;

    /**
     * 描述信息
     */
    @ApiModelProperty(value = "描述信息")
    private String message;

    /**
     * 数据对象
     */
    @ApiModelProperty(value = "数据对象")
    private T data;


    private Result(IEnum iEnum,T data){
        this.code = iEnum.getCode();
        this.message = iEnum.getMessage();
        this.data = data;
    }

    private Result(Integer code,IEnum iEnum,T data){
        this.code = code;
        this.subCode = iEnum.getCode();
        this.message = iEnum.getMessage();
        this.data = data;
    }

    private Result(Integer code,String message,T data){
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static Result success(){
        return new Result<>(ApiEnum.SUCCESS, null);
    }

    public static Result success(String message){
        return success(message,null);
    }

    public static <T>Result<T> success(T data){
        return new Result<>(ApiEnum.SUCCESS, data);
    }

    public static <T>Result<T> success(String message,T data){
        return new Result<>(ApiEnum.SUCCESS.getCode(), message, data);
    }

    public static <T>Result<T> success(Integer code,String message,T data){
        return new Result<>(code, message, data);
    }
    public static <T>Result<T> success(IEnum iEnum,T data){
        return new Result<>(ApiEnum.SUCCESS.getCode(),iEnum, data);
    }
    public static <T>Result<T> success(IEnum iEnum){
        return new Result<>(ApiEnum.SUCCESS.getCode(),iEnum, null);
    }

    public static Result fail(){
        return new Result<>(ApiEnum.FAIL,null);
    }
    public static Result fail(String message){
        return fail(message,null);
    }

    public static <T>Result<T> fail(T data){
        return new Result<>(ApiEnum.FAIL,data);
    }

    public static <T>Result<T> fail(String message,T data){
        return new Result<>(ApiEnum.FAIL.getCode(),message,data);
    }
    public static <T>Result<T> fail(IEnum iEnum,T data){
        return new Result<>(ApiEnum.FAIL.getCode(),iEnum,data);
    }
    public static <T>Result<T> fail(IEnum iEnum){
        return new Result<>(ApiEnum.FAIL.getCode(),iEnum,null);
    }

    public static <T>Result<T> fail(Integer code,String message,T data){
        return new Result<>(code,message,data);
    }

    public static <T>Result<T> restResult(IEnum iEnum, T data){
        return new Result<>(iEnum.getCode(), iEnum.getMessage(),data);
    }
    public static Result restResult(IEnum iEnum){
        return new Result<>(iEnum.getCode(), iEnum.getMessage(),null);
    }

}
