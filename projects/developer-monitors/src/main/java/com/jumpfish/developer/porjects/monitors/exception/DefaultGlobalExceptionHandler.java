package com.jumpfish.developer.porjects.monitors.exception;

import com.jumpfish.developer.porjects.monitors.common.result.*;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ConditionalOnWebApplication
@RestControllerAdvice
public class DefaultGlobalExceptionHandler {

    @ExceptionHandler({FeignException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result feignException(HttpServletRequest request, Exception e) {
        return ResultFactory.of(new ResultCode() {
            @Override
            public Integer getCode() {
                return DefaultResultCode.FEIGN_REMOTE_CALL_FAILED.getCode();
            }

            @Override
            public String getMessage() {
                return e.getMessage();
            }
        });
    }

    @ExceptionHandler({MicroBizException.class})
    @ResponseStatus(HttpStatus.OK)
    public Result microBizException(HttpServletRequest request, MicroBizException e) {

        return ResultFactory.of(new ResultCode() {
            @Override
            public Integer getCode() {
                return e.getCode();
            }

            @Override
            public String getMessage() {
                return e.getMessage();
            }
        });
    }
}
