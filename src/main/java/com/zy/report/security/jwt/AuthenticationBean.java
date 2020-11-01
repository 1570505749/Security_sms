package com.zy.report.security.jwt;

import lombok.Getter;
import lombok.Setter;

/**
 * @program: web_template
 * @description: json用户名密码Bean
 * @author: nile
 * @create: 2020-10-17 13:08
 **/
@Getter
@Setter
public class AuthenticationBean {

    private String username;

    private String password;

    private boolean remember;

}
