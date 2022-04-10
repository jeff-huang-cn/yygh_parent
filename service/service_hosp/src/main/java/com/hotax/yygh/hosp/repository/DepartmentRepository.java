package com.hotax.yygh.hosp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import yygh.model.hosp.Department;

/**
 * @author: Jeff 2022-04-05 23:14
 * @description:
 **/
@Repository
public interface DepartmentRepository extends MongoRepository<Department, String> {
    Department getDepartmentByHoscodeAndDepcode(String hoscode, String depcode);
}
