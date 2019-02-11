package com.zh.dto.login;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zh.entity.basic.BasicDivisionOrganization;
import com.zh.entity.sys.SysResources;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * 用户登陆成功后数据传输对象
 *
 * @author  赵梦霞
 * @since 2018-11-09 9:45

 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserLoginDTO implements Serializable {


    private static final long serialVersionUID = -8724632963235320334L;

    private Long id;

    private String username;

    private String telephoneNumber;

    private String email;

    /**
     * 验证成功后token
     */
    private String token;

    /**
     * 用户权限资源列.
     */
    private Set<SysResources> sysResources;

    @ApiModelProperty(value = "部门列表")
    @JsonProperty(value = "dept")
    private List<BasicDivisionOrganization> basicDivisionOrganizations;
}
