package com.hotax.yygh.hosp.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hotax.yygh.common.utils.MD5;
import com.hotax.yygh.common.result.Result;
import com.hotax.yygh.hosp.service.HospitalSetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import yygh.model.hosp.HospitalSet;
import yygh.vo.hosp.HospitalQueryVo;

import java.util.List;
import java.util.Random;

/**
 * @author: Jeff 2022-04-03 14:58
 * @description:
 **/
@Api(tags = "醫院設置管理")
@RestController
@RequestMapping("/admin/hosp/hospitalSet")
@CrossOrigin
public class HospitalSetController {
    @Autowired
    private HospitalSetService hospitalSetService;

    /**
     * 查詢醫院設置表所有信息
     * @return
     */
    @ApiOperation("查詢醫院設置表所有信息")
    @GetMapping("findAll")
    public Result finnAllHospitalSet(){
        // 調用service的方法
        List<HospitalSet> list = hospitalSetService.list();
        return Result.ok(list);
    }

    @ApiOperation("逻辑删除医院信息")
    @DeleteMapping("{id}")
    public Result removeHospSet(@PathVariable Long id){
        boolean flag = hospitalSetService.removeById(id);
        if(flag) {
            return Result.ok();
        }
        return Result.fail();
    }

    @ApiOperation("分页查询医院信息")
    @PostMapping("findPageHospSet/{current}/{limit}")
    public Result findPageHospSet(@PathVariable long current,
                                  @PathVariable long limit,
                                  @RequestBody(required = false) HospitalQueryVo hospitalQueryVo){
        Page<HospitalSet> page = new Page<>(current, limit);
        QueryWrapper<HospitalSet> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(hospitalQueryVo.getHosname())){
            wrapper.like("hosname", hospitalQueryVo.getHosname());
        }
        if(!StringUtils.isEmpty(hospitalQueryVo.getHoscode())){
            wrapper.like("hoscode", hospitalQueryVo.getHoscode());
        }
        Page<HospitalSet> list = hospitalSetService.page(page, wrapper);
        return Result.ok(list);
    }

    @ApiOperation("新增医院信息")
    @PostMapping("saveHospitalSet")
    public Result saveHospitalSet(@RequestBody HospitalSet hospitalSet) {
        hospitalSet.setStatus(1);
        //签名秘钥
        Random random = new Random();
        hospitalSet.setSignKey(MD5.encrypt(System.currentTimeMillis()+""+random.nextInt(1000)));
        //调用service
        boolean save = hospitalSetService.save(hospitalSet);
        if(save) {
            return Result.ok();
        }
        return Result.fail();
    }

    @PutMapping("updateHospitalSet")
    public Result updateHospitalSet(@RequestBody HospitalSet hospitalSet) {
        //签名秘钥
        Random random = new Random();
        hospitalSet.setSignKey(MD5.encrypt(System.currentTimeMillis()+""+random.nextInt(1000)));
        //调用service
        boolean save = hospitalSetService.updateById(hospitalSet);
        if(save) {
            return Result.ok();
        }
        return Result.fail();
    }

    @PutMapping("lockHospitalSet/{id}/{status}")
    public Result lockHospitalSet(@PathVariable Long id, @PathVariable Integer status) {
        HospitalSet hospitalSet = hospitalSetService.getById(id);
        hospitalSet.setStatus(status);
        hospitalSetService.updateById(hospitalSet);
        return Result.ok();
    }

    @GetMapping("getHospSet/{id}")
    public Result getHospSet(@PathVariable Long id) {
        HospitalSet hospitalSet = hospitalSetService.getById(id);
        return Result.ok(hospitalSet);
    }
}
