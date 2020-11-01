package com.zy.report.security.customauths;

import com.zy.report.modules.user.vo.SysPermissionVo;
import com.zy.report.modules.user.service.SysPermissionService;
import com.zy.report.modules.user.service.SysRoleService;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @author Nile
 */
@Component
public class CustomPermissionEvaluator implements PermissionEvaluator {

    @Resource
    private SysPermissionService permissionService;

    @Resource
    private SysRoleService roleService;


    @Override
    public boolean hasPermission(Authentication authentication, Object targetUrl, Object targetPermission) {
        // 获得loadUserByUsername()方法的结果
        User user = (User)authentication.getPrincipal();
        // 获得loadUserByUsername()中注入的角色
        Collection<GrantedAuthority> authorities = user.getAuthorities();

        // 遍历用户所有角色
        for(GrantedAuthority authority : authorities) {
            String roleName = authority.getAuthority();
            Long roleId = roleService.selectByName(roleName).getRoleId();
            // 得到角色所有的权限
            List<SysPermissionVo> permissionList = permissionService.listByRoleId(roleId);

            // 遍历permissionList
            for(SysPermissionVo sysPermission : permissionList) {
                // 获取权限集
                List<String> permissions = sysPermission.getPermissions();
                // 如果访问的权限标识和用户权限符合的话，返回true
                if(targetUrl.equals(sysPermission.getMark())
                        && permissions.contains(targetPermission.toString())) {
                    return true;
                }
            }

        }

        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable serializable, String s, Object o) {
        return false;
    }
}