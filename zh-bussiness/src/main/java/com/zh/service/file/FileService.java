package com.zh.service.file;

import org.springframework.web.multipart.MultipartFile;

/**
 * 文件 service
 *
 * @author  赵梦霞
 * @date 2018-08-28 11:13

 **/
public interface FileService {

    /**
     * @author  赵梦霞
     * @since 2018/8/28 11:14
     * @param file 需上传的文件
     * @Description 文件上传
     * @return 文件路径
     **/
    String upload(MultipartFile file);



}
