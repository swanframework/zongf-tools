package org.zongf.tools.common.utils;

import org.slf4j.Logger;
import org.zongf.tools.log.util.LogUtil;

/** 流关闭工具类
 * @author: zongf
 * @date: 2019-07-01
 */
public class CloseUtil {

    private static Logger log = LogUtil.getLogger(CloseUtil.class);

    /**手工关闭流
     * @param closeables 任意多个实现AutoCloseable接口的对象
     * @author zongf
     * @date 2019-07-01
     */
    public static void close(AutoCloseable... closeables) {
        for (AutoCloseable closeable: closeables) {
            try {
                if (closeable != null) {
                    closeable.close();
                }
            } catch (Exception e) {
                log.warn("流关闭异常, 异常原因:{}", e.getMessage());
            }
        }
    }

}
