package com.zh.service.basic.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zh.api.Result;
import com.zh.entity.basic.CountryEconomic;
import com.zh.entity.basic.Polder;
import com.zh.mapper.basic.CountryEconomicMapper;
import com.zh.service.basic.CountryEconomicService;
import com.zh.util.file.excel.ExcelHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 赵梦霞
 * @since 2019-01-07
 */
@Service
public class CountryEconomicServiceImpl extends ServiceImpl<CountryEconomicMapper, CountryEconomic> implements CountryEconomicService {

    @Resource
    CountryEconomicMapper countryEconomicMapper;

    @Override
    public Result getListPage(String keyword) {
        List<CountryEconomic> list = countryEconomicMapper.getList(keyword);
//        Map<Integer, List<CountryEconomic>> collect =
//                list.stream().collect(Collectors.groupingBy(CountryEconomic::getType));
//        List<CountryEconomicDTO> countryEconomicDTOS = Lists.newArrayList();
//        collect.forEach((type, countryEconomicS)->{
//            CountryEconomicDTO countryEconomicDTO = new CountryEconomicDTO();
//            countryEconomicDTO.setType(type);
//            countryEconomicDTO.setTypeName(CountryEconomicEnum.getName(type));
//            countryEconomicDTOS.add(countryEconomicDTO);
//        });
        return Result.success(list);
    }

    @Override
    public void exportExcel(HttpServletResponse response, String keywords) throws IOException {
        List<CountryEconomic> list=new ArrayList<>();
        List<CountryEconomic> list1 = countryEconomicMapper.getList(keywords);
        int i=1;
        for (CountryEconomic countryEconomic:list1){
            countryEconomic.setSerialNumber(i);
            list.add(countryEconomic);
            i++;
        }
        ExcelHelper.exportExcel(response, "县(市、区)社会经济", "县(市、区)社会经济", CountryEconomic.class, list);
    }
}
