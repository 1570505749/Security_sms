package com.zy.report.modules.report.service.impl;

import com.zy.report.modules.report.mapper.ReportMarkingMapper;
import com.zy.report.modules.report.service.ReportMarkingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
/**
 * @program: clms
 * @description: 报告批阅service实现类
 * @author: nile
 * @create: 2020-03-24 16:17
 **/
@Service
public class ReportMarkingServiceImpl implements ReportMarkingService {

    @Resource
    private ReportMarkingMapper reportMarkingMapper;

}
