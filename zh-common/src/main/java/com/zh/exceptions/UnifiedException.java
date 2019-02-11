package com.zh.exceptions;

import com.zh.enums.ExceptionEnum;
import com.zh.enums.IEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 统一异常类
 *
 * @author  赵梦霞
 * @since  2018-08-22 13:42

 **/
@Data
@EqualsAndHashCode(callSuper = false)
@SuppressWarnings("unused")
public class UnifiedException extends RuntimeException {

    private static final long serialVersionUID = -3663785648260762719L;

    public UnifiedException(String message){
        this.message = message;
    }

    public UnifiedException(ExceptionEnum exceptionEnum){
        this.code = exceptionEnum.getCode();
        this.message = exceptionEnum.getMessage();
    }

    private Integer code;

    private String message;

    private IEnum iEnum;
}
