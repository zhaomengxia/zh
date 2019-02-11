package com.zh.service.file.impl;

import com.zh.service.file.FileService;
import com.zh.util.FileHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author  赵梦霞
 * @since 2018-08-28 11:14

 **/
@Service
public class FileServiceImpl implements FileService {

    @Override
    public String upload(MultipartFile file) {
        return FileHelper.upload(file);
    }

}
