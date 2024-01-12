package com.jumpfish.developer.porjects.monitors.common.result;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Result<T> {

    private Integer code;

    private String message;

    private T data;


    private Map<String, Object> ext;

    public Result(Integer code) {
        this.code = code;
    }

    public Result(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public boolean isSuccess(){
        if(this.code.equals(DefaultResultCode.SUCCESS.getCode())){
            return true;
        }else{
            return false;
        }
    }

}
