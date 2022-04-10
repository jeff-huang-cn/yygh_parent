package com.hotax.yygh.hosp.service;

import org.springframework.data.domain.Page;
import yygh.model.hosp.Hospital;
import yygh.vo.hosp.HospitalQueryVo;
import yygh.vo.hosp.HospitalSetQueryVo;

import java.util.Map;

/**
 * @author: Jeff 2022-04-04 23:14
 * @description:
 **/
public interface HospitalService {
    void save(Map<String, Object> paramMap);

    Hospital getByHoscode(String hoscode);

    Page<Hospital> selectHospPage(Integer page, Integer limit, HospitalQueryVo hospitalQueryVo);
}
