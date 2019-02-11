package com.zh.controller.basic;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.aop.annotation.Log;
import com.zh.api.Result;
import com.zh.entity.basic.HistoryFloodRecord;
import com.zh.enums.ExceptionEnum;
import com.zh.exceptions.UnifiedException;
import com.zh.service.basic.HistoryFloodRecordService;
import com.zh.service.excel.ImportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 赵梦霞
 * @since 2019-01-07
 */
@RestController
@RequestMapping("/historyFloodRecord")
@Api(description = "基础信息-历史洪涝灾害")
public class HistoryFloodRecordController {

    @Resource
    HistoryFloodRecordService historyFloodRecordService;

    @Resource
    private ImportService importService;
    @GetMapping("getListPage")
    @ApiOperation(value = "获取分页列表")
    public Result getListPage(Page<HistoryFloodRecord> page, String keywords){
        return historyFloodRecordService.getListPage(page, keywords);
    }

    @PostMapping(value = "addOrUpdate")
    @ApiOperation(value = "新增或修改")
    public Result addOrUpdate(@RequestBody @Valid HistoryFloodRecord historyFloodRecord){
        if(historyFloodRecord.getId() == null){
            historyFloodRecordService.save(historyFloodRecord);
            return Result.success("新增成功!");
        }else {
            historyFloodRecordService.updateById(historyFloodRecord);
            return Result.success("修改成功!");
        }
    }

    @PostMapping(value = "delete")
    @ApiOperation(value = "删除")
    public Result delete(@RequestBody List<Long> ids){
        historyFloodRecordService.removeByIds(ids);
        return Result.success("删除成功!");
    }

    @ApiOperation(value = "历史洪涝灾害信息导入")
    @PostMapping("/historyFlood")
    @Log(desc = "历史洪涝灾害信息导入")
    public Result history(MultipartFile file) throws IOException {
        if (importService.importhistoryFloodRecord(file.getInputStream())) {
            return Result.success(ExceptionEnum.EXCEL_IMPORT_SUCCESS);
        }
        throw new UnifiedException(ExceptionEnum.EXCEL_IMPORT_FAIL);
    }

    @ApiOperation(value = "历史洪涝灾害excel导出")
    @GetMapping("/excel")
    @Log(desc = "历史洪涝灾害excel导出")
    public void excel(HttpServletResponse response, String keywords) {
        try {
            historyFloodRecordService.exportExcel(response, keywords);
        } catch (IOException e) {
            throw new UnifiedException(ExceptionEnum.EXCEL_EXPORT_FAIL);
        }
    }
}

