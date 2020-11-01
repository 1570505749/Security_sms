package com.zy.report.security.handles;

import com.zy.report.commons.enums.ResultEnum;
import com.zy.report.commons.utils.Result;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @program: web_template
 * @description: Spring mvc全局异常处理器
 * @author: nile
 * @create: 2020-10-18 14:37
 **/
@RestControllerAdvice
public class GlobalExceptionHandler<T> {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result<Object> sendErrorResponseSystem(Exception exception){
        if (exception instanceof HttpRequestMethodNotSupportedException) {
            return new Result<>(ResultEnum.METHOD_NOT_ALLOWED,exception.getMessage());
        } else if(exception instanceof UserDefinedException){
            return new Result<>(((UserDefinedException) exception).getException(),exception.getMessage());
        }
        return new Result<>(ResultEnum.UNKNOWN_MISTAKE,exception.getMessage());
    }

}
