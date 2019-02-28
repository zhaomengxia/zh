package com.zh.service.excel.impl;

import com.zh.entity.test2.ZUser;
import com.zh.enums.ExceptionEnum;
import com.zh.exceptions.UnifiedException;
import com.zh.service.excel.ImportService;
import com.zh.service.test2.ZUserService;
import com.zh.util.DateUtil;
import com.zh.util.file.excel.ExcelHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.InputStream;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


//
//import cn.hutool.core.util.StrUtil;
//import com.google.common.collect.Lists;
//import com.vortex.entity.basic.*;
//import com.vortex.entity.flood.FloodPreventionOwner;
//import com.vortex.entity.wading.*;
//import com.vortex.enums.ExceptionEnum;
//import com.vortex.exceptions.UnifiedException;
//import com.vortex.service.basic.*;
//import com.vortex.service.excel.ImportService;
//import com.vortex.service.flood.FloodPreventionOwnerService;
//import com.vortex.service.wading.*;
//import com.vortex.util.file.excel.ExcelHelper;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//import java.io.InputStream;
//import java.util.List;
//import java.util.stream.Collectors;
//
///**
// * @author hahaha
// * @since 2018-12-25 11:51
// **/
@Service
public class ImportServiceImpl implements ImportService {
    @Resource
    private ZUserService zUserService;
    @Resource
    private PasswordEncoder passwordEncoder;
    private String defaultPassword = "123456";

    @Override
    public boolean importDike(InputStream inputStream) {
        return false;
    }

    @Override
    public boolean importWater(InputStream inputStream) {
        return false;
    }

    @Override
    public boolean importPond(InputStream inputStream) {
        return false;
    }

    @Override
    public boolean importRoad(InputStream inputStream) {
        return false;
    }

    @Override
    public boolean importBridge(InputStream inputStream) {
        return false;
    }

    @Override
    public boolean importStation(InputStream inputStream) {
        return false;
    }

    @Override
    public boolean importWhole(InputStream inputStream) {
        return false;
    }

    @Override
    public boolean importPreventionOwner(InputStream inputStream) {
        return false;
    }

    @Override
    public boolean importRiverFlood(InputStream inputStream) {
        return false;
    }

    @Override
    public boolean importPolder(InputStream inputStream) {
        return false;
    }

    @Override
    public boolean importAutoStation(InputStream inputStream) {
        return false;
    }

    @Override
    public boolean importhistoryFloodRecord(InputStream inputStream) {
        return false;
    }

    @Override
    public boolean importEnterprise(InputStream inputStream) {
        return false;
    }

    @Override
    public boolean importCountry(InputStream inputStream) {
        return false;
    }

