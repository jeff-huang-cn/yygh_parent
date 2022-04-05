package com.hotax.yygh.cmn.controller;

import com.hotax.yygh.cmn.service.DictService;
import com.hotax.yygh.common.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import yygh.model.cmn.Dict;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author: Jeff 2022-04-04 11:37
 * @description:
 **/
@Api(tags = "字典管理")
@RestController
@RequestMapping("/admin/cmn/dict")
@CrossOrigin
public class DictController {
    @Autowired
    private DictService dictService;

    //根据数据id查询子数据列表
    @ApiOperation(value = "根据数据id查询子数据列表")
    @GetMapping("findChildData/{id}")
    public Result findChildData(@PathVariable Long id) {
        List<Dict> list = dictService.findChlidData(id);
        return Result.ok(list);
    }

    // 导出数据字典接口
    @ApiOperation(value="导出")
    @GetMapping(value = "/exportData")
    public Result exportDict(HttpServletResponse response){
        dictService.exportDict(response);
        return Result.ok();
    }

    // 导入数据字典
    @PostMapping("importData")
    public Result importDict(MultipartFile file) {
        dictService.importDictData(file);
        return Result.ok();
    }
}
