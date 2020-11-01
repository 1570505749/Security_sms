package com.zy.report.security.sms;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @program: test_security
 * @description: 短信验证码
 * @author: nile
 * @create: 2020-05-11 22:37
 **/
@Getter
@Setter
public class SmsCode implements Serializable {

    private String code;

}
