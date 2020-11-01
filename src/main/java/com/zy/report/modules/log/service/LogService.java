package com.zy.report.modules.log.service;

import com.zy.report.commons.utils.Page;
import com.zy.report.modules.log.entity.Log;

import java.util.List;

public interface LogService {

    /**
     * 保存
     * @param logger
     */
    void save(Log logger);

    /**
     * 分页查询
     * @param page
     * @return
     */
    Page<Log> getByPage(Page<Log> page);

    /**
     * 根据id删除
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 批量删除[根据id集合删除]
     * @param ids
     */
    void deleteByIds(List<Integer> ids);

}
