package com.jumpfish.developer.porjects.monitors.common.result;



/**
 * description 返回编码
 * @author 吴佳伟
 * @date 2021/5/27 5:57 下午
 */
public interface ResultCode {

    /**
     * 错误编码
     *
     * @return
     */
    Integer getCode();

    /**
     * 错误信息
     *
     * @return
     */
    String getMessage();

    default MicroBizException exception() {
        return new MicroBizException(this.getCode(), this.getMessage());
    }

}
