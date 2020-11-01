package com.zy.report.modules.user.mapper;

import com.zy.report.modules.user.entity.SysUser;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @program: web_template
 * @description: 用户登录mapper
 * @author: nile
 * @create: 2020-09-12 18:27
 **/
@Mapper
@CacheNamespace
public interface LoginMapper {

    /**
     * 获取用户登录信息
     * @return
     */
    @Select("select * from sys_user where username = #{userName}")
    SysUser getLoginInfo(String userName);

    /**
     * 根据用户电话号码获取用户
     * @param mobile
     * @return
     */
    @Select("SELECT * FROM sys_user WHERE phone= #{mobile}")
    SysUser selectByMobile(String mobile);

}
