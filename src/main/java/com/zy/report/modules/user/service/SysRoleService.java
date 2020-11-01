package com.zy.report.modules.user.service;


import com.zy.report.modules.user.entity.SysRole;

/**
 * @author Nile
 */
public interface SysRoleService {

    /**
     * 根据用户id获取权限列表
     * @param id
     * @return
     */
    SysRole selectById(Integer id);

    /**
     * 根据用户名获取权限列表
     * @param name
     * @return
     */
    SysRole selectByName(String name);
}