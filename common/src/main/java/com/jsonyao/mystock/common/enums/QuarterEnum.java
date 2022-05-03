package com.jsonyao.mystock.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 季度 枚举类
 *
 * @author yaocs2
 * @since 20220502
 */
@AllArgsConstructor
@Getter
public enum QuarterEnum {

    ONE(1, "一季度", "一"),
    TWO(2, "二季度", "二"),
    THREE(3, "一季度", "三"),
    FOUR(4, "一季度", "四"),

    ;

    private Integer value;
    private String name;
    private String shortName;
}
