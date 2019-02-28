package com.zh.service.test2.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.api.Result;
import com.zh.dto.user.ChangePasswordDTO;
import com.zh.dto.user.SysUserInertOrUpdateDTO;
import com.zh.dto.user.SysUserQueryDTO;
import com.zh.dto.user.SysUserShowDTO;
import com.zh.entity.test2.*;
import com.zh.enums.ExceptionEnum;
import com.zh.mapper.test2.ZUserMapper;
import com.zh.security.userdetails.UserDetailsServiceExpansion;
import com.zh.service.test2.ZResourcesService;
import com.zh.service.test2.ZRolesResourcesService;
import com.zh.service.test2.ZUserRolesService;
import com.zh.service.test2.ZUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zh.util.file.excel.ExcelHelper;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author  hahaha 
 * @since 2019-02-21
 */
@Service
public class ZUserServiceImpl extends ServiceImpl<ZUserMapper, ZUser> implements ZUserService,UserDetailsServiceExpansion {

    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private ZUserMapper zUserMapper;
    @Resource
    private ZUserRolesService zUserRolesService;
    @Resource
    private ZRolesResourcesService zRolesResourcesService;
    @Resource
    private ZResourcesService zResourcesService;
    @Resource
    private ZUserService zUserService;
    //默认用户密码
    private String defaultPassword = "123456";

    @Value("${file.upload-path}")
    String url;

    @Override
    public List<ZUser> checkUserName(String name) {
        return null;
    }

    @Override
    public boolean checkUserExist(SysUserInertOrUpdateDTO userInertOrUpdateDTO) {
        return false;
    }

