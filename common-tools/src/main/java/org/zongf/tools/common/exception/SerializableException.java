package org.zongf.tools.common.exception;

/** 序列化异常
 * @since 1.0
 * @author zongf
 * @created 2019-07-24
 */
public class SerializableException extends AbsBaseException {

    public SerializableException(String message, Object... params) {
        super(message, params);
    }

    public SerializableException(Throwable cause, String message, Object... params) {
        super(cause, message, params);
    }

    public SerializableException(int code, String message, Object... params) {
        super(code, message, params);
    }

    public SerializableException(Throwable cause, int code, String message, String... params) {
        super(cause, code, message, params);
    }
}
