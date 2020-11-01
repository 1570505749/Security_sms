package com.zy.report.security.sms;

import com.zy.report.commons.utils.RedisUtil;
import com.zy.report.security.jwt.JwtAuthTokenFilter;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: test_security
 * @description: ValidateCodeFilter
 * @author: nile
 * @create: 2020-05-15 16:49
 **/
public class ValidateCodeFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthTokenFilter.class);

    private AuthenticationFailureHandler authenticationFailureHandler;

    private RedisUtil redisUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse,FilterChain filterChain) throws ServletException, IOException {
        boolean result = StringUtils.equals("/authentication/mobile", httpServletRequest.getRequestURI());
        if(result && StringUtils.equalsIgnoreCase(httpServletRequest.getMethod(), "POST")){
            try {
                validateSmsCode(httpServletRequest);
                redisUtil.del(httpServletRequest.getParameter("mobile"));
            }catch (BadCredentialsException e) {
               authenticationFailureHandler.onAuthenticationFailure(httpServletRequest,httpServletResponse,e);
               return;
            }
        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }

    /**
     * 校验手机验证码
     */
    private void validateSmsCode(HttpServletRequest request) {
        //请求里的手机号和验证码
        String mobileInRequest = request.getParameter("mobile");
        String codeInRequest = request.getParameter("smsCode");

        try {
            SmsCode code = (SmsCode)redisUtil.get(mobileInRequest);

            if (StringUtils.isBlank(codeInRequest)) {
                logger.error("sms validate error : 验证码的值不能为空! -> phone: {}",mobileInRequest);
                throw new BadCredentialsException("验证码的值不能为空!");
            }

            if(!StringUtils.equals(code.getCode(), codeInRequest)) {
                logger.error("sms validate error : 验证码不匹配!->phone: {}",mobileInRequest);
                throw new BadCredentialsException("验证码不匹配!");
            }
        } catch (NullPointerException e){
            logger.error("sms validate error : 验证码已失效! ->phone: {}",mobileInRequest);
            throw new BadCredentialsException("验证码已失效!");
        }

    }

    public AuthenticationFailureHandler getAuthenticationFailureHandler() {
        return authenticationFailureHandler;
    }

    public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    public void setRedisUtil(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

}