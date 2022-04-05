package com.hotax.yygh.hosp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.hotax.yygh.hosp.repository.HospitalRepository;
import com.hotax.yygh.hosp.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yygh.model.hosp.Hospital;

import java.util.Date;
import java.util.Map;

/**
 * @author: Jeff 2022-04-04 23:14
 * @description:
 **/
@Service
public class HospitalServiceImpl implements HospitalService {
    @Autowired
    private HospitalRepository hospitalRepository;

    @Override
    public void save(Map<String, Object> paramMap) {
        String mapString = JSONObject.toJSONString(paramMap);
        Hospital hospital = JSONObject.parseObject(mapString, Hospital.class);
        String hoscode = hospital.getHoscode();
        Hospital targetHospital = hospitalRepository.getHospitalByHoscode(hoscode);
        if(null != targetHospital) {
            hospital.setStatus(targetHospital.getStatus());
            hospital.setCreateTime(targetHospital.getCreateTime());
        } else {
            //0：未上线 1：已上线
            hospital.setStatus(0);
            hospital.setCreateTime(new Date());
        }
        hospital.setUpdateTime(new Date());
        hospital.setIsDeleted(0);
        hospitalRepository.save(hospital);
    }
}
