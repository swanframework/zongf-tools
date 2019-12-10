package org.zongf.tools.common.exception;

import org.zongf.tools.common.constant.ExceptionCode;

/** 断言异常
 * @author zongf
 * @date 2019-07-01
 */
public class AssertException extends BaseRuntimeException {

    public AssertException() {
        super(ExceptionCode.ASSERT_EXCEPTION);
    }

    public AssertException(String message) {
        super(ExceptionCode.ASSERT_EXCEPTION, message);
    }

    public AssertException(String message, Throwable cause) {
        super(ExceptionCode.ASSERT_EXCEPTION, message, cause);
    }

    public AssertException(Throwable cause) {
        super(ExceptionCode.ASSERT_EXCEPTION, cause);
    }

    public AssertException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(ExceptionCode.ASSERT_EXCEPTION, message, cause, enableSuppression, writableStackTrace);
    }
}
