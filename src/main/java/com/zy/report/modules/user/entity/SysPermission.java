package com.zy.report.modules.user.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @program: web_template
 * @description: 角色权限实体类
 * @author: nile
 * @create: 2020-09-12 20:04
 **/
@Data
public class SysPermission implements Serializable {

    static final long serialVersionUID = 1L;

    private String menuId;

    private Long roleId;

    private String permission;

    private List<String> permissions;

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

    public List<String> getPermissions() {
        return Arrays.asList(this.permission.trim().split(","));
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }
}
