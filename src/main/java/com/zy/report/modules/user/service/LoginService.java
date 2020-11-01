package com.zy.report.modules.user.service;


import com.zy.report.modules.user.entity.SysUser;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @program: web_template
 * @description: 用户登录Service
 * @author: nile
 * @create: 2020-09-12 18:28
 **/
public interface LoginService {

    /**
     * 用户登录验证
     * @param userName
     * @return
     */
    SysUser getLoginInfo(String userName);

    /**
     * 根据用户电话号码获取用户
     * @param mobile
     * @return
     */
    SysUser selectByMobile(String mobile);

    /**
     * 用户发送短信验证码
     * @param mobile
     */
    void sendSmsCode(@RequestParam String mobile);

}
