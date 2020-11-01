package com.zy.report.modules.user.service.impl;

import com.zy.report.modules.user.entity.SysUserRole;
import com.zy.report.modules.user.mapper.SysUserRoleMapper;
import com.zy.report.modules.user.service.SysUserRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: web_template
 * @description: SysUserRoleServiceImpl
 * @author: nile
 * @create: 2020-09-13 18:51
 **/
@Service
public class SysUserRoleServiceImpl implements SysUserRoleService {

    @Resource
    private SysUserRoleMapper userRoleMapper;

    @Override
    public List<SysUserRole> listByUserId(Long userId) {
        return userRoleMapper.listByUserId(userId);
    }
}