    @Override
    public Result<?> saveOrUpdateUser(SysUserInertOrUpdateDTO userInertOrUpdateDTO, MultipartFile multipartFile, HttpServletRequest request) throws IOException {
        String userName=userInertOrUpdateDTO.getName();
        String mobile=userInertOrUpdateDTO.getMobile();
        String profession=userInertOrUpdateDTO.getProfession();
        String roles=userInertOrUpdateDTO.getRoles();
        String type = "";
        //文件保存路径
        String savePath = "";
        //文件原名称
        String fileName = "";
        String trueFileName = "";
        //上传用户头像
        if (null != multipartFile) {
            fileName = multipartFile.getOriginalFilename();
            type = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()) : null;
            //判断文件类型是否为空
            if (type != null) {
                if ("GIF".equals(type.toUpperCase()) || "PNG".equals(type.toUpperCase()) || "JPG".equals(type.toUpperCase())) {
                   //自定义的文件名称
                    trueFileName = String.valueOf(System.currentTimeMillis()) + "." + type;
                    //设置图片存放的路径
                    savePath = trueFileName;
                    String fileTypePath = "//4";
                    String filePath = "4" + "//" + savePath;
                    String dirPath = url + fileTypePath;
                    if (!new File(dirPath).exists()) {
                        new File(dirPath).mkdirs();
                    }
                    File file = new File(url + "//" + filePath);
                    FileOutputStream outputStream = new FileOutputStream(file);
                    byte[] datas = new byte[1024];
                    int len = 0;
                    InputStream inputStream = multipartFile.getInputStream();
                    while ((len = inputStream.read(datas)) > 0) {
                        // 读进多少字节，就写出多少字节
                        outputStream.write(datas, 0, len);
                    }
                    outputStream.flush();
                    outputStream.close();
                    inputStream.close();
                } else {
                    return Result.fail("图片类型不符合");
                }
            }
        }
        //添加用户表
        ZUser zUser=new ZUser();
        zUser.setName(userName);
        zUser.setPassword(passwordEncoder.encode(defaultPassword));
        zUser.setMobile(mobile);
        zUser.setPhoto(savePath);
        zUser.setProfession(profession);
        this.saveOrUpdate(zUser);
        //用户与角色关联
        List<ZUserRoles> zUserRolesList=new ArrayList<>();
        String s[]=roles.split(",");
        for (String s1:s) {
            ZUserRoles zUserRoles = new ZUserRoles();
            zUserRoles.setSysRolesId(Long.valueOf(s1));
            zUserRoles.setSysUserId(zUser.getId());
            zUserRolesList.add(zUserRoles);
        }
        zUserRolesService.saveOrUpdateBatch(zUserRolesList);
        return Result.success("添加成功");
    }

    @Override
    public String exportOne(OutputStream outputStream) {
        List<ZUser> zUsers=zUserService.list();
        List<SysUserInertOrUpdateDTO> sysUserInertOrUpdateDTOS=new ArrayList<>();
        for (ZUser zUser:zUsers){
            SysUserInertOrUpdateDTO sysUserInertOrUpdateDTO=new SysUserInertOrUpdateDTO();
            BeanUtils.copyProperties(zUser,sysUserInertOrUpdateDTO);
            sysUserInertOrUpdateDTOS.add(sysUserInertOrUpdateDTO);
        }
        HSSFWorkbook workbook = new HSSFWorkbook();
        for (SysUserInertOrUpdateDTO sysUserInertOrUpdateDTO:sysUserInertOrUpdateDTOS){
            HSSFSheet sheet = workbook.createSheet("用户信息");
            sheet.autoSizeColumn(1, true);
            HSSFCellStyle normalStyle = createNormalStyle(workbook);

            //excel表头 第一行
//            createCell(sheet, statisticHourlyReportDTO.getSiteName() + statisticHourlyReportDTO.getDeviceName() + "运行日报",
//                    0, 0, createHeaderStyle(workbook));
//
//            createCell(sheet, ("日期：" + year + "年" + month + "月" +
//                    day + "日"), 1, 0, createTitleStyle(workbook));
//
//            createCell(sheet, ("星期：" + weekDays), 1, 9, createTitleStyle(workbook));
//            createCell(sheet, ("天气:" + statisticHourlyReportDTO.getWeather()), 1, 13, createTitleStyle(workbook));
//
//            createCell(sheet, "集水井提升泵", 2, 0, normalStyle);
//            createCell(sheet, "调节提升泵", 2, 4, normalStyle);
//            createCell(sheet, "回转风机", 2, 8, normalStyle);
//            createCell(sheet, "产水泵", 2, 12, normalStyle);
//            createCell(sheet, "污泥提升泵", 2, 16, normalStyle);
//            createCell(sheet, "清水泵", 2, 18, normalStyle);
//            createCell(sheet, "紫外线消毒", 2, 20, normalStyle);
//            createCell(sheet, "加药泵", 2, 22, normalStyle);
//            createCell(sheet, "潜水搅拌器", 2, 24, normalStyle);
//            createCell(sheet, "膜曝气放空阀", 2, 26, normalStyle);
//            createCell(sheet, "药箱曝气阀", 2, 28, normalStyle);
//            createCell(sheet, "气提排泥阀", 2, 30, normalStyle);
//
//            createCell(sheet, "A", 3, 0, normalStyle);
//            createCell(sheet, "B", 3, 2, normalStyle);
//            createCell(sheet, "A", 3, 4, normalStyle);
//            createCell(sheet, "B", 3, 6, normalStyle);
//            createCell(sheet, "A", 3, 8, normalStyle);
//            createCell(sheet, "B", 3, 10, normalStyle);
//            createCell(sheet, "A", 3, 12, normalStyle);
//            createCell(sheet, "B", 3, 14, normalStyle);
//            createCell(sheet, "A", 3, 16, normalStyle);
//
//            addRegion(sheet, 0, 0, 0, 25);//一体化污水处理站设备运行时间统计报表
//            addRegion(sheet, 1, 0, 1, 6);//日期：2018年08月14日 第一行 第0列到第5列
//            addRegion(sheet, 1, 9, 1, 10);
//            addRegion(sheet, 1, 13, 1, 14);
//
//            //集水井提升泵---潜水搅拌器
//            addRegion(sheet, 2, 0, 2, 3);//从第2行到第5行，从第0列到第3列
//            addRegion(sheet, 2, 4, 2, 7);//从第2行到第5行，从第4列到第7列
//            addRegion(sheet, 2, 8, 2, 11);//从第2行到第5行，从第8列到第11列
//            addRegion(sheet, 2, 12, 2, 15);//从第2行到第12行，从第0列到第15列
//            addRegion(sheet, 2, 16, 2, 17);//从第2行到第5行，从第0列到第3列
//
//
//            addRegion(sheet, 2, 18, 3, 19);//从第2行到第3行，从第18列到第19列  //周/运行状况
//            addRegion(sheet, 2, 20, 3, 21);//从 第二行到第3行 第20列到第21列
//            addRegion(sheet, 2, 22, 3, 23);//从第2行开始到第3行结束，从第22列开始到第23列结束
//            addRegion(sheet, 2, 24, 3, 25);//从第2行开始到第3行结束，从第24列开始到第25列结束
//            addRegion(sheet, 2, 26, 3, 27);//从第2行开始到第3行结束，从第26列到第27列
//
//            addRegion(sheet, 2, 28, 3, 29);//从第2行开始到第3行结束，从第28列开始到第29列结束
//            addRegion(sheet, 2, 30, 3, 31);//从第2行开始到第3行结束，从第30列到第31列
//
//            addRegion(sheet, 3, 0, 3, 1);
//            addRegion(sheet, 3, 2, 3, 3);
//            addRegion(sheet, 3, 4, 3, 5);
//            addRegion(sheet, 3, 6, 3, 7);
//            addRegion(sheet, 3, 8, 3, 9);
//            addRegion(sheet, 3, 10, 3, 11);
//            addRegion(sheet, 3, 12, 3, 13);
//            addRegion(sheet, 3, 14, 3, 15);
//            addRegion(sheet, 3, 16, 3, 17);
//
//            for (int i = 0; i <= 15; i++) {
//                createCell(sheet, "开起时间(min)", 4, 2 * i, normalStyle);
//                createCell(sheet, "停止时间(min)", 4, 2 * i + 1, normalStyle);
//            }
//
//            for (int i = 0; i <= 31; i++) {
//                addRegion(sheet, 4, i, 4, i);
//            }
//
//
//            for (int s = 0; s < hourlyReportDayStartTimeAndEndTimeDTOS.size(); s++) {
//                HourlyReportDayStartTimeAndEndTimeDTO hourlyReportDayStartTimeAndEndTimeDTO = hourlyReportDayStartTimeAndEndTimeDTOS.get(s);
//                if (hourlyReportDayStartTimeAndEndTimeDTO.getValue1() != null) {
//                    Date date1 = new Date(hourlyReportDayStartTimeAndEndTimeDTO.getValue1());
//                    String time1 = simpleDateFormat.format(date1);
//                    String hour = time1.substring(11, 13);
//                    String minute = time1.substring(14, 16);
//                    createCell(sheet, hour + ":" + minute, s + 5, 0, normalStyle);
//                } else {
//                    createCell(sheet, "", s + 5, 0, normalStyle);
//                }
//                if (hourlyReportDayStartTimeAndEndTimeDTO.getValue2() != null) {
//                    Date date1 = new Date(hourlyReportDayStartTimeAndEndTimeDTO.getValue2());
//                    String time1 = simpleDateFormat.format(date1);
//                    String hour = time1.substring(11, 13);
//                    String minute = time1.substring(14, 16);
//                    createCell(sheet, hour + ":" + minute, s + 5, 1, normalStyle);
//                } else {
//                    createCell(sheet, "", s + 5, 1, normalStyle);
//                }
//                if (hourlyReportDayStartTimeAndEndTimeDTO.getValue3() != null) {
//                    Date date1 = new Date(hourlyReportDayStartTimeAndEndTimeDTO.getValue3());
//                    String time1 = simpleDateFormat.format(date1);
//                    String hour = time1.substring(11, 13);
//                    String minute = time1.substring(14, 16);
//                    createCell(sheet, hour + ":" + minute, s + 5, 2, normalStyle);
//                } else {
//                    createCell(sheet, "", s + 5, 2, normalStyle);
//                }
//                if (hourlyReportDayStartTimeAndEndTimeDTO.getValue4() != null) {
//                    Date date1 = new Date(hourlyReportDayStartTimeAndEndTimeDTO.getValue4());
//                    String time1 = simpleDateFormat.format(date1);
//                    String hour = time1.substring(11, 13);
//                    String minute = time1.substring(14, 16);
//                    createCell(sheet, hour + ":" + minute, s + 5, 3, normalStyle);
//                } else {
//                    createCell(sheet, "", s + 5, 3, normalStyle);
//                }
//                if (hourlyReportDayStartTimeAndEndTimeDTO.getValue5() != null) {
//                    Date date1 = new Date(hourlyReportDayStartTimeAndEndTimeDTO.getValue5());
//                    String time1 = simpleDateFormat.format(date1);
//                    String hour = time1.substring(11, 13);
//                    String minute = time1.substring(14, 16);
//                    createCell(sheet, hour + ":" + minute, s + 5, 4, normalStyle);
//                } else {
//                    createCell(sheet, "", s + 5, 4, normalStyle);
//                }
//                if (hourlyReportDayStartTimeAndEndTimeDTO.getValue6() != null) {
//                    Date date1 = new Date(hourlyReportDayStartTimeAndEndTimeDTO.getValue6());
//                    String time1 = simpleDateFormat.format(date1);
//                    String hour = time1.substring(11, 13);
//                    String minute = time1.substring(14, 16);
//                    createCell(sheet, hour + ":" + minute, s + 5, 5, normalStyle);
//                } else {
//                    createCell(sheet, "", s + 5, 5, normalStyle);
//                }
//                if (hourlyReportDayStartTimeAndEndTimeDTO.getValue7() != null) {
//                    Date date1 = new Date(hourlyReportDayStartTimeAndEndTimeDTO.getValue7());
//                    String time1 = simpleDateFormat.format(date1);
//                    String hour = time1.substring(11, 13);
//                    String minute = time1.substring(14, 16);
//                    createCell(sheet, hour + ":" + minute, s + 5, 6, normalStyle);
//                } else {
//                    createCell(sheet, "", s + 5, 6, normalStyle);
//                }
//                if (hourlyReportDayStartTimeAndEndTimeDTO.getValue8() != null) {
//                    Date date1 = new Date(hourlyReportDayStartTimeAndEndTimeDTO.getValue8());
//                    String time1 = simpleDateFormat.format(date1);
//                    String hour = time1.substring(11, 13);
//                    String minute = time1.substring(14, 16);
//                    createCell(sheet, hour + ":" + minute, s + 5, 7, normalStyle);
//                } else {
//                    createCell(sheet, "", s + 5, 7, normalStyle);
//                }
//                if (hourlyReportDayStartTimeAndEndTimeDTO.getValue9() != null) {
//                    Date date1 = new Date(hourlyReportDayStartTimeAndEndTimeDTO.getValue9());
//                    String time1 = simpleDateFormat.format(date1);
//                    String hour = time1.substring(11, 13);
//                    String minute = time1.substring(14, 16);
//                    createCell(sheet, hour + ":" + minute, s + 5, 8, normalStyle);
//                } else {
//                    createCell(sheet, "", s + 5, 8, normalStyle);
//                }
//                if (hourlyReportDayStartTimeAndEndTimeDTO.getValue10() != null) {
//                    Date date1 = new Date(hourlyReportDayStartTimeAndEndTimeDTO.getValue10());
//                    String time1 = simpleDateFormat.format(date1);
//                    String hour = time1.substring(11, 13);
//                    String minute = time1.substring(14, 16);
//                    createCell(sheet, hour + ":" + minute, s + 5, 9, normalStyle);
//                } else {
//                    createCell(sheet, "", s + 5, 9, normalStyle);
//                }
//                if (hourlyReportDayStartTimeAndEndTimeDTO.getValue11() != null) {
//                    Date date1 = new Date(hourlyReportDayStartTimeAndEndTimeDTO.getValue11());
//                    String time1 = simpleDateFormat.format(date1);
//                    String hour = time1.substring(11, 13);
//                    String minute = time1.substring(14, 16);
//                    createCell(sheet, hour + ":" + minute, s + 5, 10, normalStyle);
//                } else {
//                    createCell(sheet, "", s + 5, 10, normalStyle);
//                }
//                if (hourlyReportDayStartTimeAndEndTimeDTO.getValue12() != null) {
//                    Date date1 = new Date(hourlyReportDayStartTimeAndEndTimeDTO.getValue12());
//                    String time1 = simpleDateFormat.format(date1);
//                    String hour = time1.substring(11, 13);
//                    String minute = time1.substring(14, 16);
//                    createCell(sheet, hour + ":" + minute, s + 5, 11, normalStyle);
//                } else {
//                    createCell(sheet, "", s + 5, 11, normalStyle);
//                }
//                if (hourlyReportDayStartTimeAndEndTimeDTO.getValue13() != null) {
//                    Date date1 = new Date(hourlyReportDayStartTimeAndEndTimeDTO.getValue13());
//                    String time1 = simpleDateFormat.format(date1);
//                    String hour = time1.substring(11, 13);
//                    String minute = time1.substring(14, 16);
//                    createCell(sheet, hour + ":" + minute, s + 5, 12, normalStyle);
//                } else {
//                    createCell(sheet, "", s + 5, 12, normalStyle);
//                }
//                if (hourlyReportDayStartTimeAndEndTimeDTO.getValue14() != null) {
//                    Date date1 = new Date(hourlyReportDayStartTimeAndEndTimeDTO.getValue14());
//                    String time1 = simpleDateFormat.format(date1);
//                    String hour = time1.substring(11, 13);
//                    String minute = time1.substring(14, 16);
//                    createCell(sheet, hour + ":" + minute, s + 5, 13, normalStyle);
//                } else {
//                    createCell(sheet, "", s + 5, 13, normalStyle);
//                }
//                if (hourlyReportDayStartTimeAndEndTimeDTO.getValue15() != null) {
//                    Date date1 = new Date(hourlyReportDayStartTimeAndEndTimeDTO.getValue15());
//                    String time1 = simpleDateFormat.format(date1);
//                    String hour = time1.substring(11, 13);
//                    String minute = time1.substring(14, 16);
//                    createCell(sheet, hour + ":" + minute, s + 5, 14, normalStyle);
//                } else {
//                    createCell(sheet, "", s + 5, 14, normalStyle);
//                }
//                if (hourlyReportDayStartTimeAndEndTimeDTO.getValue16() != null) {
//                    Date date1 = new Date(hourlyReportDayStartTimeAndEndTimeDTO.getValue16());
//                    String time1 = simpleDateFormat.format(date1);
//                    String hour = time1.substring(11, 13);
//                    String minute = time1.substring(14, 16);
//                    createCell(sheet, hour + ":" + minute, s + 5, 15, normalStyle);
//                    addRegion(sheet, s + 5, 15, s + 5, 15);
//                } else {
//                    createCell(sheet, "", s + 5, 15, normalStyle);
//                }
//                if (hourlyReportDayStartTimeAndEndTimeDTO.getValue17() != null) {
//                    Date date1 = new Date(hourlyReportDayStartTimeAndEndTimeDTO.getValue17());
//                    String time1 = simpleDateFormat.format(date1);
//                    String hour = time1.substring(11, 13);
//                    String minute = time1.substring(14, 16);
//                    createCell(sheet, hour + ":" + minute, s + 5, 16, normalStyle);
//                    addRegion(sheet, s + 5, 16, s + 5, 16);
//                } else {
//                    createCell(sheet, "", s + 5, 16, normalStyle);
//                }
//                if (hourlyReportDayStartTimeAndEndTimeDTO.getValue18() != null) {
//                    Date date1 = new Date(hourlyReportDayStartTimeAndEndTimeDTO.getValue18());
//                    String time1 = simpleDateFormat.format(date1);
//                    String hour = time1.substring(11, 13);
//                    String minute = time1.substring(14, 16);
//                    createCell(sheet, hour + ":" + minute, s + 5, 17, normalStyle);
//                    addRegion(sheet, s + 5, 17, s + 5, 17);
//                } else {
//                    createCell(sheet, "", s + 5, 17, normalStyle);
//                }
//                if (hourlyReportDayStartTimeAndEndTimeDTO.getValue19() != null) {
//                    Date date1 = new Date(hourlyReportDayStartTimeAndEndTimeDTO.getValue19());
//                    String time1 = simpleDateFormat.format(date1);
//                    String hour = time1.substring(11, 13);
//                    String minute = time1.substring(14, 16);
//                    createCell(sheet, hour + ":" + minute, s + 5, 18, normalStyle);
//                    addRegion(sheet, s + 5, 18, s + 5, 18);
//                } else {
//                    createCell(sheet, "", s + 5, 18, normalStyle);
//                }
//                if (hourlyReportDayStartTimeAndEndTimeDTO.getValue20() != null) {
//                    Date date1 = new Date(hourlyReportDayStartTimeAndEndTimeDTO.getValue20());
//                    String time1 = simpleDateFormat.format(date1);
//                    String hour = time1.substring(11, 13);
//                    String minute = time1.substring(14, 16);
//                    createCell(sheet, hour + ":" + minute, s + 5, 19, normalStyle);
//
//                } else {
//                    createCell(sheet, "", s + 5, 19, normalStyle);
//                }
//                if (hourlyReportDayStartTimeAndEndTimeDTO.getValue21() != null) {
//                    Date date1 = new Date(hourlyReportDayStartTimeAndEndTimeDTO.getValue21());
//                    String time1 = simpleDateFormat.format(date1);
//                    String hour = time1.substring(11, 13);
//                    String minute = time1.substring(14, 16);
//                    createCell(sheet, hour + ":" + minute, s + 5, 20, normalStyle);
//
//                } else {
//                    createCell(sheet, "", s + 5, 20, normalStyle);
//                }
//                if (hourlyReportDayStartTimeAndEndTimeDTO.getValue22() != null) {
//                    Date date1 = new Date(hourlyReportDayStartTimeAndEndTimeDTO.getValue22());
//                    String time1 = simpleDateFormat.format(date1);
//                    String hour = time1.substring(11, 13);
//                    String minute = time1.substring(14, 16);
//                    createCell(sheet, hour + ":" + minute, s + 5, 21, normalStyle);
//                } else {
//                    createCell(sheet, "", s + 5, 21, normalStyle);
//                }
//                if (hourlyReportDayStartTimeAndEndTimeDTO.getValue23() != null) {
//                    Date date1 = new Date(hourlyReportDayStartTimeAndEndTimeDTO.getValue23());
//                    String time1 = simpleDateFormat.format(date1);
//                    String hour = time1.substring(11, 13);
//                    String minute = time1.substring(14, 16);
//                    createCell(sheet, hour + ":" + minute, s + 5, 22, normalStyle);
//
//                } else {
//                    createCell(sheet, "", s + 5, 22, normalStyle);
//                }
//                if (hourlyReportDayStartTimeAndEndTimeDTO.getValue24() != null) {
//                    Date date1 = new Date(hourlyReportDayStartTimeAndEndTimeDTO.getValue24());
//                    String time1 = simpleDateFormat.format(date1);
//                    String hour = time1.substring(11, 13);
//                    String minute = time1.substring(14, 16);
//                    createCell(sheet, hour + ":" + minute, s + 5, 23, normalStyle);
//
//                } else {
//                    createCell(sheet, "", s + 5, 23, normalStyle);
//                }
//                if (hourlyReportDayStartTimeAndEndTimeDTO.getValue25() != null) {
//                    Date date1 = new Date(hourlyReportDayStartTimeAndEndTimeDTO.getValue25());
//                    String time1 = simpleDateFormat.format(date1);
//                    String hour = time1.substring(11, 13);
//                    String minute = time1.substring(14, 16);
//                    createCell(sheet, hour + ":" + minute, s + 5, 24, normalStyle);
//
//                } else {
//                    createCell(sheet, "", s + 5, 24, normalStyle);
//                }
//                if (hourlyReportDayStartTimeAndEndTimeDTO.getValue26() != null) {
//                    Date date1 = new Date(hourlyReportDayStartTimeAndEndTimeDTO.getValue26());
//                    String time1 = simpleDateFormat.format(date1);
//                    String hour = time1.substring(11, 13);
//                    String minute = time1.substring(14, 16);
//                    createCell(sheet, hour + ":" + minute, s + 5, 25, normalStyle);
//
//                } else {
//                    createCell(sheet, "", s + 5, 25, normalStyle);
//                }
//                if (hourlyReportDayStartTimeAndEndTimeDTO.getValue27() != null) {
//                    Date date1 = new Date(hourlyReportDayStartTimeAndEndTimeDTO.getValue27());
//                    String time1 = simpleDateFormat.format(date1);
//                    String hour = time1.substring(11, 13);
//                    String minute = time1.substring(14, 16);
//                    createCell(sheet, hour + ":" + minute, s + 5, 26, normalStyle);
//
//                } else {
//                    createCell(sheet, "", s + 5, 26, normalStyle);
//                }
//                if (hourlyReportDayStartTimeAndEndTimeDTO.getValue28() != null) {
//                    Date date1 = new Date(hourlyReportDayStartTimeAndEndTimeDTO.getValue28());
//                    String time1 = simpleDateFormat.format(date1);
//                    String hour = time1.substring(11, 13);
//                    String minute = time1.substring(14, 16);
//                    createCell(sheet, hour + ":" + minute, s + 5, 27, normalStyle);
//                } else {
//                    createCell(sheet, "", s + 5, 27, normalStyle);
//                }
//
//                if (hourlyReportDayStartTimeAndEndTimeDTO.getValue29() != null) {
//                    Date date1 = new Date(hourlyReportDayStartTimeAndEndTimeDTO.getValue29());
//                    String time1 = simpleDateFormat.format(date1);
//                    String hour = time1.substring(11, 13);
//                    String minute = time1.substring(14, 16);
//                    createCell(sheet, hour + ":" + minute, s + 5, 28, normalStyle);
//
//                } else {
//                    createCell(sheet, "", s + 5, 28, normalStyle);
//                }
//                if (hourlyReportDayStartTimeAndEndTimeDTO.getValue30() != null) {
//                    Date date1 = new Date(hourlyReportDayStartTimeAndEndTimeDTO.getValue30());
//                    String time1 = simpleDateFormat.format(date1);
//                    String hour = time1.substring(11, 13);
//                    String minute = time1.substring(14, 16);
//                    createCell(sheet, hour + ":" + minute, s + 5, 29, normalStyle);
//
//                } else {
//                    createCell(sheet, "", s + 5, 29, normalStyle);
//                }
//                if (hourlyReportDayStartTimeAndEndTimeDTO.getValue31() != null) {
//                    Date date1 = new Date(hourlyReportDayStartTimeAndEndTimeDTO.getValue31());
//                    String time1 = simpleDateFormat.format(date1);
//                    String hour = time1.substring(11, 13);
//                    String minute = time1.substring(14, 16);
//                    createCell(sheet, hour + ":" + minute, s + 5, 30, normalStyle);
//
//                } else {
//                    createCell(sheet, "", s + 5, 30, normalStyle);
//                }
//                if (hourlyReportDayStartTimeAndEndTimeDTO.getValue32() != null) {
//                    Date date1 = new Date(hourlyReportDayStartTimeAndEndTimeDTO.getValue32());
//                    String time1 = simpleDateFormat.format(date1);
//                    String hour = time1.substring(11, 13);
//                    String minute = time1.substring(14, 16);
//                    createCell(sheet, hour + ":" + minute, s + 5, 31, normalStyle);
//                } else {
//                    createCell(sheet, "", s + 5, 31, normalStyle);
//                }

//                addRegion(sheet, s + 5, 0, s + 5, 0);
//                addRegion(sheet, s + 5, 1, s + 5, 1);
//                addRegion(sheet, s + 5, 2, s + 5, 2);
//
//                addRegion(sheet, s + 5, 3, s + 5, 3);
//                addRegion(sheet, s + 5, 4, s + 5, 4);
//                addRegion(sheet, s + 5, 5, s + 5, 5);
//                addRegion(sheet, s + 5, 6, s + 5, 6);
//                addRegion(sheet, s + 5, 7, s + 5, 7);
//                addRegion(sheet, s + 5, 8, s + 5, 8);
//                addRegion(sheet, s + 5, 9, s + 5, 9);
//                addRegion(sheet, s + 5, 10, s + 5, 10);
//
//                addRegion(sheet, s + 5, 11, s + 5, 11);
//                addRegion(sheet, s + 5, 12, s + 5, 12);
//                addRegion(sheet, s + 5, 13, s + 5, 13);
//                addRegion(sheet, s + 5, 14, s + 5, 14);
//                addRegion(sheet, s + 5, 15, s + 5, 15);
//                addRegion(sheet, s + 5, 16, s + 5, 16);
//                addRegion(sheet, s + 5, 17, s + 5, 17);
//                addRegion(sheet, s + 5, 18, s + 5, 18);
//
//                addRegion(sheet, s + 5, 19, s + 5, 19);
//                addRegion(sheet, s + 5, 20, s + 5, 20);
//                addRegion(sheet, s + 5, 21, s + 5, 21);
//                addRegion(sheet, s + 5, 22, s + 5, 22);
//                addRegion(sheet, s + 5, 23, s + 5, 23);
//                addRegion(sheet, s + 5, 24, s + 5, 24);
//                addRegion(sheet, s + 5, 25, s + 5, 25);
//                addRegion(sheet, s + 5, 26, s + 5, 26);
//                addRegion(sheet, s + 5, 27, s + 5, 27);
//                addRegion(sheet, s + 5, 28, s + 5, 28);
//                addRegion(sheet, s + 5, 29, s + 5, 29);
//                addRegion(sheet, s + 5, 30, s + 5, 30);
//                addRegion(sheet, s + 5, 31, s + 5, 31);
                for (int k = 2; k <= 25; k++) {
                    sheet.setColumnWidth(k, 150 * 20 + 120);
                }
                normalStyle.setWrapText(true);
            }


//        }
        return null;
    }
    private HSSFCellStyle createNormalStyle(HSSFWorkbook workbook) {
        HSSFCellStyle setBorder = workbook.createCellStyle();
        HSSFCellStyle result = workbook.createCellStyle();
        // 居中
//        result.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//        result.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // 字体
        HSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short) 10);//设置字体大小
        result.setFont(font);
        setBorder.setFont(font);
        setBorder.setWrapText(true);
        return result;
    }
