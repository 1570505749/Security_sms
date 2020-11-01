package com.zy.report.modules.user.vo;

import com.zy.report.modules.user.entity.SysPermission;
import lombok.Getter;
import lombok.Setter;

/**
 * @program: web_template
 * @description: 权限Vo
 * @author: nile
 * @create: 2020-10-06 15:34
 **/
@Setter
@Getter
public class SysPermissionVo extends SysPermission {

    private String name;

    private String mark;

}
