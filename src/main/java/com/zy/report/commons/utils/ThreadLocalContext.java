package com.zy.report.commons.utils;

import com.zy.report.modules.log.entity.Log;
import lombok.Data;

/**
 * Description: 本地线程变量
 * @author Nile
 */
@Data
public class ThreadLocalContext {
    /**
     * 日志实体
     */
    private Log logger = new Log();

    /**
     * 是否记录日志
     */
    private boolean isLog = false;

    /**
     * 线程本地内存中的变量
     */
    private static ThreadLocal<ThreadLocalContext> threadLocal = new ThreadLocal<>();

    public static ThreadLocalContext get() {
        if (threadLocal.get() == null) {
            ThreadLocalContext threadLocalContext = new ThreadLocalContext();
            threadLocal.set(threadLocalContext);
        }
        return threadLocal.get();
    }

    public void remove() {
        this.logger = null;
        this.isLog = false;
        threadLocal.remove();
    }
}