    @Override
    public boolean importUser(InputStream inputStream) {
        List<List<Object>> list;
        try {
            list = ExcelHelper.importExcel(inputStream, 2, 0);
        } catch (Exception e) {
            throw new UnifiedException(ExceptionEnum.EXCEL_IMPORT_FAIL);
        }
        List<ZUser> zUsers = new ArrayList<>();
        list.stream().forEach(zuser -> {

            try {
                zUsers.add(ZUser.builder().name(zuser.get(0).toString())
                        .mobile(zuser.get(1).toString())
                        .email(zuser.get(2).toString())
                        .birthday(new SimpleDateFormat("yyyy-MM-dd").parse(zuser.get(3).toString()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                        .college(zuser.get(4).toString())
                        .gender(Integer.valueOf(zuser.get(5).toString()))
                        .profession(zuser.get(6).toString())
                        .password(passwordEncoder.encode(defaultPassword))
                        .build());
            } catch (ParseException e) {
                e.printStackTrace();
            }

        });
        return zUserService.saveBatch(zUsers);
    }
//    @Resource
//    private BasicAdministrativeDivisionService basicAdministrativeDivisionService;
//    @Resource
//    private DikeService dikeService;
//    @Resource
//    private WaterService waterService;
//    @Resource
//    private SmallReservoirService smallReservoirService;
//    @Resource
//    private RoadCulvertService roadCulvertService;
//    @Resource
//    private BridgeService bridgeService;
//    @Resource
//    private PumpStationService pumpStationService;
//    @Resource
//    private WholeService wholeService;
//    @Resource
//    private FloodPreventionOwnerService floodPreventionOwnerService;
//    @Resource
//    private RiverFloodProtectionService riverFloodProtectionService;
//    @Resource
//    private PolderService polderService;
//    @Resource
//    private AutoStationService autoStationService;
//    @Resource
//    private HistoryFloodRecordService historyFloodRecordService;
//    @Resource
//    private EnterpriseService enterpriseService;
//    @Resource
//    private CountryEconomicService countryEconomicService;
//    @Override
//    public boolean importDike(InputStream inputStream) {
//        List<List<Object>> list;
//        try {
//            list = ExcelHelper.importExcel(inputStream, 2, 0);
//        } catch (Exception e) {
//            throw new UnifiedException(ExceptionEnum.EXCEL_IMPORT_FAIL);
//        }
//        List<Dike> dikes = Lists.newArrayList();
//        //判断行政区划代码
//        this.judgeCodeLegal(list);
//        list.stream().forEach(dike ->
//                dikes.add(Dike.builder()
//                        .areaCode(dike.get(2).toString())
//                        .name(dike.get(3).toString())
//                        .code(dike.get(4).toString())
//                        .river(dike.get(5).toString())
//                        .riverType(dike.get(6).toString())
//                        .transboundary(dike.get(7).toString())
//                        .type(dike.get(8).toString())
//                        .form(dike.get(9).toString())
//                        .degree(dike.get(10).toString())
//                        .recurrencePeriod(dike.get(11).toString())
//                        .length(dike.get(12).toString())
//                        .standardLength(dike.get(13).toString())
//                        .altitudeSystem(dike.get(14).toString())
//                        .designLevel(dike.get(15).toString())
//                        .heightMax(dike.get(16).toString())
//                        .heightMin(dike.get(17).toString())
//                        .widthMax(dike.get(18).toString())
//                        .widthMin(dike.get(19).toString())
//                        .engineeringTask(dike.get(20).toString())
//                        .startAltitude(dike.get(21).toString())
//                        .endAltitude(dike.get(22).toString())
//                        .description(dike.get(23).toString())
//                        .build())
//        );
//
//        return dikeService.saveBatch(dikes);
//    }
//
//    @Override
//    public boolean importWater(InputStream inputStream) {
//        List<List<Object>> list;
//        try {
//            list = ExcelHelper.importExcel(inputStream, 2, 0);
//        } catch (Exception e) {
//            throw new UnifiedException(ExceptionEnum.EXCEL_IMPORT_FAIL);
//        }
//        //判断行政区划代码
//        this.judgeCodeLegal(list);
//        List<Water> waters = Lists.newArrayList();
//        list.stream().forEach(water ->
//                waters.add(Water.builder()
//                        .areaCode(water.get(2).toString())
//                        .waterName(water.get(3).toString())
//                        .waterCode(water.get(4).toString())
//                        .amount(water.get(5).toString())
//                        .build())
//        );
//        return waterService.saveBatch(waters);
//    }
//
//    @Override
//    public boolean importPond(InputStream inputStream) {
//        List<List<Object>> list;
//        try {
//            list = ExcelHelper.importExcel(inputStream, 2, 0);
//        } catch (Exception e) {
//            throw new UnifiedException(ExceptionEnum.EXCEL_IMPORT_FAIL);
//        }
//        //判断行政区划代码
//        this.judgeCodeLegal(list);
//        List<SmallReservoir> smallReservoirs = Lists.newArrayList();
//        list.stream().forEach(smallReservoir ->
//                smallReservoirs.add(SmallReservoir.builder()
//                        .areaCode(smallReservoir.get(2).toString())
//                        .name(smallReservoir.get(3).toString())
//                        .code(smallReservoir.get(4).toString())
//                        .height(smallReservoir.get(5).toString())
//                        .length(smallReservoir.get(6).toString())
//                        .type(smallReservoir.get(7).toString())
//                        .build())
//        );
//        return smallReservoirService.saveBatch(smallReservoirs);
//    }
//
//    @Override
//    public boolean importRoad(InputStream inputStream) {
//        List<List<Object>> list;
//        try {
//            list = ExcelHelper.importExcel(inputStream, 2, 0);
//        } catch (Exception e) {
//            throw new UnifiedException(ExceptionEnum.EXCEL_IMPORT_FAIL);
//        }
//        //判断行政区划代码
//        this.judgeCodeLegal(list);
//        List<RoadCulvert> roadCulverts = Lists.newArrayList();
//        list.stream().forEach(roadCulvert ->
//                roadCulverts.add(RoadCulvert.builder()
//                        .areaCode(roadCulvert.get(2).toString())
//                        .name(roadCulvert.get(3).toString())
//                        .code(roadCulvert.get(4).toString())
//                        .type(roadCulvert.get(5).toString())
//                        .height(roadCulvert.get(6).toString())
//                        .length(roadCulvert.get(7).toString())
//                        .width(roadCulvert.get(8).toString())
//                        .build())
//        );
//        return roadCulvertService.saveBatch(roadCulverts);
//    }
//
//    @Override
//    public boolean importBridge(InputStream inputStream) {
//        List<List<Object>> list;
//        try {
//            list = ExcelHelper.importExcel(inputStream, 2, 0);
//        } catch (Exception e) {
//            throw new UnifiedException(ExceptionEnum.EXCEL_IMPORT_FAIL);
//        }
//        //判断行政区划代码
//        this.judgeCodeLegal(list);
//        List<Bridge> bridges = Lists.newArrayList();
//        list.stream().forEach(bridge ->
//                bridges.add(Bridge.builder()
//                        .areaCode(bridge.get(2).toString())
//                        .name(bridge.get(3).toString())
//                        .code(bridge.get(4).toString())
//                        .type(bridge.get(5).toString())
//                        .height(bridge.get(6).toString())
//                        .length(bridge.get(7).toString())
//                        .width(bridge.get(8).toString())
//                        .build())
//        );
//        return bridgeService.saveBatch(bridges);
//    }
//
//    @Override
//    public boolean importStation(InputStream inputStream) {
//        List<List<Object>> list;
//        try {
//            list = ExcelHelper.importExcel(inputStream, 2, 0);
//        } catch (Exception e) {
//            throw new UnifiedException(ExceptionEnum.EXCEL_IMPORT_FAIL);
//        }
//        //判断行政区划代码
//        this.judgeCodeLegal(list);
//        List<PumpStation> pumpStations = Lists.newArrayList();
//        list.stream().forEach(bridge ->
//                pumpStations.add(PumpStation.builder()
//                        .areaCode(bridge.get(2).toString())
//                        .name(bridge.get(3).toString())
//                        .code(bridge.get(4).toString())
//                        .type(bridge.get(5).toString())
//                        .power(bridge.get(6).toString())
//                        .flow(bridge.get(7).toString())
//                        .remark(bridge.get(8).toString())
//                        .build())
//        );
//        return pumpStationService.saveBatch(pumpStations);
//    }
//
//    @Override
//    public boolean importWhole(InputStream inputStream) {
//        List<List<Object>> list;
//        try {
//            list = ExcelHelper.importExcel(inputStream, 2, 0);
//        } catch (Exception e) {
//            throw new UnifiedException(ExceptionEnum.EXCEL_IMPORT_FAIL);
//        }
//        //判断行政区划代码
//        this.judgeCodeLegal(list);
//        List<FloodPreventionOwner> floodPreventionOwners = Lists.newArrayList();
//        list.stream().forEach(flood ->
//                floodPreventionOwners.add(FloodPreventionOwner.builder()
//                        .areaCode(flood.get(2).toString())
//                        .company(flood.get(3).toString())
//                        .userId(Long.valueOf(flood.get(4).toString()))
//                        .work(flood.get(5).toString())
//                        .telephone(flood.get(6).toString())
//                        .mark(flood.get(7).toString())
//                        .typeId(Long.valueOf(flood.get(8).toString()))
//                        .build())
//        );
//        return floodPreventionOwnerService.saveBatch(floodPreventionOwners);
//    }
//
//    @Override
//    public boolean importPreventionOwner(InputStream inputStream) {
//        List<List<Object>> list;
//        try {
//            list = ExcelHelper.importExcel(inputStream, 2, 0);
//        } catch (Exception e) {
//            throw new UnifiedException(ExceptionEnum.EXCEL_IMPORT_FAIL);
//        }
//        //判断行政区划代码
//        this.judgeCodeLegal(list);
//        List<Whole> wholes = Lists.newArrayList();
//        list.stream().forEach(whole ->
//                wholes.add(Whole.builder()
//                        .areaCode(whole.get(2).toString())
//                        .dikeLength(whole.get(3).toString())
//                        .waterCount(Long.valueOf(whole.get(4).toString()))
//                        .roadCount(Long.valueOf(whole.get(5).toString()))
//                        .bridgeCount(Long.valueOf (whole.get(6).toString()))
//                        .pumpCount(Long.valueOf(whole.get(7).toString()))
//                        .build())
//        );
//        return wholeService.saveBatch(wholes);
//    }
//
//    @Override
//    public boolean importRiverFlood(InputStream inputStream) {
//        List<List<Object>> list;
//        try {
//            list = ExcelHelper.importExcel(inputStream, 2, 0);
//        } catch (Exception e) {
//            throw new UnifiedException(ExceptionEnum.EXCEL_IMPORT_FAIL);
//        }
//        //判断行政区划代码
//        this.judgeCodeLegal(list);
//        List<RiverFloodProtection> riverFloodProtections = Lists.newArrayList();
//        list.stream().forEach(river ->
//                riverFloodProtections.add(RiverFloodProtection.builder()
//                        .areaCode(river.get(2).toString())
//                        .areaName(river.get(3).toString())
//                        .riverName(river.get(4).toString())
//                        .hasDike(river.get(5).equals("是"))
//                        .sectionName(river.get(6).toString())
//                        .sectionCode(river.get(7).toString())
//                        .floodFrequency(river.get(8).toString())
//                        .level(river.get(9).toString())
//                        .flow(river.get(10).toString())
//                        .build())
//        );
//        return riverFloodProtectionService.saveBatch(riverFloodProtections);
//
//    }
//
//    @Override
//    public boolean importPolder(InputStream inputStream) {
//        List<List<Object>> list;
//        try {
//            list = ExcelHelper.importExcel(inputStream, 2, 0);
//        } catch (Exception e) {
//            throw new UnifiedException(ExceptionEnum.EXCEL_IMPORT_FAIL);
//        }
//        //判断行政区划代码
//        this.judgeCodeLegal(list);
//        List<Polder> polders = Lists.newArrayList();
//        list.stream().forEach(ploder ->
//                polders.add(Polder.builder()
//                        .areaName(ploder.get(1).toString())
//                        .areaCode(ploder.get(2).toString())
//                        .polderName(ploder.get(3).toString())
//                        .dikeLength(ploder.get(4).toString())
//                        .dikeAltitude(ploder.get(5).toString())
//                        .dikeWidth(ploder.get(6).toString())
//                        .polderArea(ploder.get(7).toString())
//                        .villagePlatformAltitude(ploder.get(8).toString())
//                        .villagePlatformArea(ploder.get(9).toString())
//                        .pumpNum(Integer.parseInt(ploder.get(10).toString()))
//                        .pumpRate(ploder.get(11).toString())
//                        .drainageFlow(ploder.get(12).toString())
//                        .drainageRate(ploder.get(13).toString())
//                        .culvert(ploder.get(14).toString())
//                        .gate(ploder.get(15).toString())
//                        .build())
//        );
//        return polderService.saveBatch(polders);
//    }
//
//    @Override
//    public boolean importAutoStation(InputStream inputStream) {
//        List<List<Object>> list;
//        try {
//            list = ExcelHelper.importExcel(inputStream, 2, 0);
//        } catch (Exception e) {
//            throw new UnifiedException(ExceptionEnum.EXCEL_IMPORT_FAIL);
//        }
//        //判断行政区划代码
//        this.judgeCodeLegalAutoStation(list);
//        List<AutoStation> autoStations = Lists.newArrayList();
//        list.stream().forEach(auto ->
//                autoStations.add(AutoStation.builder()
//                        .siteCode(auto.get(1).toString())
//                        .siteName(auto.get(2).toString())
//                        .riverName(auto.get(3).toString())
//                        .drainage(auto.get(4).toString())
//                        .basin(auto.get(5).toString())
//                        .longitude(auto.get(6).toString())
//                        .latitude(auto.get(7).toString())
//                        .address(auto.get(8).toString())
//                        .areaCode(auto.get(9).toString())
//                        .datumName(auto.get(10).toString())
//                        .datumAltitude(auto.get(11).toString())
//                        .datumCorrectionValue(Double.valueOf(auto.get(12).toString()))
//                        .type(auto.get(13).toString())
//                        .newReportDegree(auto.get(14).toString())
//                        .buildTime(auto.get(15).toString())
//                        .initialReportTime(auto.get(16).toString())
//                        .industryCompany(auto.get(17).toString())
//                        .informationManageCompany(auto.get(18).toString())
//                        .changeManageCompany(auto.get(19).toString())
//                        .shoreSeparation(auto.get(20).toString())
//                        .position(auto.get(21).toString())
//                        .riverDistance(auto.get(22).toString())
//                        .catchmentArea(auto.get(23).toString())
//                        .pinyinCode(auto.get(24).toString())
//                        .enableLogo(auto.get(25).toString())
//                        .content(auto.get(26).toString())
//                        .timeStamp(auto.get(27).toString())
//                        .build())
//        );
//        return autoStationService.saveBatch(autoStations);
//    }
//
//    @Override
//    public boolean importhistoryFloodRecord(InputStream inputStream) {
//        List<List<Object>> list;
//        try {
//            list = ExcelHelper.importExcel(inputStream, 2, 0);
//        } catch (Exception e) {
//            throw new UnifiedException(ExceptionEnum.EXCEL_IMPORT_FAIL);
//        }
//        //判断行政区划代码
//        this.judgeCodeLegal(list);
//        List<HistoryFloodRecord> historyFloodRecords = Lists.newArrayList();
//        list.stream().forEach( history->
//                historyFloodRecords.add(HistoryFloodRecord.builder()
//                        .areaCode(history.get(2).toString())
//                        .disasterPlace(history.get(3).toString())
//                        .disasterTime(history.get(4).toString())
//                        .rainfall(Double.valueOf(history.get(5).toString()))
//                        .siteName(history.get(6).toString())
//                        .longitude(history.get(7).toString())
//                        .latitude(history.get(8).toString())
//                        .manageCompany(history.get(9).toString())
//                        .maxFlow(history.get(10).toString())
//                        .maxDrownDepth(history.get(11).toString())
//                        .deathToll(Integer.valueOf(history.get(12).toString()))
//                        .missingPersons(Integer.valueOf(history.get(13).toString()))
//                        .floodHitPopulation(Integer.valueOf(history.get(14).toString()))
//                        .destoryedHouse(Integer.valueOf(history.get(15).toString()))
//                        .pecuniaryLoss(Double.valueOf(history.get(16).toString()))
//                        .build())
//        );
//        return historyFloodRecordService.saveBatch(historyFloodRecords);
//    }
//
//    @Override
//    public boolean importEnterprise(InputStream inputStream) {
//        List<List<Object>> list;
//        try {
//            list = ExcelHelper.importExcel(inputStream, 2, 0);
//        } catch (Exception e) {
//            throw new UnifiedException(ExceptionEnum.EXCEL_IMPORT_FAIL);
//        }
//        //判断行政区划代码
////        this.judgeCodeLegal(list);
//        List<Enterprise> enterprises = Lists.newArrayList();
//        list.stream().forEach(enterprise->
//                enterprises.add(Enterprise.builder()
//                        .name(enterprise.get(1).toString())
//                        .type(enterprise.get(2).toString())
//                        .areaName(enterprise.get(3).toString())
//                        .address(enterprise.get(4).toString())
//                        .nature(enterprise.get(5).toString())
//                        .dutyNumber(Integer.valueOf(enterprise.get(6).toString()))
//                        .averageOutputValue(Double.valueOf(enterprise.get(7).toString()))
//                        .hazardousChemicals(enterprise.get(8).toString())
//                        .waterlogged(enterprise.get(9).equals("是"))
//                        .floodThreat(enterprise.get(10).equals("是"))
//                        .description(enterprise.get(11).toString())
//                        .build())
//        );
//        return enterpriseService.saveBatch(enterprises);
//    }
//
//    @Override
//    public boolean importCountry(InputStream inputStream) {
//        List<List<Object>> list;
//        try {
//            list = ExcelHelper.importExcel(inputStream, 2, 0);
//        } catch (Exception e) {
//            throw new UnifiedException(ExceptionEnum.EXCEL_IMPORT_FAIL);
//        }
//        List<CountryEconomic> countryEconomics = Lists.newArrayList();
//        boolean remove = countryEconomicService.remove(null);
//        list.stream().forEach(enterprise->
//                countryEconomics.add(CountryEconomic.builder()
//                        .indexOne(enterprise.get(1).toString())
//                        .unit(enterprise.get(2).toString())
//                        .number(Double.valueOf(enterprise.get(3).toString()))
//                        .build())
//        );
//        return countryEconomicService.saveBatch(countryEconomics);
//    }
//
//    /**
//     * @param list 读取的excel集合
//     * @return
//     * @author hahaha
//     * @Description 判断行政区划代码是否重复或者存在
//     * @since 2018/12/25 14:28
//     **/
//    private void judgeCodeLegal(List<List<Object>> list) {
//        List<String> codeDB = basicAdministrativeDivisionService.list().parallelStream().map(BasicAdministrativeDivision::getDivisionCode).collect(Collectors.toList());
//        list.parallelStream().forEach(dike -> {
//            String areaCode = String.valueOf(dike.get(2));
//            if (StrUtil.isBlank(areaCode) || !codeDB.contains(areaCode)) {
//                throw new UnifiedException("序号" + dike.get(0) + "的行政区划代码为空或者不存在请核实后再导入");
//            }
//        });
//    }
//
//    /**
//     * 自动监测站
//     * @param list
//     */
//    private void judgeCodeLegalAutoStation(List<List<Object>> list) {
//        List<String> codeDB = basicAdministrativeDivisionService.list().parallelStream().map(BasicAdministrativeDivision::getDivisionCode).collect(Collectors.toList());
//        list.parallelStream().forEach(dike -> {
//            String areaCode = String.valueOf(dike.get(9));
//            if (StrUtil.isBlank(areaCode) || !codeDB.contains(areaCode)) {
//                throw new UnifiedException("序号" + dike.get(0) + "的行政区划代码为空或者不存在请核实后再导入");
//            }
//        });
//    }
//
}
