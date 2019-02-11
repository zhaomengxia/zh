package com.zh.util.file.excel;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * Copyright 2016 envCloud Inc.
 *
 * @author 赵梦霞
 * @description:
 */

public class ExcelHelper {

    public static <T> List<T> getInputList(InputStream inputStream, Class<T> tClass, Integer titleRows,
                                           Integer headRow) throws Exception {
        ImportParams params = new ImportParams();
        params.setTitleRows(titleRows);
        params.setHeadRows(headRow);
        return ExcelImportUtil.importExcel(inputStream, tClass, params);
    }

    /**
     * @param inputStream      excel输入流
     * @param titleRow         标题行数
     * @param headRow          头行数
     * @param lastOfInvalidRow 忽略最后几行
     * @author  赵梦霞
     * @Description 导入excel到List<Map> 集合中
     * @since 2018/12/25 13:50
     **/
    public static List<Map<String, String>> importExcel(InputStream inputStream, int titleRow, int headRow, int lastOfInvalidRow) throws Exception {
        ImportParams params = new ImportParams();
        params.setLastOfInvalidRow(lastOfInvalidRow);
        params.setTitleRows(titleRow);
        params.setHeadRows(headRow);
        return ExcelImportUtil.importExcel(inputStream, Map.class, params);
    }

    /**
     * @param inputStream      excel输入流
     * @param startRowIndex    开始行号
     * @param lastOfInvalidRow 忽略最后几行
     * @author  赵梦霞
     * @Description
     * @since 2018/12/25 17:28
     **/
    public static List<List<Object>> importExcel(InputStream inputStream, int startRowIndex, int lastOfInvalidRow) {
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        List<List<Object>> read = reader.read(startRowIndex);
        return read.subList(0, read.size() - lastOfInvalidRow);
    }

    /**
     * @param response  响应对象
     * @param title     excel 名称
     * @param sheetName sheet 名称
     * @param clazz     实体类Class对象
     * @param list      数据集合
     * @author  赵梦霞
     * @Description 根据实体类注解导出excel
     * @since 2018/12/18 17:15
     **/
    public static void exportExcel(HttpServletResponse response, String title, String sheetName, Class clazz, List list) throws IOException {

        downloadSetting(response, title);
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(title, sheetName), clazz, list);
        workbook.write(response.getOutputStream());

    }


    /**
     * @param exportEntities 构建excel对象
     * @param data           导出数据
     * @param title          excel名称
     * @param sheetName      sheet名称
     * @author  赵梦霞
     * @Description 根据Map导出excel
     * @since 2018/12/24 10:18
     **/
    public static void exportExcel(HttpServletResponse response, List<ExcelExportEntity> exportEntities, List<Map<String, Object>> data, String title, String sheetName) throws IOException {
        downloadSetting(response, title);
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(title, sheetName), exportEntities, data);
        workbook.write(response.getOutputStream());
    }

    /**
     * @param response 响应对象
     * @author  赵梦霞
     * @Description 设置excel下载响应对象
     * @since 2018/12/18 17:20
     **/
    private static void downloadSetting(HttpServletResponse response, String title) throws UnsupportedEncodingException {
        // 告诉浏览器用什么软件可以打开此文件
        response.setHeader("content-Type", "application/vnd.ms-excel");
        // 下载文件的默认名称
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(title.replaceAll(StrUtil.SPACE, StrUtil.EMPTY), "UTF-8") + ".xlsx");
        //编码
        response.setCharacterEncoding("UTF-8");

    }
}
