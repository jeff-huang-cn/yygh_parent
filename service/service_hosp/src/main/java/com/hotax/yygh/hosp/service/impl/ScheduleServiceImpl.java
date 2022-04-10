package com.hotax.yygh.hosp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.hotax.yygh.hosp.repository.ScheduleRepository;
import com.hotax.yygh.hosp.service.ScheduleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import yygh.model.hosp.Schedule;
import yygh.vo.hosp.ScheduleQueryVo;

import java.util.Date;
import java.util.Map;

/**
 * @author: Jeff 2022-04-06 22:33
 * @description:
 **/
@Service
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;

    @Override
    public void save(Map<String, Object> paramMap) {
        String mapString = JSONObject.toJSONString(paramMap);
        Schedule schedule = JSONObject.parseObject(mapString, Schedule.class);

        Schedule scheduleExist = scheduleRepository.getScheduleByHoscodeAndHosScheduleId(schedule.getHoscode(),schedule.getHosScheduleId());
        if (null == scheduleExist) {
            schedule.setCreateTime(new Date());
            schedule.setUpdateTime(new Date());
            schedule.setIsDeleted(0);
            schedule.setStatus(1);
            scheduleRepository.save(schedule);
        } else {
            scheduleExist.setUpdateTime(new Date());
            schedule.setIsDeleted(0);
            scheduleExist.setStatus(1);
            scheduleRepository.save(scheduleExist);
        }
    }

    @Override
    public Page<Schedule> findPageDepartment(Integer page, Integer limit, ScheduleQueryVo scheduleQueryVo) {
        Pageable pageable = PageRequest.of(page - 1, limit);
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase(true);

        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(scheduleQueryVo, schedule);
        schedule.setIsDeleted(0);

        Example<Schedule> example = Example.of(schedule, exampleMatcher);
        Page<Schedule> all = scheduleRepository.findAll(example, pageable);
        return all;
    }

    @Override
    public void remove(String hoscode, String hosScheduleId) {
        Schedule scheduleExist = scheduleRepository.getScheduleByHoscodeAndHosScheduleId(hoscode, hosScheduleId);
        if(null == scheduleExist) {
            return;
        }
        scheduleRepository.deleteById(scheduleExist.getId());
    }
}
