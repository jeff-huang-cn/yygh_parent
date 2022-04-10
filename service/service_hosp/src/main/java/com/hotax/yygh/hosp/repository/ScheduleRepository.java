package com.hotax.yygh.hosp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import yygh.model.hosp.Schedule;

/**
 * @author: Jeff 2022-04-06 22:17
 * @description:
 **/
@Repository
public interface ScheduleRepository extends MongoRepository<Schedule, String> {
    Schedule getScheduleByHoscodeAndHosScheduleId(String hoscode, String hosScheduleId);
}
