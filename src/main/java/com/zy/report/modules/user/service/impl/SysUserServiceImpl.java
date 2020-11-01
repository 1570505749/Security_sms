package com.zy.report.modules.user.service.impl;

import com.zy.report.modules.user.entity.SysUser;
import com.zy.report.modules.user.mapper.SysUserMapper;
import com.zy.report.modules.user.service.SysUserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: web_template
 * @description: SysUserServiceImpl
 * @author: nile
 * @create: 2020-09-13 18:52
 **/
@Service
public class SysUserServiceImpl implements SysUserService {

    @Resource
    private SysUserMapper userMapper;

    @Override
    public SysUser selectById(Integer id) {
        return userMapper.selectById(id);
    }

    @Override
    public SysUser selectByName(String name) {
        return userMapper.selectByName(name);
    }

    @Override
    public List<SysUser> getUser(){
        return  userMapper.getUser();
    }

    @Override
    public SysUser selectByMobile(String mobile){return  userMapper.selectByMobile(mobile);}

    @Override
    public Map<String, Object> getUserInfo() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        SysUser sysUser = userMapper.selectByName(user.getUsername());
        sysUser.setPassword("");
        Map<String,Object> result = new HashMap<>(2);
        result.put("userInfo",sysUser);
        result.put("roles",user.getAuthorities());
        return result;
    }
}
