package com.zy.report.modules.user.mapper;

import com.zy.report.modules.user.vo.SysMenuVo;
import org.apache.ibatis.annotations.*;

import java.util.List;


/**
 * @program: web_template
 * @description: 菜单mapper
 * @author: nile
 * @create: 2020-09-12 22:32
 **/
@Mapper
@CacheNamespace
public interface UserMenuMapper {

    /**
     * 获取菜单名
     * @param roleId
     * @return
     */
    @Select("select * from user_menu where role_id = #{roleId} and status = 0")
    String getMenuName(Long roleId);

    /**
     * 根据RoleId获取菜单list
     * @param roleId
     * @return
     */
    @Select("select menu_name as name, path,component, menu_type = 'C' as is_loading,icon,menu_name as title from user_menu where role_id = #{roleId} and status = 0")
    @Results({
            @Result(column = "is_loading", property = "mate.isLoading"),
            @Result(column = "icon", property = "mate.icon"),
            @Result(column = "title", property = "mate.title")})
    List<SysMenuVo> getRoleMenu(Long roleId);
}
