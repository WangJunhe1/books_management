package com.bookManage.exception;

/**
 * @author :Wjh
 * @since :2023/12/27 19:52
 */

public class TokenNotExitException extends BaseException{
    public TokenNotExitException() {
    }

    public TokenNotExitException(String msg) {
        super(msg);
    }
}
