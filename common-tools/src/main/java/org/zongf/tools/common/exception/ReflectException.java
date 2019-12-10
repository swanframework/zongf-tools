package org.zongf.tools.common.exception;

import org.zongf.tools.common.constant.ExceptionCode;

/** 反射异常
 * @since 1.0
 * @author zongf
 * @created 2019-07-01
 */
public class ReflectException extends BaseRuntimeException {

    public ReflectException() {
        super(ExceptionCode.REFLECT_EXCEPTION);
    }

    public ReflectException(String message) {
        super(ExceptionCode.REFLECT_EXCEPTION, message);
    }

    public ReflectException(String message, Throwable cause) {
        super(ExceptionCode.REFLECT_EXCEPTION, message, cause);
    }

    public ReflectException(Throwable cause) {
        super(ExceptionCode.REFLECT_EXCEPTION, cause);
    }

    public ReflectException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(ExceptionCode.REFLECT_EXCEPTION, message, cause, enableSuppression, writableStackTrace);
    }
}
