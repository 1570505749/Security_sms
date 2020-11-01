package com.zy.report.modules.user.service.impl;

import com.zy.report.modules.user.vo.SysMenuVo;
import com.zy.report.modules.user.mapper.UserMenuMapper;
import com.zy.report.modules.user.service.SysRoleService;
import com.zy.report.modules.user.service.UserMenuService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @program: web_template
 * @description: 菜单业务层
 * @author: nile
 * @create: 2020-09-12 22:35
 **/
@Service
public class UserMenuServiceImpl implements UserMenuService {
    @Resource
    private UserMenuMapper menuMapper;

    @Resource
    private SysRoleService roleService;

    @Override
    public String getMenuName(Long menuId){
        return menuMapper.getMenuName(menuId);
    }

    @Override
    public List<SysMenuVo> getRoleMenu() {
        List<SysMenuVo> sysMenuList = new ArrayList<>();
        // 获得loadUserByUsername()方法的结果
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // 获得loadUserByUsername()中注入的角色
        Collection<GrantedAuthority> authorities = user.getAuthorities();
        // 遍历用户所有角色
        for(GrantedAuthority authority : authorities) {
            String roleName = authority.getAuthority();
            Long roleId = roleService.selectByName(roleName).getRoleId();
            // 得到角色所有的权限
            List<SysMenuVo> menuList = menuMapper.getRoleMenu(roleId);
            sysMenuList.addAll(menuList);
        }
        return sysMenuList;
    }


}
