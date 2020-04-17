package org.zongf.tools.common.exception;


/** URL 编码异常
 * @author zongf
 * @date 2019-12-04
 */
public class URLCoderException extends AbsBaseException {

    public URLCoderException(String message, Object... params) {
        super(message, params);
    }

    public URLCoderException(Throwable cause, String message, Object... params) {
        super(cause, message, params);
    }

    public URLCoderException(int code, String message, Object... params) {
        super(code, message, params);
    }

    public URLCoderException(Throwable cause, int code, String message, String... params) {
        super(cause, code, message, params);
    }
}
