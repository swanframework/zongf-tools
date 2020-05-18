package org.zongf.tools.common.exception;

/** 锁异常
 * @author zongf
 * @date 2020-05-15
 */
public class LockException extends AbsBaseException {

    public LockException(String message, Object... params) {
        super(message, params);
    }

    public LockException(Throwable cause, String message, Object... params) {
        super(cause, message, params);
    }

    public LockException(int code, String message, Object... params) {
        super(code, message, params);
    }

    public LockException(Throwable cause, int code, String message, String... params) {
        super(cause, code, message, params);
    }
}
