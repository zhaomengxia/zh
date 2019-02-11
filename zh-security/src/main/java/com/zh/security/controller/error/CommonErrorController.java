package com.zh.security.controller.error;

import com.zh.enums.ExceptionEnum;
import com.zh.exceptions.UnifiedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author  赵梦霞
 * @since 2018-11-08 10:08

 **/
@RestController
public class CommonErrorController {

    /**
     * @author  赵梦霞
     * @Description 无权限跳转
     * @since 2018/11/8 10:08
     **/
    @GetMapping("/authentication/forbidden")
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public void forbidden() {
        throw new UnifiedException(ExceptionEnum.ACCESS_FORBIDDEN);
    }

    /**
     * @author  赵梦霞
     * @Description 404
     * @since 2018/12/14 14:28
     **/
    @GetMapping("/unknown")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void notFound() {
        throw new UnifiedException(ExceptionEnum.ACCESS_NOT_FOUND);
    }

}
