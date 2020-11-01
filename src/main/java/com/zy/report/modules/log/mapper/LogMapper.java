package com.zy.report.modules.log.mapper;


import com.zy.report.commons.utils.Page;
import com.zy.report.modules.log.entity.Log;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Nile
 */
@Mapper
@Repository
public interface LogMapper {
    /**
     * 保存日志信息
     *
     * @param logger
     */
    @Insert("insert into sys_log(log_url, log_params, log_status, log_message, log_method, log_time, log_result, log_ip) " +
            "values (#{logUrl}, #{logParams}, #{logStatus},  #{logMessage}, #{logMethod}, #{logTime}, #{logResult}, #{logIp})")
    void save(Log logger);

    /**
     * 分页查询
     *
     * @param page
     * @return
     */
    @Select("<script>select\n" +
            "        log_id, log_url, log_params, log_status, log_message,\n" +
            "        log_method, log_time, log_result, log_ip, created_time\n" +
            "        from sys_log\n" +
            "        <where>\n" +
            "            <if test=\"params.logUrl!=null and params.logUrl!=''\">\n" +
            "                and log_url = #{params.logUrl}\n" +
            "            </if>\n" +
            "            <if test=\"params.logStatus!=null\">\n" +
            "                and log_status = #{params.logStatus}\n" +
            "            </if>\n" +
            "            <if test=\"params.logMethod!=null and params.logMethod!=''\">\n" +
            "                and log_method = #{params.logMethod}\n" +
            "            </if>\n" +
            "            <if test=\"params.logIp!=null and params.logIp!=''\">\n" +
            "                and log_ip = #{params.logIp}\n" +
            "            </if>\n" +
            "        </where>\n" +
            "        <if test=\"sortColumn!=null and sortColumn!=''\">\n" +
            "            order by ${sortColumn} ${sortMethod}\n" +
            "        </if>\n" +
            "        limit #{index}, #{pageSize}" +
            "</script>")
    List<Log> getByPage(Page<Log> page);

    /**
     * 分页查询时统计总数
     *
     * @param page
     * @return
     */
    @Select("<script>select\n" +
            "        count(*)\n" +
            "        from sys_log\n" +
            "        <where>\n" +
            "            <if test=\"params.logUrl!=null and params.logUrl!=''\">\n" +
            "                and log_url = #{params.logUrl}\n" +
            "            </if>\n" +
            "            <if test=\"params.logStatus!=null\">\n" +
            "                and log_status = #{params.logStatus}\n" +
            "            </if>\n" +
            "            <if test=\"params.logMethod!=null and params.logMethod!=''\">\n" +
            "                and log_method = #{params.logMethod}\n" +
            "            </if>\n" +
            "            <if test=\"params.logIp!=null and params.logIp!=''\">\n" +
            "                and log_ip = #{params.logIp}\n" +
            "            </if>\n" +
            "        </where>" +
            "</script>")
    int getCountByPage(Page<Log> page);

    /**
     * 根据id删除
     * @param id
     */
    @Delete("delete from sys_log where log_id = #{id}")
    void deleteById(Integer id);

    /**
     * 根据id集合删除
     * @param ids
     */
    @Delete("<script>" +
            "        delete from sys_log\n" +
            "        where log_id in\n" +
            "        <foreach collection=\"list\" separator=\",\" item=\"id\" open=\"(\" close=\")\">\n" +
            "            #{id}\n" +
            "        </foreach>" +
            "</script>")
    void deleteByIds(List<Integer> ids);

}
