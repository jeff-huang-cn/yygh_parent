package com.hotax.yygh.hosp.controller.api;

import com.hotax.yygh.common.exception.YyghException;
import com.hotax.yygh.common.helper.HttpRequestHelper;
import com.hotax.yygh.common.result.Result;
import com.hotax.yygh.common.result.ResultCodeEnum;
import com.hotax.yygh.common.utils.MD5;
import com.hotax.yygh.hosp.service.DepartmentService;
import com.hotax.yygh.hosp.service.HospitalService;
import com.hotax.yygh.hosp.service.HospitalSetService;
import com.hotax.yygh.hosp.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yygh.model.hosp.Department;
import yygh.model.hosp.Hospital;
import yygh.model.hosp.Schedule;
import yygh.vo.hosp.DepartmentQueryVo;
import yygh.vo.hosp.ScheduleQueryVo;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author: Jeff 2022-04-04 23:15
 * @description:
 **/
@RestController
@RequestMapping("/api/hosp")
public class ApiController {
    @Autowired
    private HospitalService hospitalService;

    @Autowired
    private HospitalSetService hospitalSetService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private ScheduleService scheduleService;

    @PostMapping("saveHospital")
    public Result saveHosp(HttpServletRequest request) {
        Map<String, String[]> requestMap = request.getParameterMap();
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(requestMap);

        String hospSign = (String) paramMap.get("sign");
        String hoscode = (String) paramMap.get("hoscode");

        checkSign(hospSign, hoscode);

        //传输过程中“+”转换为了“ ”，因此我们要转换回来
        String logoData = (String) paramMap.get("logoData");
        logoData = logoData.replaceAll(" ", "+");
        paramMap.put("logoData", logoData);

        hospitalService.save(paramMap);
        return Result.ok();
    }

    @PostMapping("hospital/show")
    public Result getHospital(HttpServletRequest request) {
        Map<String, String[]> requestMap = request.getParameterMap();
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(requestMap);

        String hoscode = (String) paramMap.get("hoscode");
        String hospSign = (String) paramMap.get("sign");
        checkSign(hospSign, hoscode);

        Hospital hospital = hospitalService.getByHoscode(hoscode);
        return Result.ok(hospital);
    }

    @PostMapping("saveDepartment")
    public Result saveDepartment(HttpServletRequest request) {
        Map<String, String[]> requestMap = request.getParameterMap();
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(requestMap);

        String hospSign = (String) paramMap.get("sign");
        String hoscode = (String) paramMap.get("hoscode");
        checkSign(hospSign, hoscode);

        departmentService.save(paramMap);
        return Result.ok();
    }

    @PostMapping("department/list")
    public Result findDepartment(HttpServletRequest request) {
        Map<String, String[]> requestMap = request.getParameterMap();
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(requestMap);
        String hoscode = (String) paramMap.get("hoscode");
        Integer page = StringUtils.isEmpty(paramMap.get("page")) ? 1 : Integer.parseInt(paramMap.get("page").toString());
        Integer limit = StringUtils.isEmpty(paramMap.get("limit")) ? 1 : Integer.parseInt(paramMap.get("limit").toString());

        String hospSign = (String) paramMap.get("sign");
        checkSign(hospSign, hoscode);

        DepartmentQueryVo departmentQueryVo = new DepartmentQueryVo();
        departmentQueryVo.setHoscode(hoscode);

        Page<Department> pageModel = departmentService.findPageDepartment(page, limit, departmentQueryVo);
        return Result.ok(pageModel);
    }

    @PostMapping("department/remove")
    public Result remove(HttpServletRequest request){
        Map<String, String[]> requestMap = request.getParameterMap();
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(requestMap);

        String hoscode = (String) paramMap.get("hoscode");
        String depcode = (String) paramMap.get("depcode");
        String hospSign = (String) paramMap.get("sign");
        checkSign(hospSign, hoscode);
        departmentService.remove(hoscode, depcode);
        return Result.ok();
    }

    @PostMapping("saveSchedule")
    public Result saveSchedule(HttpServletRequest request) {
        Map<String, String[]> requestMap = request.getParameterMap();
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(requestMap);

        String hospSign = (String) paramMap.get("sign");
        String hoscode = (String) paramMap.get("hoscode");
        checkSign(hospSign, hoscode);

        scheduleService.save(paramMap);
        return Result.ok();
    }

    @PostMapping("schedule/list")
    public Result findSchedule(HttpServletRequest request) {
        Map<String, String[]> requestMap = request.getParameterMap();
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(requestMap);
        String hoscode = (String) paramMap.get("hoscode");
        Integer page = StringUtils.isEmpty(paramMap.get("page")) ? 1 : Integer.parseInt(paramMap.get("page").toString());
        Integer limit = StringUtils.isEmpty(paramMap.get("limit")) ? 1 : Integer.parseInt(paramMap.get("limit").toString());

        String hospSign = (String) paramMap.get("sign");
        checkSign(hospSign, hoscode);

        ScheduleQueryVo scheduleQueryVo = new ScheduleQueryVo();
        scheduleQueryVo.setHoscode(hoscode);

        Page<Schedule> pageModel = scheduleService.findPageDepartment(page, limit, scheduleQueryVo);
        return Result.ok(pageModel);
    }

    @PostMapping("schedule/remove")
    public Result removeSchedule(HttpServletRequest request){
        Map<String, String[]> requestMap = request.getParameterMap();
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(requestMap);

        String hoscode = (String) paramMap.get("hoscode");
        String hosScheduleId = (String) paramMap.get("hosScheduleId");
        String hospSign = (String) paramMap.get("sign");
        checkSign(hospSign, hoscode);
        scheduleService.remove(hoscode, hosScheduleId);
        return Result.ok();
    }

    private void checkSign(String hospSign, String hoscode) {
        String signKey = hospitalSetService.getSignKey(hoscode);
        String signKeyMd5 = MD5.encrypt(signKey);

        if(!hospSign.equals(signKeyMd5)) {
            //return Result.fail("签名错误");
            throw new YyghException(ResultCodeEnum.SIGN_ERROR);
        }
    }
}
