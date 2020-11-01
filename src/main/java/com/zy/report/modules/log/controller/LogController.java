package com.zy.report.modules.log.controller;


import com.zy.report.commons.enums.ResultEnum;
import com.zy.report.commons.utils.Page;
import com.zy.report.commons.utils.Result;
import com.zy.report.commons.utils.StringUtils;
import com.zy.report.modules.log.entity.Log;
import com.zy.report.modules.log.service.LogService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @author Nile
 */
@RestController
@RequestMapping("/log")
public class LogController {

    @Resource
    private LogService logService;

    /**
     * 分页查询 + 排序
     * @param page
     * @return
     */
    @PostMapping("/getByPage")
    public Result<Page<Log>> getByPage(@RequestBody Page<Log> page){
        // 获取排序方式  page对象中 封装了 sortColumn 排序列
        String sortColumn = page.getSortColumn();
        // 驼峰转下划线
        String newSortColumn = StringUtils.upperCharToUnderLine(sortColumn);
        // 下划线的 排序列
        page.setSortColumn(newSortColumn);
        // 判断排序列不为空
        if(StringUtils.isNotBlank(sortColumn)){
            // 根据 日志请求路径，日志状态，请求方式，响应时间，请求时间 排序
            String[] sortColumns = {"log_url", "log_status", "log_method", "log_time", "created_time"};
            // Arrays.asList() 方法使用
            // 1. 该方法是将数组转换成list。 Json 数据格式中的 排序列为数组形式，此处需要转换成 List数据形式
            // 2. 该方法不适用于剧本数据类型（byte,short,int,long,float,double,boolean）
            // 3. 不支持add和remove方法
            List<String> sortList = Arrays.asList(sortColumns);
            if(!sortList.contains(newSortColumn.toLowerCase())) {
                return new Result<>(ResultEnum.PARAMS_ERROR);
            }
        }
        page = logService.getByPage(page);
        return new Result<>(page);
    }

    /**
     * 根据id删除
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public Result<Object> delete(@PathVariable("id") Integer id){
        logService.deleteById(id);
        return new Result<>(ResultEnum.DELETE_SUCCESS);
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @PutMapping("/deleteByIds")
    public Result<Object> deleteByIds(@RequestBody List<Integer> ids) {
        logService.deleteByIds(ids);
        return new Result<>(ResultEnum.DELETE_SUCCESS);
    }

}
