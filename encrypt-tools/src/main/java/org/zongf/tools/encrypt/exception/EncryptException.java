package org.zongf.tools.encrypt.exception;

import org.zongf.tools.common.exception.AbsBaseException;

/** 加密异常
 * @author zongf
 * @date 2020-05-18
 */
public class EncryptException extends AbsBaseException {

    public EncryptException(String message, Object... params) {
        super(message, params);
    }

    public EncryptException(Throwable cause, String message, Object... params) {
        super(cause, message, params);
    }

    public EncryptException(int code, String message, Object... params) {
        super(code, message, params);
    }

    public EncryptException(Throwable cause, int code, String message, String... params) {
        super(cause, code, message, params);
    }

}
