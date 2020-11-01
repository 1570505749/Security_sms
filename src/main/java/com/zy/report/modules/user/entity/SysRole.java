package com.zy.report.modules.user.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: web_template
 * @description: 角色表
 * @author: nile
 * @create: 2020-09-12 20:01
 **/
@Data
public class SysRole implements Serializable {

    static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 创建时间
     */
    private String createdTime;

    /**
     * 更新时间
     */
    private String updateTime;

    /**
     * 乐观锁
     */
    private Integer version;

    /**
     * 是否启用，0否1是 默认1
     */
    private Integer isEnabled;

    /**
     * 是否删除，0否1是 默认0
     */
    private Integer isDeleted;
}
