package com.jumpfish.developer.porjects.monitors.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 *
 * 删除标识枚举
 * @date 2021/8/4 16:08
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum IsDeleteEnum {

    NOT_DELETE("1","未删除"),
    DELETED("2","已删除");

    private String value;

    private String name;


}
