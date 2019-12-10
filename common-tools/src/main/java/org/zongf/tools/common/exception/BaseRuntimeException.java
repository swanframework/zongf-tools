package org.zongf.tools.common.exception;

/** 异常基类
 * @author zongf
 * @date 2019-12-10
 */
public class BaseRuntimeException extends RuntimeException {

    private Integer code;

    public BaseRuntimeException(Integer code) {
        this.code = code;
    }

    public BaseRuntimeException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public BaseRuntimeException( Integer code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public BaseRuntimeException(Integer code, Throwable cause) {
        super(cause);
        this.code = code;
    }

    public BaseRuntimeException(Integer code, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
    }
}
