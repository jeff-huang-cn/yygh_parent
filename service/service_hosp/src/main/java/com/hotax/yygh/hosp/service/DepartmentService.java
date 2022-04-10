package com.hotax.yygh.hosp.service;

import org.springframework.data.domain.Page;
import yygh.model.hosp.Department;
import yygh.vo.hosp.DepartmentQueryVo;

import java.util.Map;

/**
 * @author: Jeff 2022-04-05 23:14
 * @description:
 **/
public interface DepartmentService {
    void save(Map<String, Object> paramMap);

    Page<Department> findPageDepartment(Integer page, Integer limit, DepartmentQueryVo departmentQueryVo);

    void remove(String hoscode, String depcode);
}
