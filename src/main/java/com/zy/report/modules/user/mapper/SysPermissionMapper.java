package com.zy.report.modules.user.mapper;

import com.zy.report.modules.user.vo.SysPermissionVo;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/**
 * @author Nile
 */
@Mapper
@CacheNamespace
public interface SysPermissionMapper {

    /**
     * 获取当前用户的权限
     * @param roleId
     * @return
     */
    @Select("SELECT a.*,b.name,b.mark FROM sys_permission a left join sys_menu b on a.menu_id = b.menu_id WHERE role_id=#{roleId}")
    List<SysPermissionVo> listByRoleId(Long roleId);
}