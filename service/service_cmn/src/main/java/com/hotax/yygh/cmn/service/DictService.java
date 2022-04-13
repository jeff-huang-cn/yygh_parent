package com.hotax.yygh.cmn.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import yygh.model.cmn.Dict;
import yygh.model.hosp.HospitalSet;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author: Jeff 2022-04-03 14:52
 * @description:
 **/
@Service
public interface DictService extends IService<Dict>  {
    //根据数据id查询子数据列表
    List<Dict> findChlidData(Long id);

    void exportDict(HttpServletResponse response);

    void importDictData(MultipartFile file);

    String getDictName(String dictCode, String value);

    List<Dict> findByDictCode(String dictCode);
}
