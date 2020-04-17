package org.zongf.tools.log.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zongf
 * @date 2020-04-16
 */
public class LogUtil {

    /** 获取日志对象
     * @param clz
     * @return Logger
     * @author zongf
     * @date 2020-04-16
     */
    public static Logger getLogger(Class<?> clz){
        return LoggerFactory.getLogger(clz);
    }

}
