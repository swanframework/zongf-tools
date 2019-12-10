package org.zongf.tools.common.exception;

import org.zongf.tools.common.constant.ExceptionCode;

/** 类型转换异常
 * @author zongf
 * @date 2019-07-01
 */
public class TypeConversionException extends BaseRuntimeException {

    public TypeConversionException() {
        super(ExceptionCode.TYPE_CONVERSION_EXCEPTION);
    }

    public TypeConversionException(String message) {
        super(ExceptionCode.TYPE_CONVERSION_EXCEPTION, message);
    }

    public TypeConversionException(String message, Throwable cause) {
        super(ExceptionCode.TYPE_CONVERSION_EXCEPTION, message, cause);
    }

    public TypeConversionException(Throwable cause) {
        super(ExceptionCode.TYPE_CONVERSION_EXCEPTION, cause);
    }

    public TypeConversionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(ExceptionCode.TYPE_CONVERSION_EXCEPTION, message, cause, enableSuppression, writableStackTrace);
    }
}
