package com.zh.controller.file;

import cn.hutool.core.util.NetUtil;
import cn.hutool.core.util.StrUtil;
import com.zh.aop.annotation.Log;
import com.zh.api.Result;
import com.zh.service.file.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * 通用controller
 *
 * @author  赵梦霞
 * @since 2018-08-23 13:43

 **/
@RestController
@Api(description = "文件相关")
@RequestMapping("/file")
public class FileController {

    @Value("${server.port}")
    private String port;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Value("${onlinePreview.url}")
    private String previewUrl;


    @Resource
    private FileService fileService;

    /**
     * @param file 上传的文件
     * @return 文件访问路径
     * @author  赵梦霞
     * @Description 文件上传方法
     * @since 2018/8/23 13:45
     **/
    @PostMapping("/upload")
    @ApiOperation(value = "文件上传")
    @Log(desc = "文件上传")
    public Result upload(MultipartFile file) {
        return Result.success("文件上传成功", fileService.upload(file));
    }


    /**
     * @author  赵梦霞
     * @since 2018/12/3 16:26
     * @param filePath 需预览文件路径
     * @Description 获取在线文件预览地址
     **/
    @GetMapping("/onlinePreview")
    @ApiOperation(value = "获取在线文件预览地址")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "filePath",value = "需预览文件路径")
    })
    public Result onlinePreview(String filePath) {
        return Result.success(StrUtil.format(previewUrl, NetUtil.getLocalhostStr(), port, contextPath, filePath));
    }

}
