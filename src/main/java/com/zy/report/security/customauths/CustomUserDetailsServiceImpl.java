package com.zy.report.security.customauths;

import com.zy.report.modules.user.entity.SysRole;
import com.zy.report.modules.user.entity.SysUser;
import com.zy.report.modules.user.entity.SysUserRole;
import com.zy.report.modules.user.service.LoginService;
import com.zy.report.modules.user.service.SysRoleService;
import com.zy.report.modules.user.service.SysUserRoleService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author: nile
 * @create: 2020-03-22 22:19
 *Security 注册用户类
 */

@Service("userDetailsService")
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private LoginService loginService;

    @Resource
    private SysUserRoleService userRoleService;

    @Resource
    private SysRoleService roleService;

    /**
     * 添加认证信息
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = loginService.getLoginInfo(username);
        return generateUserDetails(user);
    }

    /**
     * 添加认证信息
     */
    public UserDetails loadUserByMobile(String mobile) throws UsernameNotFoundException {
        SysUser user = loginService.selectByMobile(mobile);
        return generateUserDetails(user);
    }

    public UserDetails generateUserDetails(SysUser user){
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        // 判断用户是否存在
        if(user == null) {
            throw new UsernameNotFoundException("用户名/手机号不存在");
        }
        // 添加权限
        List<SysUserRole> userRoles = userRoleService.listByUserId(user.getUserId());
        for (SysUserRole userRole : userRoles) {
            SysRole role = roleService.selectById(userRole.getRoleId());
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        // 返回
        return new org.springframework.security.core.userdetails.User(user.getUserName(),user.getPassword(),authorities);
    }
}