package com.jsonyao.mystock.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 是否交易 枚举类
 *
 * @author yaocs2
 * @since 20220502
 */
@AllArgsConstructor
@Getter
public enum IsOpenEnum {

    NO("0", "休市"),
    YES("1", "交易"),

    ;

    private String value;
    private String name;
}
