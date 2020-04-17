package org.zongf.tools.common.exception;

/** 反射异常
 * @since 1.0
 * @author zongf
 * @created 2019-07-01
 */
public class ReflectException extends AbsBaseException {

    public ReflectException(String message, Object... params) {
        super(message, params);
    }

    public ReflectException(Throwable cause, String message, Object... params) {
        super(cause, message, params);
    }

    public ReflectException(int code, String message, Object... params) {
        super(code, message, params);
    }

    public ReflectException(Throwable cause, int code, String message, String... params) {
        super(cause, code, message, params);
    }
}
