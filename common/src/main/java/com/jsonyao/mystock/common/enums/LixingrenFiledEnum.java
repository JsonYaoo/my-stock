package com.jsonyao.mystock.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 理性人字段 枚举类
 *
 * @author yaocs2
 * @since 20220502
 */
@AllArgsConstructor
@Getter
public enum LixingrenFiledEnum {

    TOTAL_ASSETS("total_assets", "资产合计", LixingrenTableEnum.SYNC_ASSETS_DEBT.getValue(), "totalAssets"),
    PCOMPANY_HOLDERS_TOTAL_EQUITY("pcompany_holders_total_equity", "归属于母公司普通股股东权益合计", LixingrenTableEnum.SYNC_ASSETS_DEBT.getValue(), "phTotalEquity"),
    PCOMPANY_HOLDERS_NET_PROFIT("pcompany_holders_net_profit", "归属于母公司普通股股东的净利润", LixingrenTableEnum.SYNC_ASSETS_DEBT.getValue(), "phNetProfit"),
    TOTAL_OPERATING_INCOME("total_operating_income", "营业总收入", LixingrenTableEnum.SYNC_ASSETS_DEBT.getValue(), "totalOpIncome"),
    OPERATING_INCOME("operating_income", "营业收入", LixingrenTableEnum.SYNC_ASSETS_DEBT.getValue(), "opIncome"),
    SALES_COSTS("sales_costs", "销售费用", LixingrenTableEnum.SYNC_ASSETS_DEBT.getValue(), "salesCosts"),
    MANAGEMENT_COSTS("management_costs", "管理费用", LixingrenTableEnum.SYNC_ASSETS_DEBT.getValue(), "managementCosts"),
    FINANCIAL_COSTS("financial_costs", "财务费用", LixingrenTableEnum.SYNC_ASSETS_DEBT.getValue(), "financialCosts"),
    RD_COSTS("rd_costs", "研发费用", LixingrenTableEnum.SYNC_ASSETS_DEBT.getValue(), "rdCosts"),
    INVENTORY("inventory", "存货", LixingrenTableEnum.SYNC_ASSETS_DEBT.getValue(), "inventory"),
    ACCOUNTS_PAYABLE("accounts_payable", "应付账款", LixingrenTableEnum.SYNC_ASSETS_DEBT.getValue(), "accountsPayable"),
    ACCOUNTS_RECEIVABLE("accounts_receivable", "应收账款", LixingrenTableEnum.SYNC_ASSETS_DEBT.getValue(), "accountsReceivable"),
    PREPAYMENTS("prepayments", "预付款项", LixingrenTableEnum.SYNC_ASSETS_DEBT.getValue(), "prepayments"),
    ADVANCE_PAYMENT("advance_payment", "预收账款", LixingrenTableEnum.SYNC_ASSETS_DEBT.getValue(), "advancePayment"),

    FIXED_INTANGIBLE_LONGTERM_ASSETS_COSTS("fixed_intangible_longterm_assets_costs", "购建固定资产、无形资产及其他长期资产所支付的现金", LixingrenTableEnum.SYNC_CASH_FLOW.getValue(), ""),
    CASH_FLOW_FROM_SALES("cash_flow_from_sales", "销售商品提供劳务收到的现金", LixingrenTableEnum.SYNC_CASH_FLOW.getValue(), ""),
    FREE_CASH_FLOW("free_cash_flow", "自由现金流量", LixingrenTableEnum.SYNC_CASH_FLOW.getValue(), ""),
    NET_CASH_FLOW_FROM_BUSINESS("net_cash_flow_from_business", "经营活动产生的现金流量净额", LixingrenTableEnum.SYNC_CASH_FLOW.getValue(), ""),
    NET_CASH_FLOW_FROM_INVESTMENT("net_cash_flow_from_investment", "投资活动产生的现金流量净额", LixingrenTableEnum.SYNC_CASH_FLOW.getValue(), ""),
    NET_CASH_FLOW_FROM_FINANCING("net_cash_flow_from_financing", "筹资活动产生的现金流量净额", LixingrenTableEnum.SYNC_CASH_FLOW.getValue(), ""),

