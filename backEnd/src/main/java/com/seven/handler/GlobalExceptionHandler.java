package com.seven.handler;

import com.seven.constant.MessageConstant;
import com.seven.domain.pojo.Result;
import com.seven.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * @author :Wjh
 * @since :2023/11/28 13:44
 */

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 捕获业务异常
     * @param e
     * @return
     */
    @ExceptionHandler
    public Result baseExceptionHandler(BaseException e) {
        log.error(e.getMessage());
        return Result.error(e.getMessage());
    }

    @ExceptionHandler
    public Result baseExceptionHandler(SQLIntegrityConstraintViolationException e) {
        log.error(e.getMessage());
        String message = e.getMessage();
        if(message.contains("Duplicate entry")){
            String[] messageArr = message.split(" ");
            String username = messageArr[2];
            String msg = username + MessageConstant.ALREADY_EXISTS;
            return Result.error(msg);
        }
        return Result.error(MessageConstant.UNKNOWN_ERROR);
    }
}
