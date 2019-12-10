package org.zongf.tools.common.exception;

import org.zongf.tools.common.constant.ExceptionCode;

/** 序列化异常
 * @since 1.0
 * @author zongf
 * @created 2019-07-24
 */
public class SerializableException extends BaseRuntimeException {

    public SerializableException() {
        super(ExceptionCode.SERIALIZABLE_EXCEPTION);
    }

    public SerializableException(String message) {
        super(ExceptionCode.SERIALIZABLE_EXCEPTION, message);
    }

    public SerializableException(String message, Throwable cause) {
        super(ExceptionCode.SERIALIZABLE_EXCEPTION, message, cause);
    }

    public SerializableException(Throwable cause) {
        super(ExceptionCode.SERIALIZABLE_EXCEPTION, cause);
    }

    public SerializableException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(ExceptionCode.SERIALIZABLE_EXCEPTION, message, cause, enableSuppression, writableStackTrace);
    }
}
