package com.hotax.yygh.hosp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hotax.yygh.hosp.mapper.HospitalSetMapper;
import com.hotax.yygh.hosp.service.HospitalSetService;
import org.springframework.stereotype.Service;
import yygh.model.hosp.HospitalSet;

/**
 * @author: Jeff 2022-04-03 14:55
 * @description:
 **/
@Service
public class HospitalSetServiceImpl extends ServiceImpl<HospitalSetMapper, HospitalSet> implements HospitalSetService {
    @Override
    public String getSignKey(String hoscode) {
        QueryWrapper<HospitalSet> wrapper = new QueryWrapper<>();
        wrapper.eq("hoscode", hoscode);
        HospitalSet hospitalSet = baseMapper.selectOne(wrapper);
        return hospitalSet.getSignKey();
    }
}
