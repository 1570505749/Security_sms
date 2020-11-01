package com.zy.report.modules.user.service;



import com.zy.report.modules.user.entity.SysUser;

import java.util.List;
import java.util.Map;

/**
 * report
 * @author Nile
 */
public interface SysUserService {

     /**
      * 根据用户ID获取用户
      * @param id
      * @return
      */
     SysUser selectById(Integer id);

     /**
      * 根据用户名获取用户
      * @param name
      * @return
      */
     SysUser selectByName(String name);

     /**
      * 获取用户列表
      * @return
      */
     List<SysUser> getUser();

     /**
      * 根据用户电话号码获取用户
      * @param mobile
      * @return
      */
     SysUser selectByMobile(String mobile);


     /**
      * 获取当前用户基本信息
      * @return
      */
     Map<String,Object> getUserInfo();
}