package com.jsonyao.mystock.sync.cashflow.dto;

import com.jsonyao.mystock.common.lixingren.ElevenImportBean;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.math.BigDecimal;

/**
 * 现金流量表 DTO
 *
 * @author yaocs2
 * @since 20220502
 */
@Data
@Slf4j
public class SyncCashFlowDTO {
    
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
     * 购建固定资产、无形资产及其他长期资产所支付的现金(元)
     */
    private BigDecimal filAssetsCosts;

    /**
     * 销售商品提供劳务收到的现金(元)
     */
    private BigDecimal cfFromSales;

    /**
     * 自由现金流量(元)
     */
    private BigDecimal freeCashFlow;

    /**
     * 经营活动产生的现金流量净额(元)
     */
    private BigDecimal ncfFromBusiness;

    /**
     * 投资活动产生的现金流量净额(元)
     */
    private BigDecimal ncfFromInvestment;

    /**
     * 筹资活动产生的现金流量净额(元)
     */
    private BigDecimal ncfFromFinancing;

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