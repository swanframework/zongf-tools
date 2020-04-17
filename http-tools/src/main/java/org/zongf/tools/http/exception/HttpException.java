package org.zongf.tools.http.exception;

import org.zongf.tools.common.exception.AbsBaseException;

/** Http异常
 * @author zongf
 * @date 2020-04-16
 */
public class HttpException extends AbsBaseException {

    public HttpException(String message, Object... params) {
        super(message, params);
    }

    public HttpException(Throwable cause, String message, Object... params) {
        super(cause, message, params);
    }

    public HttpException(int code, String message, Object... params) {
        super(code, message, params);
    }

    public HttpException(Throwable cause, int code, String message, String... params) {
        super(cause, code, message, params);
    }
}