    TOTAL_SHARE_CAPITAL("total_share_capital", "总股本", LixingrenTableEnum.SYNC_FINANCE_INDEX.getValue(), ""),
    DIVIDEND_RATIO("dividend_ratio", "分红率", LixingrenTableEnum.SYNC_FINANCE_INDEX.getValue(), ""),
    PCOMPANY_HOLDERS_WEIGHTED_ROE("pcompany_holders_weighted_roe", "归属于母公司普通股股东加权ROE", LixingrenTableEnum.SYNC_FINANCE_INDEX.getValue(), ""),
    NET_PROFIT_RATIO("net_profit_ratio", "净利润率", LixingrenTableEnum.SYNC_FINANCE_INDEX.getValue(), ""),
    GROSS_MARGIN_GM("gross_Margin_gm", "毛利率GM", LixingrenTableEnum.SYNC_FINANCE_INDEX.getValue(), ""),
    LEVERAGE("leverage", "杠杆倍数", LixingrenTableEnum.SYNC_FINANCE_INDEX.getValue(), ""),
    ASSETS_LIABILITIES_RATIO("assets_liabilities_ratio", "资产负债率", LixingrenTableEnum.SYNC_FINANCE_INDEX.getValue(), ""),
    INTEREST_BEARING_DEBT_RATIO("interest_bearing_debt_ratio", "有息负债率", LixingrenTableEnum.SYNC_FINANCE_INDEX.getValue(), ""),
    CURRENT_RATIO("current_ratio", "流动比率", LixingrenTableEnum.SYNC_FINANCE_INDEX.getValue(), ""),
    QUICK_RATIO("quick_ratio", "速动比率", LixingrenTableEnum.SYNC_FINANCE_INDEX.getValue(), ""),
    SALES_COSTS_RATIO("sales_costs_ratio", "销售费用率", LixingrenTableEnum.SYNC_FINANCE_INDEX.getValue(), ""),
    MANAGEMENT_COSTS_RATIO("management_costs_ratio", "管理费用率", LixingrenTableEnum.SYNC_FINANCE_INDEX.getValue(), ""),
    FINANCIAL_COSTS_RATIO("financial_costs_ratio", "财务费用率", LixingrenTableEnum.SYNC_FINANCE_INDEX.getValue(), ""),
    RD_COSTS_RATIO("rd_costs_ratio", "研发费用率", LixingrenTableEnum.SYNC_FINANCE_INDEX.getValue(), ""),
    ASSET_TURNOVER_RATIO("asset_turnover_ratio", "资产周转率", LixingrenTableEnum.SYNC_FINANCE_INDEX.getValue(), ""),
    INVENTORY_TURNOVER_DAYS("inventory_turnover_days", "存货周转天数", LixingrenTableEnum.SYNC_FINANCE_INDEX.getValue(), ""),
    ACCOUNTS_PAYABLE_TURNOVER_DAYS("accounts_payable_turnover_days", "应付账款周转天数", LixingrenTableEnum.SYNC_FINANCE_INDEX.getValue(), ""),
    ACCOUNTS_RECEIVABLE_TURNOVER_DAYS("accounts_receivable_turnover_days", "应收账款周转天数", LixingrenTableEnum.SYNC_FINANCE_INDEX.getValue(), ""),
    PREPAYMENTS_TURNOVER_DAYS("prepayments_turnover_days", "预付账款周转天数", LixingrenTableEnum.SYNC_FINANCE_INDEX.getValue(), ""),
    ADVANCE_PAYMENT_TURNOVER_DAYS("advance_payment_turnover_days", "预收账款周转天数", LixingrenTableEnum.SYNC_FINANCE_INDEX.getValue(), ""),;

    private String filed;
    private String desc;
    private String table;
    private String value;

    /**
     * 通过表名, 获取该表下所有的枚举类
     *
     * @param tableName
     * @return
     */
    public static List<LixingrenFiledEnum> getEnumsByTable(String tableName) {
        if(StringUtils.isBlank(tableName)) {
            return null;
        }

        List<LixingrenFiledEnum> filedEnums = new ArrayList<>();
        for (LixingrenFiledEnum filedEnum : LixingrenFiledEnum.values()) {
            if(filedEnum.getTable().equals(tableName)) {
                filedEnums.add(filedEnum);
            }
        }

        return filedEnums;
    }

    /**
     * 通过字段描述, 获取对应的枚举类
     *
     * @param filedDesc
     * @return
     */
    public static LixingrenFiledEnum getEnumsByDesc(String filedDesc) {
        if(StringUtils.isBlank(filedDesc)) {
            return null;
        }

        for (LixingrenFiledEnum filedEnum : LixingrenFiledEnum.values()) {
            if(filedEnum.getDesc().equals(filedDesc)) {
                return filedEnum;
            }
        }

        return null;
    }
}
