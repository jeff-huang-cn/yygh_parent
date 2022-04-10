package com.hotax.yygh.hosp.controller;

import com.hotax.yygh.common.result.Result;
import com.hotax.yygh.hosp.service.HospitalService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import yygh.model.hosp.Hospital;
import yygh.vo.hosp.HospitalQueryVo;
import yygh.vo.hosp.HospitalSetQueryVo;

/**
 * @author: Jeff 2022-04-10 13:11
 * @description:
 **/
@RestController
@RequestMapping("/admin/hosp/hospital")
@CrossOrigin
public class HospitalController {
    @Autowired
    private HospitalService hospitalService;

    @GetMapping("list/{page}/{limit}")
    public Result listHosp(@PathVariable Integer page, @PathVariable Integer limit, HospitalQueryVo hospitalQueryVo) {
        Page<Hospital> pageModel = hospitalService.selectHospPage(page, limit, hospitalQueryVo);
        return Result.ok(pageModel);
    }

}
