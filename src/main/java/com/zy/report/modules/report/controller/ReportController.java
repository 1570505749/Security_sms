package com.zy.report.modules.report.controller;

import com.zy.report.commons.utils.Page;
import com.zy.report.commons.utils.Result;
import com.zy.report.modules.report.entity.Report;
import com.zy.report.modules.report.service.ReportService;
import com.zy.report.modules.user.entity.SysUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * @program: clms
 * @description: 报告控制类
 * @author: nile
 * @create: 2020-03-24 16:19
 **/
@RestController
@RequestMapping("/report")
public class ReportController {

    @Resource
    private ReportService reportService;

    /**
     *
     *  获取报告可提交时间
     * @return String["日报提交时间","周报提交时间"]
     */
    @GetMapping("/getReportTime")
    public Result<String[]> getReportTime(){
        return new Result<>(reportService.getReportTime());
    }

    /**
     * 获取日报截止时间
     * @return Integer -> hour
     */
    @GetMapping("/getTime")
    public Result<Integer> getTime(){
        return new Result<>(reportService.getTime());
    }

    /**
     * 根据user_id、日期和reportType分页查询报告
     *
     */
    @PostMapping("/getMinReportInfo")
    public Result<List<Report>> getMinReportInfo(){
        List<Report> reports =reportService.getMinReportInfo();
        return new Result<>(reports);
    }

    /**
     * 根据user_id、日期和reportType分页查询报告
     * @param page
     */
    @PostMapping("/getByUserId")
    public Result<Page<Report>> getByUserId(@RequestBody Page<Report> page){
        return new Result<>(reportService.getReportByUserId(page));
    }

    /**
     * 新增报告
     * @param report
     * @return
     */
    @PostMapping("/save")
    public Result<String> save(@RequestBody Report report) throws ParseException {
        return reportService.save(report);
    }

    /**
     * 设置报告提交日期
     * @param reportTime
     */
    @PostMapping("/setReportTime")
    public Result<Object> setReportTime(@RequestBody String[] reportTime){
        reportService.setReportTime(reportTime);
        return new Result<>("更新成功");
    }

}
