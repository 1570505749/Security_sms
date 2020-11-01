package com.zy.report.modules.user.mapper;

import com.zy.report.modules.user.entity.SysUserRole;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Nile
 */
@Mapper
@CacheNamespace
public interface SysUserRoleMapper {

    /**
     * 根据用户ID获取用户权限ID
     * @param userId
     * @return
     */
    @Select("SELECT * FROM sys_user_role WHERE user_id = #{userId}")
    List<SysUserRole> listByUserId(Long userId);
}