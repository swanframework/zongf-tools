package org.zongf.tools.common.exception;

/** 异常基类
 * @author zongf
 * @date 2019-12-10
 */
abstract public class AbsBaseException extends RuntimeException {

    // 异常码
    private int code;

    public AbsBaseException(String message, Object... params) {
        super(String.format(message, params));
    }

    public AbsBaseException(Throwable cause, String message, Object... params) {
        super(String.format(message, params), cause);
    }

    public AbsBaseException(int code, String message, Object... params) {
        super(String.format(message, params));
        this.code = code;
    }

    public AbsBaseException(Throwable cause, int code, String message, String... params) {
        super(String.format(message, params), cause);
        this.code = code;
    }
}
