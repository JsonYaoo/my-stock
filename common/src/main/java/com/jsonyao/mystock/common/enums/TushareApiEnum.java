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

    STOCK_BASIC("stock_basic", "沪深股票_基础数据_股票列表_120", 120),
    STOCK_COMPANY("stock_company", "沪深股票_基础数据_上市公司基本信息_120", 120),
    BAK_BASIC("bak_basic", "沪深股票_基础数据_备用列表_5000", 120),
    TRADE_CAL("trade_cal", "沪深股票_基础数据_交易日历_免费", 0),

    ;

    private String value;
    private String name;
    private Integer score;
}
