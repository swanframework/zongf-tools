package org.zongf.tools.common.utils;


import java.io.PrintWriter;
import java.io.StringWriter;

/** 异常工具类
 * @author zongf
 * @date 2020-07-15
 */
public final class ExceptionUtil {

    /** 解析异常堆栈信息
     * @param ex
     * @return String
     * @author zongf
     * @date 2020-07-15
     */
    public static String parseStackTrace(Exception ex) {
        StringWriter sw = null;
        PrintWriter pw = null;

        try {
            sw = new StringWriter();
            pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            return sw.toString();
        } catch (Exception e) {
            // do Nothing
        } finally {
            CloseUtil.close(sw, pw);
        }
        return null;
    }

}
