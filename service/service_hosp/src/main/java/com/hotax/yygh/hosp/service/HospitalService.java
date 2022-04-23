package com.hotax.yygh.hosp.service;

import org.springframework.data.domain.Page;
import yygh.model.hosp.Hospital;
import yygh.vo.hosp.HospitalQueryVo;
import yygh.vo.hosp.HospitalSetQueryVo;

import java.util.List;
import java.util.Map;

/**
 * @author: Jeff 2022-04-04 23:14
 * @description:
 **/
public interface HospitalService {
    void save(Map<String, Object> paramMap);

    Hospital getByHoscode(String hoscode);

    Page<Hospital> selectHospPage(Integer page, Integer limit, HospitalQueryVo hospitalQueryVo);

    void updateStatus(String id, Integer status);

    Map<String, Object> show(String id);

    /**
     * 根据医院编号获取医院名称接口
     * @param hoscode
     * @return
     */
    String getHospName(String hoscode);

    List<Hospital> findByHosname(String hosname);

    /**
     * 医院预约挂号详情
     */
    Map<String, Object> item(String hoscode);
}
