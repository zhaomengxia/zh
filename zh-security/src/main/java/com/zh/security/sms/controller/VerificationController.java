package com.zh.security.sms.controller;

import cn.hutool.core.util.StrUtil;
import com.zh.api.Result;
import com.zh.enums.ExceptionEnum;
import com.zh.exceptions.UnifiedException;
import com.zh.security.sms.code.Code;
import com.zh.security.sms.service.CodeGenerator;
import com.zh.security.sms.service.CodeSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 验证码 controller
 *
 * @author  赵梦霞
 * @since 2018-12-12 14:53

 **/
@RestController
@RequestMapping("/verification")
public class VerificationController {

    @Resource
    private CodeGenerator generator;
    @Resource
    private CodeSender sender;

    @GetMapping("/code")
    public Result generatorCode(String mobile) {
        if (StrUtil.isBlank(mobile)) {
            throw new UnifiedException(ExceptionEnum.MOBILE_IS_BLANK);
        }
        //生成验证码
        Code code = generator.generator(mobile);
        //发送手机验证码
        sender.send(mobile, code.getCode());
        return Result.success(ExceptionEnum.CODE_SEND_SUCCESS);
    }

}
