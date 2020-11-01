package com.zy.report.security.handles;

import com.alibaba.fastjson.JSON;
import com.zy.report.commons.enums.ResultEnum;
import com.zy.report.commons.utils.Result;
import com.zy.report.modules.user.mapper.SysUserMapper;
import com.zy.report.security.jwt.JwtProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @author: nile
 * @create: 2020-03-22 22:19
 *自定义 CustomAuthenticationSuccessHandler 类来实现 AuthenticationSuccessHandler 接口，用来处理认证成功后逻辑：
 *onAuthenticationSuccess() 方法返回认证成功信息。
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Resource
    private JwtProvider jwtProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException{
        response.setContentType("application/json;charset=utf-8");
        boolean isRemember = (boolean) request.getAttribute("isRemember");
        String jwtToken = jwtProvider.generateJwtToken(isRemember,authentication);
        response.getWriter().write(JSON.toJSONString(new Result<Object>(ResultEnum.GET_SUCCESS,jwtToken)));
    }
}