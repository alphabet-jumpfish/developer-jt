package com.jumpfish.developer.porjects.monitors.common.result;


import java.util.Map;

public class ResultFactory {

    public static Result of(Integer code,String message) {
        return new Result(code, message);
    }

    public static Result of(ResultCode resultCode) {
        return new Result(resultCode.getCode(), resultCode.getMessage());
    }

    public static Result of(ResultCode resultCode, Object data) {
        return new Result(resultCode.getCode(), resultCode.getMessage(), data);
    }

    public static Result of(ResultCode resultCode, Object data, Map<String, Object> ext) {
        return new Result(resultCode.getCode(), resultCode.getMessage(), data, ext);
    }

    public static Result successOf() {
        return of(DefaultResultCode.SUCCESS);
    }

    public static Result invalidParamOf() {
        return of(DefaultResultCode.INVALID_PARAMETER);
    }

    public static Result invalidParamOf(String data) {
        return of(DefaultResultCode.INVALID_PARAMETER, data);
    }

    public static Result invalidUserOf() {
        return of(DefaultResultCode.INVALID_USER);
    }

    public static Result invalidTokenAuthorizationOf() {
        return of(DefaultResultCode.TOKEN_AUTHORIZATION_FAILED);
    }

    public static Result invalidTokenAuthorizationOf(String data) {
        return of(DefaultResultCode.TOKEN_AUTHORIZATION_FAILED, data);
    }

    public static Result successOf(Object data) {
        return of(DefaultResultCode.SUCCESS, data);
    }

    public static Result errorOf() {
        return of(DefaultResultCode.INTERNAL_SERVER_ERROR);
    }

    public static Result errorOf(String message) {
        return of(DefaultResultCode.INTERNAL_SERVER_ERROR.getCode(), message);
    }
    public static Result businessErrorOf(String message) {
        return of(DefaultResultCode.BUSINESS_EXCEPTION.getCode(), message);
    }
}