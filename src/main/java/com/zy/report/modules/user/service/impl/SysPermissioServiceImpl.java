package com.zy.report.modules.user.service.impl;


import com.zy.report.modules.user.vo.SysPermissionVo;
import com.zy.report.modules.user.mapper.SysPermissionMapper;
import com.zy.report.modules.user.service.SysPermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: web_template
 * @description: 11
 * @author: nile
 * @create: 2020-09-13 18:51
 **/
@Service
public class SysPermissioServiceImpl implements SysPermissionService {

    @Resource
    private SysPermissionMapper permissionMapper;

    /**
     * 获取指定角色所有权限
     */
    @Override
    public List<SysPermissionVo> listByRoleId(Long roleId) {
        return permissionMapper.listByRoleId(roleId);
    }
}
