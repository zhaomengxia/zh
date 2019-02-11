package com.zh.service.basic.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zh.dto.basic.BasicDivisionOrganizationPeopleDTO;
import com.zh.entity.basic.UserDepartment;
import com.zh.exceptions.UnifiedException;
import com.zh.mapper.basic.UserDepartmentMapper;
import com.zh.service.basic.UserDepartmentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 人员部门表 服务实现类
 * </p>
 *
 * @author 赵梦霞
 * @since 2018-12-27
 */
@Service
public class UserDepartmentServiceImpl extends ServiceImpl<UserDepartmentMapper, UserDepartment> implements UserDepartmentService {

    @Resource
    private UserDepartmentMapper userDepartmentMapper;
    @Override
    public IPage<BasicDivisionOrganizationPeopleDTO> selectByIdAndName(Page<BasicDivisionOrganizationPeopleDTO> page, Long divisionOrganizationId, String keywords) {
        return userDepartmentMapper.selectByIdAndName(page,divisionOrganizationId,keywords);
    }

    @Override
    public boolean checkUser(UserDepartment userDepartment ) {
        Long id=userDepartment.getId();
        Long organizationId=userDepartment.getOrganizationId();
        Long userId=userDepartment.getUserId();
        if (organizationId==null){
            throw new UnifiedException("请传部门id！");
        }
        if (userId==null){
            throw new UnifiedException("请传用户！");
        }
        List<UserDepartment> userDepartments=this.list();
        Map<Long,List<UserDepartment>> map=userDepartments.stream().collect(Collectors.groupingBy(UserDepartment::getOrganizationId));
        List<UserDepartment> userDepartments1=new ArrayList<>();
        if (map.containsKey(organizationId)){
            userDepartments1=map.get(organizationId);
        }
        boolean f=true;
        if (userDepartments1.size()>0){
            //该部门下 以用户id
            Map<Long,UserDepartment> map1=userDepartments1.stream().collect(Collectors.toMap(a->a.getUserId(),a->a));
            if (id!=null) {
                if (map1.containsKey(userId)&&!map1.get(userId).getId().equals(id)) {
                    f = false;
                    throw new UnifiedException("已有该成员！");
                }
            }
            else{
                if (map1.containsKey(userId)){
                    f = false;
                    throw new UnifiedException("已有该成员！");
                }
            }
        }
        return f;
    }
}
