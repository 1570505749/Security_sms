package com.zy.report.modules.log.service.impl;

import com.zy.report.commons.utils.Page;
import com.zy.report.modules.log.entity.Log;
import com.zy.report.modules.log.mapper.LogMapper;
import com.zy.report.modules.log.service.LogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Nile
 */
@Service
public class LogServiceImpl implements LogService {

    @Resource
    private LogMapper logMapper;

    /**
     * 保存
     * @param logger
     */
    @Override
    public void save(Log logger) {
        logMapper.save(logger);
    }

    /**
     * 分页查询
     * @param page
     * @return
     */
    @Override
    public Page<Log> getByPage(Page<Log> page) {
        // 查询数据
        List<Log> logList = logMapper.getByPage(page);
        page.setList(logList);
        // 查询总数 [查询数据之后统计总数]
        int totalCount = logMapper.getCountByPage(page);
        page.setTotalCount(totalCount);
        return page;
    }

    /**
     * 根据id删除
     * @param id
     */
    @Override
    public void deleteById(Integer id) {
        logMapper.deleteById(id);
    }

    /**
     * 根据id集合删除
     * @param ids
     */
    @Override
    public void deleteByIds(List<Integer> ids) {
        logMapper.deleteByIds(ids);
    }
}
