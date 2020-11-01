package com.zy.report.modules.user.mapper;

import com.zy.report.modules.user.entity.SysUser;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/**
 * @author Nile
 */
@Mapper
@CacheNamespace
public interface SysUserMapper {

    /**
     * 根据用户ID获取用户
     * @param id
     * @return
     */
    @Select("SELECT * FROM sys_user WHERE user_id = #{id}")
    SysUser selectById(Integer id);

    /**
     * 根据用户名获取用户
     * @param name
     * @return
     */
    @Select("SELECT * FROM sys_user WHERE username = #{name}")
    SysUser selectByName(String name);

    /**
     * 根据用户电话号码获取用户
     * @param mobile
     * @return
     */
    @Select("SELECT * FROM sys_user WHERE phone= #{mobile}")
    SysUser selectByMobile(String mobile);

    /**
     * 获取用户列表
     * @return
     */
    @Select("SELECT * FROM sys_user")
    List<SysUser> getUser();

    /**
     * 获取班级成员名单
     * @return
     */
    @Select("<script>" +
            "SELECT\n" +
            "\tNAME \n" +
            "FROM\n" +
            "cl_user \n" +
            "WHERE\n" +
            "\tis_enabled = 1 \n" +
            "<if test='group != 0' > \n" +
            "\tAND user_group_id = #{group}"+
            "</if> \n"+
            "\tAND is_deleted = 0 \n" +
            "\tAND user_position_id IN (\n" +
            "\t\t0,\n" +
            "\t1,\n" +
            "\t2)"+
            "</script>")
    String[] getClassNames(@Param("group") Integer group);

}