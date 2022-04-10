package com.hotax.yygh.hosp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.hotax.yygh.hosp.repository.DepartmentRepository;
import com.hotax.yygh.hosp.service.DepartmentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import yygh.model.hosp.Department;
import yygh.vo.hosp.DepartmentQueryVo;

import java.util.Date;
import java.util.Map;

/**
 * @author: Jeff 2022-04-05 23:15
 * @description:
 **/
@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public void save(Map<String, Object> paramMap) {
        String mapString = JSONObject.toJSONString(paramMap);
        Department department = JSONObject.parseObject(mapString, Department.class);

        Department departmentExist = departmentRepository.getDepartmentByHoscodeAndDepcode(department.getHoscode(),department.getDepcode());
        if(null == departmentExist) {
            department.setCreateTime(new Date());
            department.setUpdateTime(new Date());
            department.setIsDeleted(0);
            departmentRepository.save(department);
        } else {
            departmentExist.setUpdateTime(new Date());
            departmentExist.setIsDeleted(0);
            departmentRepository.save(departmentExist);
        }
    }

    @Override
    public Page<Department> findPageDepartment(Integer page, Integer limit, DepartmentQueryVo departmentQueryVo) {
        Pageable pageable = PageRequest.of(page - 1, limit);
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase(true);

        Department department = new Department();
        BeanUtils.copyProperties(departmentQueryVo, department);
        department.setIsDeleted(0);

        Example<Department> example = Example.of(department, exampleMatcher);
        Page<Department> all = departmentRepository.findAll(example, pageable);
        return all;
    }

    @Override
    public void remove(String hoscode, String depcode) {
        Department departmentExist = departmentRepository.getDepartmentByHoscodeAndDepcode(hoscode, depcode);
        if(null == departmentExist) {
            return;
        }
        departmentRepository.deleteById(departmentExist.getId());
    }
}
