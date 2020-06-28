package org.zongf.tools.spring.excetion;

import org.zongf.tools.common.exception.AbsBaseException;

/** 事务异常
 * @author zongf
 * @date 2020-06-28
 */
public class TxException extends AbsBaseException {

    public TxException(String message, Object... params) {
        super(message, params);
    }

    public TxException(Throwable cause, String message, Object... params) {
        super(cause, message, params);
    }

    public TxException(int code, String message, Object... params) {
        super(code, message, params);
    }

    public TxException(Throwable cause, int code, String message, String... params) {
        super(cause, code, message, params);
    }
}
