package com.zy.report.modules.user.service.impl;

import com.zy.report.commons.utils.RedisUtil;
import com.zy.report.modules.user.entity.SysUser;
import com.zy.report.modules.user.mapper.LoginMapper;
import com.zy.report.modules.user.service.LoginService;
import com.zy.report.security.sms.SendSms;
import com.zy.report.security.sms.SmsCode;
import com.zy.report.security.sms.SmsCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @program: web_template
 * @description: 用户登录service 实现类
 * @author: nile
 * @create: 2020-09-12 18:28
 **/
@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private LoginMapper loginMapper;

    @Resource
    private SmsCodeGenerator smsCodeGenerator;

    @Resource
    private RedisUtil redisUtil;

    @Override
    public SysUser getLoginInfo(String userName) {
        return loginMapper.getLoginInfo(userName);
    }

    @Override
    public SysUser selectByMobile(String mobile) {
        return loginMapper.selectByMobile(mobile);
    }

    @Override
    public void sendSmsCode(String mobile) {
        //redis中存储的过期时间180s
        final int expireTime = 180;
        SmsCode smsCode = smsCodeGenerator.generate();
        if(redisUtil.set(mobile, smsCode, expireTime)){
            SendSms.sendSms(mobile,smsCode.getCode());
        }
    }
}
