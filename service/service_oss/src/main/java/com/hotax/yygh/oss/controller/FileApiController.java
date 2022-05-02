package com.hotax.yygh.oss.controller;

import com.hotax.yygh.common.result.Result;
import com.hotax.yygh.oss.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author: Jeff 2022-05-02 18:08
 * @description:
 **/
@RestController
@RequestMapping("/api/oss/file")
public class FileApiController {

    @Autowired
    private FileService fileService;

    // 上传文件到阿里云oss
    @PostMapping("fileUpload")
    public Result fileUpload(MultipartFile file) {
        String filePath = fileService.upload(file);
        return Result.ok(filePath);
    }
}
