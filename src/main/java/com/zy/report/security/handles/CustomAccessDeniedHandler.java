package com.zy.report.security.handles;

import com.alibaba.fastjson.JSON;
import com.zy.report.commons.enums.ResultEnum;
import com.zy.report.commons.utils.Result;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: web_template
 * @description: 自定义无权限处理器
 * @author: nile
 * @create: 2020-09-13 17:15
 **/
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(ResultEnum.ACCESS_FORBIDDEN.getCode());
        response.getWriter().write(JSON.toJSONString(new Result<>(ResultEnum.NOT_AUTHORITY)));
    }

}
