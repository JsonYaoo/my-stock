package com.jsonyao.mystock.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * TuShare大数据平台API 枚举类
 *
 * @author yaocs2
 * @since 20220502
 */
@AllArgsConstructor
@Getter
public enum TushareApiEnum {

    STOCK_BASIC("stock_basic", "沪深股票_基础数据_股票列表_120", 120)

    ;

    private String value;
    private String name;
    private Integer score;
}
