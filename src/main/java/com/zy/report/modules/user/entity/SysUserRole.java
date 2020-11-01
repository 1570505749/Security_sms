package com.zy.report.modules.user.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: web_template
 * @description: 用户-角色实体类
 * @author: nile
 * @create: 2020-09-12 19:56
 **/
@Data
public class SysUserRole implements Serializable {

    static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 角色ID
     */
    private Integer roleId;

    /**
     * 创建时间，返回时转成正常日期格式 年-月-日
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createdTime;

    /**
     * 更新时间，返回时转成正常日期格式 年-月-日
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;
}
