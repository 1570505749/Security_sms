package com.zy.report.modules.report.mapper;

import com.zy.report.commons.utils.Page;
import com.zy.report.modules.report.entity.Report;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: clms
 * @description: 报告mapper
 * @author: nile
 * @create: 2020-03-24 16:14
 **/
@Mapper
@Repository
@CacheNamespace
public interface ReportMapper {

    /**
     * 保存 添加报告
     */
    @Insert("insert into user_report(report_type,work_content,difficulty,solution,experience,plan)\n" +
            "values(#{reportType},#{workContent},#{difficulty},#{solution},#{experience},#{plan})" )
    @Options(useGeneratedKeys=true, keyProperty="reportId", keyColumn="report_id")
    void save(Report report);

    /**
     * 根据report_id修改报告
     */
    @Update({"<script> \n" +
            "update user_report set report_id = #{reportId}\n" +
            "<if test='workContent!=null' > \n" +
            ",work_content = #{workContent} \n" +
            "</if> \n" +
            "<if test='difficulty!=null' > \n" +
            ",difficulty = #{difficulty} \n" +
            "</if> \n" +
            "<if test='solution!=null' > \n" +
            ",solution = #{solution} \n" +
            "</if> \n" +
            "<if test='experience != null' > \n" +
            ",experience = #{experience} \n" +
            "</if> \n" +
            "<if test='plan!=null' > \n" +
            ",plan = #{plan} \n"+
            "</if> \n" +
            "where report_id = #{reportId} and is_enabled = 0 and is_deleted = 0 \n"+
            "</script>"})
    void update(Report report);

    /**
     * 获取用户报告总数
     * @return
     */
    @Select("select count(*) from user_report a  left join  user_sys_user_report b on a.report_id = b.report_id  where b.user_id = #{params.userId} and a.report_type = #{params.reportType} and a.is_deleted = 0")
    int getCountByPage(Page<Report> page);


    /**
     * 根据user_id、起止日期和report_type查找个人报告
     */
    @Select({"<script> \n"+
            "select b.*,c.username,x.codename userGroupId,y.codename userClassesId  from user_sys_user_report a left join user_report b on a.report_id = b.report_id left join sys_user c on a.user_id = c.user_id \n"+
            "left join user_dict x on x.type='group' and x.code = c.user_group_id \n" +
            "left join user_dict y on y.type='classes' and y.code = c.user_classes_id \n"+
            "where c.user_id = #{params.userId} and b.report_type = #{params.reportType} and b.is_deleted = 0 \n"+
            "<if test=\"params.reportDate !=null and params.reportDate[0] != null  and params.reportDate[0] !='' \"  > \n" +
            "and b.created_time &gt;= #{params.reportDate[0]}\n" +
            "</if> \n"+
            "<if test=\" params.userPositionId != null and params.userPositionId !='' \"  > \n" +
            "and c.user_position_id = #{params.userPositionId}\n" +
            "</if> \n"+
            "<if test=\"sortColumn != null and sortColumn!=''\">\n" +
            "order by ${sortColumn} ${sortMethod}\n" +
            "</if>\n" +
            "</script>"})
    List<Report> getReportByUserId(Page<Report> page);

    /**
     *插入user_sys_user_report数据
     * @param userId
     * @param reportId
     */
    @Insert("insert into user_sys_user_report(user_id,report_id) value(#{userId},#{reportId})")
    void addUserReport(Long userId, Long reportId);

    /**
     * 判断数据库当前是否存在报告
     * @param userId
     * @param nowToday
     * @param reportType
     * @param results
     * @return
     */
    @Select({"<script> \n"+
            "select count(*) from user_sys_user_report a left join user_report b on a.report_id=b.report_id  \n" +
            " where a.user_id = #{userId} and b.report_type = #{reportType} and b.is_deleted = 0 \n" +
            "<if test='nowToday!=null' > \n" +
            "and created_time &gt;= #{nowToday}\n" +
            "</if> \n" +
            "<if test='results!=null' > \n" +
            "and b.created_time &lt;= #{results[1]}\n" +
            "and b.created_time &gt;= #{results[0]}\n" +
            "</if> \n"+
            "</script>"})
    int getTodayUserReport(Long userId, String nowToday, Integer reportType, String[] results);


    /**
     * 获取报告简要批阅信息
     * @param userId
     * @return
     */
    @Select({"<script> \n"+
            "select b.report_id,b.report_type,b.created_time,\n" +
            "CASE b.is_checked+b.is_classes_checked+b.is_teacher_checked WHEN 1 THEN d.group_time WHEN 2 THEN d.monitor_time WHEN 3 THEN d.teacher_time ELSE b.updated_time END as updated_time,\n" +
            "b.is_checked+b.is_classes_checked+b.is_teacher_checked as is_checked from \n" +
            "user_sys_user_report a left join user_report b on a.report_id = b.report_id left join sys_user c on a.user_id = c.user_id \n"+
            "left join user_report_marking d on a.report_id = d.report_id \n"+
            "where c.user_id = #{userId} and b.is_deleted = 0 and b.is_checked = 1\n"+
            "order by b.updated_time desc\n" +
            "limit 0, 5" +
            "</script>"})
    List<Report> getMinReportInfo(Long userId);

    /**
     * 获取日报提交时间范围
     * @return
     */
    @Select("select codename from user_dict where type='report' and typename='日报提交范围' and code = 1")
    String getDailyTime();

    /**
     * 获取周报提交时间范围
     * @return
     */
    @Select("select codename from user_dict where type='report' and typename='周报提交范围' and code = 2")
    String getWeeklyTime();

    /**
     * 设置日报提交时间范围
     * @param dailyTime
     * @return
     */
    @Update("update user_dict set codename=#{dailyTime} where type='report' and typename='日报提交范围' and code = 1")
    void setDailyTime(String dailyTime);

    /**
     * 设置周报提交时间范围
     * @param weeklyTime
     * @return
     */
    @Update("update user_dict set codename=#{weeklyTime} where type='report' and typename='周报提交范围' and code = 2")
    void setWeeklyTime(String weeklyTime);

    /**
     * 获取日报截止时间
     * @return
     */
    @Select("select code from user_dict where type='report' and typename='报告截止时间' and codename='日报截止时间' ")
    Integer getTime();

}

