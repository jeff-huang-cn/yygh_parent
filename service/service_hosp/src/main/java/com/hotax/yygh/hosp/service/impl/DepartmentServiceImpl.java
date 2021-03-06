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
import yygh.vo.hosp.DepartmentVo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Override
    public List<DepartmentVo> findDeptTree(String hoscode) {
        //??????list?????????????????????????????????
        List<DepartmentVo> result = new ArrayList<>();

        //???????????????????????????????????????????????????
        Department departmentQuery = new Department();
        departmentQuery.setHoscode(hoscode);
        Example example = Example.of(departmentQuery);
        //?????????????????? departmentList
        List<Department> departmentList = departmentRepository.findAll(example);

        //?????????????????????  bigcode ???????????????????????????????????????????????????
        Map<String, List<Department>> deparmentMap =
                departmentList.stream().collect(Collectors.groupingBy(Department::getBigcode));
        //??????map?????? deparmentMap
        for(Map.Entry<String,List<Department>> entry : deparmentMap.entrySet()) {
            //???????????????
            String bigcode = entry.getKey();
            //????????????????????????????????????
            List<Department> deparment1List = entry.getValue();
            //???????????????
            DepartmentVo departmentVo1 = new DepartmentVo();
            departmentVo1.setDepcode(bigcode);
            departmentVo1.setDepname(deparment1List.get(0).getBigname());

            //???????????????
            List<DepartmentVo> children = new ArrayList<>();
            for(Department department: deparment1List) {
                DepartmentVo departmentVo2 =  new DepartmentVo();
                departmentVo2.setDepcode(department.getDepcode());
                departmentVo2.setDepname(department.getDepname());
                //?????????list??????
                children.add(departmentVo2);
            }
            //????????????list?????????????????????children??????
            departmentVo1.setChildren(children);
            //????????????result??????
            result.add(departmentVo1);
        }
        //??????
        return result;
    }

    @Override
    public Object getDepName(String hoscode, String depcode) {
        Department department = departmentRepository.getDepartmentByHoscodeAndDepcode(hoscode, depcode);
        if(null == department) {
            return null;
        }
        return department.getDepname();
    }
}
