package com.zy.report.modules.report.service.impl;

import com.zy.report.commons.utils.DateUtils;
import com.zy.report.commons.utils.Page;
import com.zy.report.commons.utils.Result;
import com.zy.report.modules.report.entity.Report;
import com.zy.report.modules.report.mapper.ReportMapper;
import com.zy.report.modules.report.service.ReportService;
import com.zy.report.modules.user.entity.SysUser;
import com.zy.report.modules.user.mapper.SysUserMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @program: clms
 * @description: 报告service实现类
 * @author: nile
 * @create: 2020-03-24 16:16
 **/
@Service
public class ReportServiceImpl implements ReportService {

    @Resource
    private ReportMapper reportMapper;

    @Resource
    private SysUserMapper userMapper;

    @Override
    public String[] getReportTime() {
        return new String[]{reportMapper.getDailyTime(),reportMapper.getWeeklyTime()};
    }

    @Override
    public Integer getTime() {
        return reportMapper.getTime();
    }

    @Override
    public List<Report> getMinReportInfo() {
        SysUser user=userMapper.selectByName(SecurityContextHolder.getContext().getAuthentication().getName());
        return reportMapper.getMinReportInfo(user.getUserId());
    }

    @Override
    public Page<Report> getReportByUserId(Page<Report> page) {
        SysUser user=userMapper.selectByName(SecurityContextHolder.getContext().getAuthentication().getName());
        page.params.put("userId",user.getUserId());
        page.setList(reportMapper.getReportByUserId(page));;
        page.setTotalCount(reportMapper.getCountByPage(page));
        return page;
    }

    @Override
    public Result<String> save(Report report) throws ParseException {
        SysUser user=userMapper.selectByName(SecurityContextHolder.getContext().getAuthentication().getName());
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String[] reportTime = new String[]{reportMapper.getDailyTime(),reportMapper.getWeeklyTime()};
        String[] daily = reportTime[0].split(",");
        String[] weekly = reportTime[1].split(",");
        int ss = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)-1;
        if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY) >= reportMapper.getTime() ){
            return new Result<>(401,"时间已截止");
        }
        int item = 0;
        if(report.getReportType() == 0) {
            for (String i : daily) {
                if ("".equals(i)){
                    return new Result<>(401, "日报提交未开启");
                }
                if (ss == Integer.parseInt(i)) {
                    item = 1;
                    if (reportMapper.getTodayUserReport(user.getUserId(), sdf.format(new Date()), 0, null)!= 0) {
                        return new Result<>(401, "已存在今日日报数据");
                    }
                    break;
                }
            }
            if( item == 0){
                return new Result<>(401, "日报提交未开启");
            }
        } else {
            for (String i : weekly) {
                if ("".equals(i)){
                    return new Result<>(401, "周报提交未开启");
                }
                if (ss == Integer.parseInt(i)) {
                    item = 1;
                    DateUtils dateUtils = new DateUtils();
                    String[] results = dateUtils.getDateWeek(sdf.format(new Date()));
                    if (reportMapper.getTodayUserReport(user.getUserId(), null, 1, results) != 0) {
                        return new Result<>(401, "已存在本周周报数据");
                    }
                    break;
                }
            }
            if( item == 0){
                return new Result<>(401, "周报提交未开启");
            }
        }
        try {
            reportMapper.save(report);
            reportMapper.addUserReport(user.getUserId(), report.getReportId());
        } catch (Exception e){
            return new Result<>(401,"添加失败");
        }
        return new Result<>("添加成功");
    }

    @Override
    public void update(Report report) {
        reportMapper.update(report);
    }

    @Override
    public void addUserReport(Long userId,Long reportId) { reportMapper.addUserReport(userId,reportId); }

    @Override
    public int getTodayUserReport(Long userId,String nowToday, Integer reportType ,String[] results) {
        return reportMapper.getTodayUserReport(userId,nowToday,reportType,results);
    }

    @Override
    public void setReportTime(String[] reportTime) {
        reportMapper.setDailyTime(reportTime[0]);
        reportMapper.setWeeklyTime(reportTime[1]);
    }

    public static <T> List<T> compare(T[] t1, T[] t2) {
        List<T> list1 = Arrays.asList(t1);
        List<T> list2 = new ArrayList<T>();
        for (T t : t2) {
            if (!list1.contains(t)) {
                list2.add(t);
            }
        }
        return list2;
    }

}
