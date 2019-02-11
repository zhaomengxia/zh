package com.zh.service.flood.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zh.dto.basic.UserDTO;
import com.zh.dto.flood.FloodPreventionOwnerDTO;
import com.zh.entity.basic.UserDepartment;
import com.zh.entity.flood.FloodPreventionOwner;
import com.zh.exceptions.UnifiedException;
import com.zh.mapper.flood.FloodPreventionOwnerMapper;
import com.zh.service.flood.FloodPreventionOwnerService;
import com.zh.util.file.excel.ExcelHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 防汛责任人表 服务实现类
 * </p>
 *
 * @author 赵梦霞
 * @since 2019-01-04
 */
@Service
public class FloodPreventionOwnerServiceImpl extends ServiceImpl<FloodPreventionOwnerMapper, FloodPreventionOwner> implements FloodPreventionOwnerService {

    @Resource
    private FloodPreventionOwnerMapper floodPreventionOwnerMapper;
    @Override
    public IPage<FloodPreventionOwnerDTO> selectByTypeIdPage(Page<FloodPreventionOwnerDTO> page, Long typeId, String keywords) {
        return floodPreventionOwnerMapper.selectByTypeIdPage(page,typeId,keywords);
    }

    @Override
    public void exportExcel(HttpServletResponse response, Long typeId, String keywords) throws IOException {
        List<FloodPreventionOwnerDTO> floodPreventionOwnerDTOS=new ArrayList<>();
        List<FloodPreventionOwnerDTO> floodPreventionOwnerDTOS1 = floodPreventionOwnerMapper.selectByTypeIdPage(typeId,keywords);
        for (FloodPreventionOwnerDTO floodPreventionOwnerDTO:floodPreventionOwnerDTOS1){
            if (floodPreventionOwnerDTO.getGender().equals("1")){
                    floodPreventionOwnerDTO.setGender("男");
            }
            else if (floodPreventionOwnerDTO.getGender().equals("2")){
                floodPreventionOwnerDTO.setGender("女");
            }
            floodPreventionOwnerDTOS.add(floodPreventionOwnerDTO);
        }
        ExcelHelper.exportExcel(response, "防汛责任人", "防汛责任人", FloodPreventionOwnerDTO.class, floodPreventionOwnerDTOS);
    }

    @Override
    public List<UserDTO> selectAllUserByTypeId(Long typeId) {
        return floodPreventionOwnerMapper.selectAllUserByTypeId(typeId);
    }

    @Override
    public boolean checkDutyUser(FloodPreventionOwner floodPreventionOwner) {
        Long dutyId=floodPreventionOwner.getTypeId();
        Long userId=floodPreventionOwner.getUserId();
        Long id=floodPreventionOwner.getId();
        if (dutyId==null){
            throw new UnifiedException("请传责任类型！");
        }
        if (userId==null){
            throw new UnifiedException("请传用户！");
        }
        List<FloodPreventionOwner> floodPreventionOwners=this.list();
        Map<Long,List<FloodPreventionOwner>> map=floodPreventionOwners.stream().collect(Collectors.groupingBy(FloodPreventionOwner::getTypeId));
        List<FloodPreventionOwner> floodPreventionOwners1=new ArrayList<>();
        if (map.containsKey(dutyId)){
            floodPreventionOwners1=map.get(dutyId);
        }
        boolean f=true;
        if (floodPreventionOwners1.size()>0){
            Map<Long,FloodPreventionOwner> map1=floodPreventionOwners1.stream().collect(Collectors.toMap(a->a.getUserId(),a->a));
            if (id==null) {
                if (map1.containsKey(userId)) {
                    f = false;
                    throw new UnifiedException("该类型下已有该责任人！");
                }

            }
            else if (id!=null){
                if (map1.containsKey(userId)&&!id.equals(map1.get(userId).getId())){
                    f = false;
                    throw new UnifiedException("该类型下已有该责任人！");
                }
            }
        }
        return f;
    }
}
