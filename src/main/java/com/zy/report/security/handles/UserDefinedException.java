package com.zy.report.security.handles;


import com.zy.report.commons.enums.ResultEnum;

/**
 * @program: web_template
 * @description: 自定义异常
 * @author: nile
 * @create: 2020-10-18 14:39
 **/
public class UserDefinedException extends RuntimeException {

        private ResultEnum exception;

        private String message;

        public UserDefinedException(ResultEnum exception){
            this.exception = exception;
        }

        public UserDefinedException(ResultEnum exception,String message){
            this.exception = exception;
            this.message = message;
        }

        @Override
        public String getMessage() {
            return message;
        }

        public ResultEnum getException() {
            return exception;
        }

        public void setException(ResultEnum exception) {
            this.exception = exception;
        }
}
