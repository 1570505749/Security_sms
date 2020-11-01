package com.zy.report.modules.user.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: web_template
 * @description: 用户登录实体类
 * @author: nile
 * @create: 2020-09-12 18:23
 **/

@Data
public class SysUser implements Serializable {

    static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户头像地址
     */
    private String header;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 用户邮箱地址
     */
    private String mail;

    /**
     * 签名
     */
    private String signature;

    /**
     * 描述
     */
    private String description;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 性别
     */
    private String sex;

    /**
     * 地址
     */
    private String address;

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

    /**
     * 乐观锁
     */
    private Integer version;

    /**
     * 用户状态位：1 启用  0 弃用
     */
    private Integer isEnabled;

    /**
     * 逻辑删除位： 1 已删除 0未删除
     */
    private Integer isDeleted;

}
