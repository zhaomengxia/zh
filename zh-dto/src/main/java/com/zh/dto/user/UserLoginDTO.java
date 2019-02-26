package com.zh.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zh.entity.test2.ZResources;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * 用户登陆成功后数据传输对象
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
    @JsonIgnore
    private Set<ZResources> sysResources;

    /**
     * 用户权限资源列.
     */
    private List<RoleResourceDTO> roleResourceDTOS;

//    @ApiModelProperty(value = "部门列表")
//    @JsonProperty(value = "dept")
//    private List<BasicDivisionOrganization> basicDivisionOrganizations;
}
