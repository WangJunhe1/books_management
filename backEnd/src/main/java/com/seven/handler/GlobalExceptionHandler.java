package com.seven.handler;

import com.seven.domain.pojo.Result;
import com.seven.exception.BaseException;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.logging.ErrorManager;

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
}
