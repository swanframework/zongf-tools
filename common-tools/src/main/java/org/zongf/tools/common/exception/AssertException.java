package org.zongf.tools.common.exception;

/** 断言异常
 * @author zongf
 * @date 2019-07-01
 */
public class AssertException extends AbsBaseException {

    public AssertException(String message, Object... params) {
        super(message, params);
    }

    public AssertException(Throwable cause, String message, Object... params) {
        super(cause, message, params);
    }

    public AssertException(int code, String message, Object... params) {
        super(code, message, params);
    }

    public AssertException(Throwable cause, int code, String message, String... params) {
        super(cause, code, message, params);
    }
}
