package com.bookManage.exception;

/**
 * @author :Wjh
 * @since :2023/12/27 19:57
 */

public class TokenExpireException extends BaseException{
    public TokenExpireException() {
    }

    public TokenExpireException(String msg) {
        super(msg);
    }
}
