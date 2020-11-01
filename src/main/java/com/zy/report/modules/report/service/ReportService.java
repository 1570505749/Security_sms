package com.zy.report.modules.report.service;

import com.zy.report.commons.utils.Page;
import com.zy.report.commons.utils.Result;
import com.zy.report.modules.report.entity.Report;

import java.text.ParseException;
import java.util.List;

/**
 * @program: clms
 * @description: 报告service
 * @author: nile
 * @create: 2020-03-24 16:14
 **/
public interface ReportService {

    /**
     * 保存 添加报告
     */
    Result<String> save(Report report) throws ParseException;

    /**
     * 修改报告
     */
    void update(Report report);

    /**
     * 根据user_id和report_type查找个人报告
     */
    Page<Report> getReportByUserId(Page<Report> page);

    /**
     * 保存报告后添加cl_user_report表的数据
     * @param userId
     * @param reportId
     */
    void addUserReport(Long userId, Long reportId);

    /**
     * 获取数据库用户今日是否存在报告
     * @param nowToday
     * @return
     */
    int  getTodayUserReport(Long userId, String nowToday, Integer reportType, String[] results);

    /**
     * 获取报告简要批阅信息
     * @param
     * @return
     */
    List<Report> getMinReportInfo();

    /**
     * 获取日报截止时间
     * @return
     */
    Integer getTime();

    /**
     *
     *  获取报告可提交时间
     * @return
     */
    String[] getReportTime();

    /**
     * 设置报告提交日期
     * @param reportTime
     */
    void setReportTime(String[] reportTime);
}
