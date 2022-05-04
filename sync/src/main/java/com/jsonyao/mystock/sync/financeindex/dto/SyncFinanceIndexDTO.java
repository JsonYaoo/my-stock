package com.jsonyao.mystock.sync.financeindex.dto;

import com.jsonyao.mystock.common.lixingren.ElevenImportBean;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 财务指标表 DTO
 *
 * @author yaocs2
 * @since 20220502
 */
@Data
@Slf4j
public class SyncFinanceIndexDTO {

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
     * 总股本
     */
    private BigDecimal totalShareCapital;

    /**
     * 分红率
     */
    private BigDecimal dividendRatio;

    /**
     * 归属于母公司普通股股东加权ROE
     */
    private BigDecimal phWeightedRoe;

    /**
     * 净利润率
     */
    private BigDecimal netProfitRatio;

    /**
     * 毛利率GM
     */
    private BigDecimal grossMarginGm;

    /**
     * 杠杆倍数
     */
    private BigDecimal leverage;

    /**
     * 资产负债率
     */
    private BigDecimal assetsLiaRatio;

    /**
     * 有息负债率
     */
    private BigDecimal ibDebtRatio;

    /**
     * 流动比率
     */
    private BigDecimal currentRatio;

    /**
     * 速动比率
     */
    private BigDecimal quickRatio;

    /**
     * 销售费用率
     */
    private BigDecimal salesCostsRatio;

    /**
     * 管理费用率
     */
    private BigDecimal magCostsRatio;

    /**
     * 财务费用率
     */
    private BigDecimal finCostsRatio;

    /**
     * 研发费用率
     */
    private BigDecimal rdCostsRatio;

    /**
     * 资产周转率
     */
    private BigDecimal assetTurnoverRatio;

    /**
     * 存货周转天数(天)
     */
    private BigDecimal inventoryTd;

    /**
     * 应付账款周转天数(天)
     */
    private BigDecimal accountsPayableTd;

    /**
     * 应收账款周转天数(天)
     */
    private BigDecimal accountsReceivableTd;

    /**
     * 预付账款周转天数(天)
     */
    private BigDecimal prepaymentsTd;

    /**
     * 预收账款周转天数(天)
     */
    private BigDecimal advancePaymentTd;

    /**
     * 创建时间
     */
    private Date creationDate;

    /**
     * 最后更新时间
     */
    private Date lastUpdateDate;

    /**
     * 是否删除 0不删除 1删除
     */
    private Byte deleteFlag;

    /**
     * 版本号
     */
    private Long version;

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