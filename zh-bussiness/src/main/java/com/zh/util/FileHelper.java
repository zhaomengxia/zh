package com.zh.util;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.github.rholder.retry.RetryException;
import com.github.rholder.retry.Retryer;
import com.zh.enums.CatalogEnum;
import com.zh.enums.ExceptionEnum;
import com.zh.exceptions.UnifiedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 文件上传帮助类
 *
 * @author  hahaha
 * @since 2018-08-22 10:34

 **/
@Slf4j
@Component
@SuppressWarnings("unused")
public class FileHelper {

    /**
     * 文件上传根目录
     */
    private static String ROOT_PATH;

    private static List<String> imageList;

    private static List<String> officeList;

    private static List<String> mediaList;

    private FileHelper() {
    }

    @Value("${file.upload-path}")
    private String temp;

    @PostConstruct
    public void init() {
        ROOT_PATH = temp;
        imageList = CollUtil.newCopyOnWriteArrayList(CollUtil.list(true, "jpg", "png", "gif", "tif", "bmp"));
        officeList = CollUtil.newCopyOnWriteArrayList(CollUtil.list(true, "doc", "docx", "xlsx", "xls", "pdf", "wps", "txt"));
        mediaList = CollUtil.newCopyOnWriteArrayList(CollUtil.list(true, "rmvb", "flv", "mp3", "mp4", "mpg", "wmv", "wav", "avi", "ogg"));
    }


    /**
     * @param file 需上传的文件
     * @return 文件路径
     * @author  hahaha
     * @Description 上传文件到服务器（单个）
     * @since 2018/8/22 14:10
     **/
    public static String upload(MultipartFile file) {
        return uploadFile(file);
    }

    /**
     * @param file 需上传的文件
     * @author  hahaha
     * @Description 创建文件目录 格式为 年/月/日
     * @since 2018/8/22 11:24
     **/
    private static String uploadFile(MultipartFile file) {
        Assert.notNull(file, ExceptionEnum.FILE_IS_BLANK.getMessage());
        //获取文件类型
        String type;
        //文件上传路径
        String relativePath;

        type = getFileSuffix(file.getOriginalFilename());
        String lowerCaseType = type.toLowerCase();
        if (imageList.contains(lowerCaseType)) {
            relativePath = CatalogEnum.IMAGE.getType() + File.separator + lowerCaseType + generateHierarchy();
        } else if (officeList.contains(lowerCaseType)) {
            relativePath = CatalogEnum.OFFICE.getType() + File.separator + lowerCaseType + generateHierarchy();
        } else if (mediaList.contains(lowerCaseType)) {
            relativePath = CatalogEnum.MEDIA.getType() + File.separator + lowerCaseType + generateHierarchy();
        } else {
            relativePath = CatalogEnum.FILE.getType() + File.separator + lowerCaseType + generateHierarchy();
        }

        //生成文件名称 保证文件名唯一
        String fileName = generatorFileName(type);

        //文件绝对路径
        File target = new File(ROOT_PATH, relativePath);
        //判断文件夹是否存在
        if (!target.exists()) {
            boolean mkdirs = target.mkdirs();
            if (!mkdirs) {
                //重试机制保证文件夹生成成功
                Retryer<Boolean> retryer = RetryerUtil.getRetryer(false, 10, TimeUnit.MILLISECONDS, 10);
                try {
                    retryer.call(target::mkdirs);
                } catch (ExecutionException | RetryException e) {
                    log.error(ExceptionEnum.FOLDER_GENERATOR_FAIL.getMessage());
                    throw new UnifiedException(ExceptionEnum.FILE_UPLOAD_FAIL);
                }
            }
        }

        try (BufferedInputStream inputStream = new BufferedInputStream(file.getInputStream());
             BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(new File(target, fileName)))) {
            IoUtil.copyByNIO(inputStream, outputStream, IoUtil.DEFAULT_LARGE_BUFFER_SIZE, null);
        } catch (IOException e) {
            log.error(ExceptionEnum.FILE_UPLOAD_FAIL.getMessage());
            throw new UnifiedException(ExceptionEnum.FILE_UPLOAD_FAIL);
        }

        log.info(ExceptionEnum.FILE_UPLOAD_SUCCESS.getMessage());
        return StrUtil.replace(File.separator + relativePath + fileName, "\\", "/");
    }


    /**
     * @param extension 文件扩展名
     * @author  hahaha
     * @Description 文件名生成
     * @since 2018/8/22 15:31
     **/
    private static String generatorFileName(String extension) {
        return File.separator + DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS")
                .format(LocalDateTime.now()) + StrUtil.sub(IdUtil.simpleUUID(), 0, 5) + StrUtil.DOT + extension;
    }

    /**
     * @param fileWholeName 文件全名称
     * @return 文件扩展名
     * @author  hahaha
     * @Description 获取文件后缀名 不包含点
     * @since 2018/8/23 14:17
     **/
    private static String getFileSuffix(String fileWholeName) {
        if (StrUtil.isEmpty(fileWholeName)) {
            return StrUtil.EMPTY;
        }
        int lastIndexOf = fileWholeName.lastIndexOf(StrUtil.DOT);
        return fileWholeName.substring(lastIndexOf + 1);
    }

    /**
     * @author  hahaha
     * @Description 生成目录层级
     * @since 2018/8/23 14:35
     **/
    private static String generateHierarchy() {
        LocalDate date = LocalDate.now();
        return File.separator + date.getYear() + File.separator + date.getMonthValue() + File.separator + date.getDayOfMonth();
    }

}
