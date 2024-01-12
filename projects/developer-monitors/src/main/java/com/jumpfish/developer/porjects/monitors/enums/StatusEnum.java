package com.jumpfish.developer.porjects.monitors.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StatusEnum {

    VALID(1, "有效"),
    NOT_VALID(2, "无效");

    private Integer value;
    private String name;

}
