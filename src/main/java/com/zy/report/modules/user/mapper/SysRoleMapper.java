package com.zy.report.modules.user.mapper;

import com.zy.report.modules.user.entity.SysRole;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author Nile
 */
@Mapper
@CacheNamespace
public interface SysRoleMapper {

    /**
     * 根据用户id获取权限列表
     * @param id
     * @return
     */
    @Select("SELECT * FROM sys_role WHERE role_id = #{id}")
    SysRole selectById(Integer id);

    /**
     * 根据用户名获取权限列表
     * @param name
     * @return
     */
    @Select("SELECT * FROM sys_role WHERE role_name = #{name}")
    SysRole selectByName(String name);

}
