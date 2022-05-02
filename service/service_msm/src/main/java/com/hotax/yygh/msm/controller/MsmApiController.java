package com.hotax.yygh.msm.controller;

import com.hotax.yygh.common.result.Result;
import com.hotax.yygh.msm.service.MsmService;
import com.hotax.yygh.msm.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author: Jeff 2022-04-26 23:15
 * @description:
 **/
@RestController
@RequestMapping("/api/msm")
public class MsmApiController {

    @Autowired
    private MsmService msmService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @GetMapping("send/{phone}")
    public Result sendCode(@PathVariable String phone){
        String code = redisTemplate.opsForValue().get(phone);
        if(!StringUtils.isEmpty(code)) {
            return Result.ok();
        }

        code = "123456";//RandomUtil.getSixBitRandom();
        boolean isSend = true;//msmService.send(phone, code);
        if(isSend == Boolean.TRUE) {
            redisTemplate.opsForValue().set(phone, code, 5, TimeUnit.MINUTES);
            return Result.ok();
        }
        return  Result.fail("发送短信失败");
    }
}
