package com.zy.report.modules.user.service;


import com.zy.report.modules.user.entity.SysUserRole;

import java.util.List;

/**
 * @author Nile
 */
public interface SysUserRoleService {

    /**
     * 根据用户ID获取用户权限ID
     * @param userId
     * @return
     */
    List<SysUserRole> listByUserId(Long userId);

}