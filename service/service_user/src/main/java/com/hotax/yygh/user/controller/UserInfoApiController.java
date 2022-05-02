package com.hotax.yygh.user.controller;

import com.hotax.yygh.common.result.Result;
import com.hotax.yygh.common.utils.IpUtil;
import com.hotax.yygh.user.service.UserInfoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yygh.vo.user.LoginVo;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author: Jeff 2022-04-25 23:01
 * @description:
 **/
@RestController
@RequestMapping("/api/user")
public class UserInfoApiController {

    @Autowired
    private UserInfoService userInfoService;

    @ApiOperation(value = "会员登录")
    @PostMapping("login")
    public Result login(@RequestBody LoginVo loginVo, HttpServletRequest request) {
        loginVo.setIp(IpUtil.getIpAddr(request));
        Map<String, Object> info = userInfoService.login(loginVo);
        return Result.ok(info);
    }
}

