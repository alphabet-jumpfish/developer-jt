package com.jumpfish.developer.porjects.monitors.common.result;

/**
 * @Describe 描述信息
 * @Author 王加闻
 * @Company 杭州来回科技
 * @Date 2021/6/10 2:13 下午
 **/
import java.io.Serializable;

public class MicroBizException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 1235196442382516631L;
    private Integer code;

    public MicroBizException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public MicroBizException(Throwable cause, Integer code, String message) {
        super(message, cause);
        this.code = code;
    }

    public Integer getCode() {
        return this.code;
    }
}
