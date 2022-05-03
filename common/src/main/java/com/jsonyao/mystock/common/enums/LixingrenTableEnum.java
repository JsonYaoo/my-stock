package com.jsonyao.mystock.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 理性人数据表 枚举类
 *
 * @author yaocs2
 * @since 20220502
 */
@AllArgsConstructor
@Getter
public enum LixingrenTableEnum {

    SYNC_ASSETS_DEBT("sync_assets_debt", "资产负债表"),
    SYNC_CASH_FLOW("sync_cash_flow", "现金流量表"),
    SYNC_FINANCE_INDEX("sync_finance_index", "财务指标表"),

    ;

    private String value;
    private String name;
}
