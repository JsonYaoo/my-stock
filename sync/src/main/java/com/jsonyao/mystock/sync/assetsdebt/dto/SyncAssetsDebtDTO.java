package com.jsonyao.mystock.sync.assetsdebt.dto;

import com.jsonyao.mystock.common.lixingren.ElevenImportBean;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.math.BigDecimal;

/**
 * 资产负债表 DTO
 *
 * @author yaocs2
 * @since 20220502
 */
@Data
@Slf4j
public class SyncAssetsDebtDTO {

    /**
     * 年度
     */
    private Integer year;

    /**
     * 季度
     */
    private Integer quarter;

    /**
     * TS股票代码
     */
    private String symbol;

    /**
     * 股票名称
     */
    private String stockName;

    /**
     * 行业
     */
    private String industry;

    /**
     * 资产合计(元)
     */
    private BigDecimal totalAssets;

    /**
     * 归属于母公司普通股股东权益合计(元)
     */
    private BigDecimal phTotalEquity;

    /**
     * 归属于母公司普通股股东的净利润(元)
     */
    private BigDecimal phNetProfit;

    /**
     * 营业总收入(元)
     */
    private BigDecimal totalOpIncome;

    /**
     * 营业收入(元)
     */
    private BigDecimal opIncome;

    /**
     * 销售费用(元)
     */
    private BigDecimal salesCosts;

    /**
     * 管理费用(元)
     */
    private BigDecimal managementCosts;

    /**
     * 财务费用(元)
     */
    private BigDecimal financialCosts;

    /**
     * 研发费用(元)
     */
    private BigDecimal rdCosts;

    /**
     * 存货(元)
     */
    private BigDecimal inventory;

    /**
     * 应付账款(元)
     */
    private BigDecimal accountsPayable;

    /**
     * 应收账款(元)
     */
    private BigDecimal accountsReceivable;

    /**
     * 预付款项(元)
     */
    private BigDecimal prepayments;

    /**
     * 预收账款(元)
     */
    private BigDecimal advancePayment;

    /**
     * 设置BigDecimal字段的值
     *
     * @param initYear
     * @param filedName
     * @param bean
     */
    public void setBigDecimalField(Integer initYear, String filedName, ElevenImportBean bean) {
        try {
            Field field = this.getClass().getDeclaredField(filedName);
            field.setAccessible(true);
            field.set(this, bean.getValueByYear(initYear));
        } catch (Exception e) {
            log.error("设置"+ filedName +"字段的值异常! e=", e);
        }
    }
}