package com.zy.report.modules.user.service;


import com.zy.report.modules.user.vo.SysMenuVo;

import java.util.List;

/**
 * @program: report
 * @description: 菜单service
 * @author: nile
 * @create: 2020-09-13 18:49
 **/
public interface UserMenuService {

     /**
      * 根据ID获取菜单路径
      * @param menuId
      * @return
      */
     String getMenuName(Long menuId);

     /**
      * 根据RoleId获取菜单list
      * @return
      */
     List<SysMenuVo> getRoleMenu();
}
