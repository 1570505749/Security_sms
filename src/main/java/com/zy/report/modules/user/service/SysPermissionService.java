package com.zy.report.modules.user.service;


import com.zy.report.modules.user.vo.SysPermissionVo;

import java.util.List;

/**
 * @author Nile
 */
public interface SysPermissionService {

    /**
     * 获取指定角色所有权限
     */
    List<SysPermissionVo> listByRoleId(Long roleId);
}