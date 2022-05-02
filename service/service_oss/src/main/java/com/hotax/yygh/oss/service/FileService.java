package com.hotax.yygh.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author: Jeff 2022-05-02 18:11
 * @description:
 **/
public interface FileService {
    String upload(MultipartFile file);
}
