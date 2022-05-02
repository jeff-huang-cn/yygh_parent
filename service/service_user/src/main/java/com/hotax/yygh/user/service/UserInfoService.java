package com.hotax.yygh.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import yygh.model.user.UserInfo;
import yygh.vo.user.LoginVo;
import yygh.vo.user.UserAuthVo;

import java.util.Map;

/**
 * @author: Jeff 2022-04-25 23:02
 * @description:
 **/
public interface UserInfoService extends IService<UserInfo> {
    //会员登录
    Map<String, Object> login(LoginVo loginVo);

    UserInfo selectWxInfoOpenId(String openId);

    void userAuth(Long userId, UserAuthVo userAuthVo);
}