//    public void addRegion(HSSFSheet sheet, int rowFrom, int colFrom, int rowTo, int colTo) {
//        sheet.addMergedRegion(new Region(rowFrom, (short) colFrom, rowTo, (short) colTo));//poi-3.9.jar
//    }
    @Override
    public IPage<SysUserShowDTO> queryPage(Page<SysUserShowDTO> page, SysUserQueryDTO queryDTO) {
        return null;
    }

    @Override
    public SysUserShowDTO queryUserInfo(Long id) {
        return null;
    }

    @Override
    public boolean changePassword(ChangePasswordDTO changePasswordDTO) {
        return false;
    }

    @Override
    public boolean resetPassword(Long id) {
        return false;
    }

    @Override
    public void exportUser(HttpServletResponse httpServletResponse) throws IOException {
        List<ZUser> zUsers=zUserService.list();
        List<SysUserInertOrUpdateDTO> sysUserInertOrUpdateDTOS=new ArrayList<>();
        for (ZUser zUser:zUsers){
            SysUserInertOrUpdateDTO sysUserInertOrUpdateDTO=new SysUserInertOrUpdateDTO();
            BeanUtils.copyProperties(zUser,sysUserInertOrUpdateDTO);
            sysUserInertOrUpdateDTOS.add(sysUserInertOrUpdateDTO);
        }
        ExcelHelper.exportExcel(httpServletResponse,"用户","用户",SysUserInertOrUpdateDTO.class,sysUserInertOrUpdateDTOS);
    }

    @Override
    public UserDetails loadUserByUsernameMobile(String mobile) throws UsernameNotFoundException {
        ZUser zUser=zUserMapper.findByMobile(mobile);
        if (zUser==null){
            throw new BadCredentialsException(ExceptionEnum.user_not_exist.getMessage());
        }
        return permissionFilter(zUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ZUser zUser= zUserMapper.findByUserName(username);
        if (zUser==null){
            throw new BadCredentialsException(ExceptionEnum.user_not_exist.getMessage());
        }
//        UserDetails userDetails=permissionFilter(zUser);
        return permissionFilter(zUser);
    }
    public UserDetails permissionFilter(ZUser zUser){
            List<ZRoles> zRoles=zUser.getRoles();
            for (ZRoles zRoles1:zRoles) {
                List<ZResources> zResources=new ArrayList<>();
               for (ZResources zResources1:zRoles1.getResources()){
                   if (zResources1.getHasPersission()) {
                       zResources.add(zResources1);
                   }
               }
               zRoles1.setResources(zResources);
            }
            return zUser;
    }
}
