package com.hotax.yygh.hosp.service;

import org.springframework.data.domain.Page;
import yygh.model.hosp.Department;
import yygh.model.hosp.Schedule;
import yygh.vo.hosp.ScheduleQueryVo;

import java.util.List;
import java.util.Map;

/**
 * @author: Jeff 2022-04-06 22:32
 * @description:
 **/
public interface ScheduleService {
    void save(Map<String, Object> paramMap);

    Page<Schedule> findPageDepartment(Integer page, Integer limit, ScheduleQueryVo scheduleQueryVo);

    void remove(String hoscode, String hosScheduleId);

    Map<String, Object> getRuleSchedule(long page, long limit, String hoscode, String depcode);

    List<Schedule> getDetailSchedule(String hoscode, String depcode, String workDate);
}
