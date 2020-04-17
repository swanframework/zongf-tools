package org.zongf.tools.common.exception;

/** 类型转换异常
 * @author zongf
 * @date 2019-07-01
 */
public class TypeConversionException extends AbsBaseException {

    public TypeConversionException(String message, Object... params) {
        super(message, params);
    }

    public TypeConversionException(Throwable cause, String message, Object... params) {
        super(cause, message, params);
    }

    public TypeConversionException(int code, String message, Object... params) {
        super(code, message, params);
    }

    public TypeConversionException(Throwable cause, int code, String message, String... params) {
        super(cause, code, message, params);
    }
}
