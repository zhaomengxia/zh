package com.zh.security.sms.code;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 验证码实体类
 *
 * @author  赵梦霞
 * @since 2018-12-12 14:58

 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Code implements Serializable {

    private static final long serialVersionUID = -5664600163716836898L;

    /**
     * 验证码
     */
    private String code;

    /**
     * 过期时间
     */
    private LocalDateTime expireTime;

    public Code(String code, int expireIn) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }


    /**
     * @author  赵梦霞
     * @Description 判断验证码是否过期
     * @since 2018/12/12 17:20
     **/
    @JsonIgnore
    public boolean isExpried() {
        return LocalDateTime.now().isAfter(expireTime);
    }
}
