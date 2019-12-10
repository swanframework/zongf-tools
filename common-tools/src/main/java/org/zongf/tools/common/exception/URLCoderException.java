package org.zongf.tools.common.exception;

import org.zongf.tools.common.constant.ExceptionCode;

/** URL 编码异常
 * @author zongf
 * @date 2019-12-04
 */
public class URLCoderException extends BaseRuntimeException{

    public URLCoderException() {
        super(ExceptionCode.URL_CODER_EXCEPTION);
    }

    public URLCoderException(String message) {
        super(ExceptionCode.URL_CODER_EXCEPTION, message);
    }

    public URLCoderException(String message, Throwable cause) {
        super(ExceptionCode.URL_CODER_EXCEPTION, message, cause);
    }

    public URLCoderException(Throwable cause) {
        super(ExceptionCode.URL_CODER_EXCEPTION, cause);
    }

    public URLCoderException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(ExceptionCode.URL_CODER_EXCEPTION, message, cause, enableSuppression, writableStackTrace);
    }
}
