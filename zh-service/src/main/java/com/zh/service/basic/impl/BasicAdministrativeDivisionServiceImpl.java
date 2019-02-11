package com.zh.service.basic.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zh.api.Result;
import com.zh.dto.basic.BasicAdministrativeDivisionDTO;
import com.zh.entity.basic.BasicAdministrativeDivision;
import com.zh.exceptions.UnifiedException;
import com.zh.mapper.basic.BasicAdministrativeDivisionMapper;
import com.zh.service.basic.BasicAdministrativeDivisionService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 赵梦霞
 * @since 2018-12-19
 */
@Service
public class BasicAdministrativeDivisionServiceImpl extends ServiceImpl<BasicAdministrativeDivisionMapper,
        BasicAdministrativeDivision> implements BasicAdministrativeDivisionService {
    @Resource
    private BasicAdministrativeDivisionMapper basicAdministrativeDivisionMapper;

    @Override
    public Boolean checkGrade(BasicAdministrativeDivision basicAdministrativeDivision) {
        boolean f=false;
        Long parentId=basicAdministrativeDivision.getParentId();
        BasicAdministrativeDivision basicAdministrativeDivision1= this.getById(parentId);
        int type=basicAdministrativeDivision.getType();
        if (type<=basicAdministrativeDivision1.getType()){
            f=false;
            throw new UnifiedException("行政级别过大！");
        }
        else{
            f=true;
        }
        return f;
    }

    @Override
    public List<BasicAdministrativeDivision> checkAreaName(String name) {
        if (StrUtil.isBlank(name)){
            throw new UnifiedException("请传行政区！");
        }
        List<BasicAdministrativeDivision> basicAdministrativeDivisions = this.
                list(new QueryWrapper<BasicAdministrativeDivision>().lambda().eq(BasicAdministrativeDivision::getDivisionName, name));
        if (basicAdministrativeDivisions.size() == 0) {
            throw new UnifiedException("此行政区不在系统内，请先到“基础信息-行政区”中添加！");
        }
        return basicAdministrativeDivisions;
    }

    @Override
    public List<BasicAdministrativeDivisionDTO> selectTree() {
        return this.getChildRole(basicAdministrativeDivisionMapper.selectAll(),-1L);
    }
    @Override
    public IPage<BasicAdministrativeDivisionDTO> queryPage(Page<BasicAdministrativeDivisionDTO> page, String  keywords,Long id) {
        List<BasicAdministrativeDivisionDTO> basicAdministrativeDivisionDTOS1= this.getChildRoleTwo(basicAdministrativeDivisionMapper.selectAll(),id, keywords);
        BasicAdministrativeDivision basicAdministrativeDivision= basicAdministrativeDivisionMapper.selectById(id);
        BasicAdministrativeDivisionDTO basicAdministrativeDivisionDTO=new BasicAdministrativeDivisionDTO();
        BeanUtils.copyProperties(basicAdministrativeDivision, basicAdministrativeDivisionDTO);
        basicAdministrativeDivisionDTO.setName(basicAdministrativeDivision.getDivisionName());

        if (StrUtil.isNotBlank(keywords)) {
            if (StrUtil.isNotBlank(basicAdministrativeDivisionDTO.getName())){
                if (basicAdministrativeDivisionDTO.getName().contains(keywords)){
                    basicAdministrativeDivisionDTOS1.add(basicAdministrativeDivisionDTO);
                }
            }
            if (StrUtil.isNotBlank(basicAdministrativeDivisionDTO.getDivisionCode())){
                if (basicAdministrativeDivisionDTO.getDivisionCode().contains(keywords)){
                    basicAdministrativeDivisionDTOS1.add(basicAdministrativeDivisionDTO);
                }
            }
            if (StrUtil.isNotBlank(basicAdministrativeDivisionDTO.getDivisionGradeName())){
                if (basicAdministrativeDivisionDTO.getDivisionGradeName().contains(keywords)){
                    basicAdministrativeDivisionDTOS1.add(basicAdministrativeDivisionDTO);
                }
            }
            if (StrUtil.isNotBlank(basicAdministrativeDivisionDTO.getParentDivisionName())){
                if (basicAdministrativeDivisionDTO.getParentDivisionName().contains(keywords)){
                    basicAdministrativeDivisionDTOS1.add(basicAdministrativeDivisionDTO);
                }
            }
        }
        else {
            basicAdministrativeDivisionDTOS1.add(basicAdministrativeDivisionDTO);
        }
        //从大到小
        Comparator<BasicAdministrativeDivisionDTO> c1 =
                Comparator.comparing(BasicAdministrativeDivisionDTO::getId);
        basicAdministrativeDivisionDTOS1.sort(c1);

        List<BasicAdministrativeDivisionDTO> collect = basicAdministrativeDivisionDTOS1.stream().skip((page.getCurrent()-1)*page.getSize()).limit(page.getSize()).collect(Collectors.toList());
        page.setRecords(collect);
        page.setTotal(basicAdministrativeDivisionDTOS1.size());
        return  page;
    }

    private List<BasicAdministrativeDivisionDTO> getChildRoleTwo(List<BasicAdministrativeDivisionDTO>
                                                                      basicAdministrativeDivisionDTOS, long id,String keywords) {
        List<BasicAdministrativeDivisionDTO> basicAdministrativeDivisionDTOArrayList = new ArrayList<>();
        for (BasicAdministrativeDivisionDTO basicAdministrativeDivisionDTO : basicAdministrativeDivisionDTOS) {
            //获得父节点为id下的角色的信息
            if (basicAdministrativeDivisionDTO.getParentId() == id) {
                BasicAdministrativeDivisionDTO basicAdministrativeDivisionDTO1=new BasicAdministrativeDivisionDTO();
                if (StrUtil.isNotBlank(keywords)){
                    if (StrUtil.isNotBlank(basicAdministrativeDivisionDTO.getName())){
                        if (basicAdministrativeDivisionDTO.getName().contains(keywords)){
                            basicAdministrativeDivisionDTO1 = basicAdministrativeDivisionDTO;
                        }
                    }
                    if (StrUtil.isNotBlank(basicAdministrativeDivisionDTO.getDivisionCode())){
                        if (basicAdministrativeDivisionDTO.getDivisionCode().contains(keywords)){
                            basicAdministrativeDivisionDTO1 = basicAdministrativeDivisionDTO;
                        }
                    }
                    if (StrUtil.isNotBlank(basicAdministrativeDivisionDTO.getDivisionGradeName())){
                        if (basicAdministrativeDivisionDTO.getDivisionGradeName().contains(keywords)){
                            basicAdministrativeDivisionDTO1 = basicAdministrativeDivisionDTO;
                        }
                    }
                    if (StrUtil.isNotBlank(basicAdministrativeDivisionDTO.getParentDivisionName())){
                        if (basicAdministrativeDivisionDTO.getParentDivisionName().contains(keywords)){
                            basicAdministrativeDivisionDTO1 = basicAdministrativeDivisionDTO;
                        }
                    }
                }
                else{
                    basicAdministrativeDivisionDTO1 = basicAdministrativeDivisionDTO;
                }
                //递归，将此时的id和basicAdministrativeDivisionDTOS再传进getChildRole（List<BasicAdministrativeDivisionDTO>
                // basicAdministrativeDivisionDTOS, long id）
                // 执行该方法
                if (basicAdministrativeDivisionDTO1!=null&&basicAdministrativeDivisionDTO1.getId()!=null) {
                    List<BasicAdministrativeDivisionDTO> basicAdministrativeDivisionDTOS1 =
                            this.getChildRoleTwo(basicAdministrativeDivisionDTOS, basicAdministrativeDivisionDTO1.getId(),keywords);
                    basicAdministrativeDivisionDTOArrayList.addAll(basicAdministrativeDivisionDTOS1);
                    basicAdministrativeDivisionDTOArrayList.add(basicAdministrativeDivisionDTO1);
                }

            }
        }
        return basicAdministrativeDivisionDTOArrayList;
    }



    private List<BasicAdministrativeDivisionDTO> getChildRole(List<BasicAdministrativeDivisionDTO>
                                                                      basicAdministrativeDivisionDTOS, long id) {
        List<BasicAdministrativeDivisionDTO> basicAdministrativeDivisionDTOArrayList = new ArrayList<>();
        for (BasicAdministrativeDivisionDTO basicAdministrativeDivisionDTO : basicAdministrativeDivisionDTOS) {
            //获得父节点为id下的角色的信息
            if (basicAdministrativeDivisionDTO.getParentId() == id) {
                BasicAdministrativeDivisionDTO basicAdministrativeDivisionDTO1 = basicAdministrativeDivisionDTO;
                //递归，将此时的id和basicAdministrativeDivisionDTOS再传进getChildRole（List<BasicAdministrativeDivisionDTO>
                // basicAdministrativeDivisionDTOS, long id）
                 // 执行该方法
                List<BasicAdministrativeDivisionDTO> basicAdministrativeDivisionDTOS1 =
                        this.getChildRole(basicAdministrativeDivisionDTOS, basicAdministrativeDivisionDTO1.getId());

                basicAdministrativeDivisionDTO1.setChildren(basicAdministrativeDivisionDTOS1);
                basicAdministrativeDivisionDTOArrayList.add(basicAdministrativeDivisionDTO1);
            }
        }
        return basicAdministrativeDivisionDTOArrayList;
    }

    @Override
    public List<BasicAdministrativeDivisionDTO> selectByParentId(Long parentId) {
        return basicAdministrativeDivisionMapper.selectByParentId(parentId);
    }

    @Override
    public boolean checkNameIsSame(BasicAdministrativeDivision basicAdministrativeDivision) {
        boolean f=false;
        String name=basicAdministrativeDivision.getDivisionName();
        Long id =basicAdministrativeDivision.getId();
       BasicAdministrativeDivisionDTO  basicAdministrativeDivisionDTO=basicAdministrativeDivisionMapper.selectByName(name);
       if (null!=basicAdministrativeDivisionDTO){
           if (id!=null&&id.equals(basicAdministrativeDivisionDTO.getId())){
               f=true;
           }
       }else{
           f=true;
       }
        return f;
    }

    @Override
    public boolean checkCodeIsSame(BasicAdministrativeDivision basicAdministrativeDivision) {
        boolean f=false;
        String code=basicAdministrativeDivision.getDivisionCode();
        Long id =basicAdministrativeDivision.getId();
        BasicAdministrativeDivisionDTO  basicAdministrativeDivisionDTO=basicAdministrativeDivisionMapper.checkCodeIsSame(code);
        if (null!=basicAdministrativeDivisionDTO){
            if (id!=null&&id.equals(basicAdministrativeDivisionDTO.getId())){
                f=true;
            }
        }else{
            f=true;
        }
        return f;
    }
}
