package com.zy.report.modules.report.controller;

import com.zy.report.modules.report.entity.Report;
import com.zy.report.modules.report.service.ReportMarkingService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: clms
 * @description: 报告批阅实体类
 * @author: nile
 * @create: 2020-03-24 16:20
 **/
@RestController
@RequestMapping("/reportMarking")
public class ReportMarkingController {

    @Resource
    private ReportMarkingService reportMarkingService;

}
