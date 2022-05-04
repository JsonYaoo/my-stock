package com.jsonyao.mystock.sync.financeindex.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 财务指标表
 *
 * @author yaocs2
 * @since 20220502
 */
@Data
@Table(name = "sync_finance_index")
public class SyncFinanceIndex {

    /**
     * 主键ID
     */
    @Id
    @Column(name = "INDEX_ID")
    private Long indexId;

    /**
     * 年度
     */
    @Column(name = "YEAR")
    private Integer year;

    /**
     * 季度
     */
    @Column(name = "QUARTER")
    private Integer quarter;

    /**
     * TS股票代码
     */
    @Column(name = "TS_CODE")
    private String tsCode;

    /**
     * 股票名称
     */
    @Column(name = "STOCK_NAME")
    private String stockName;

    /**
     * 行业
     */
    @Column(name = "INDUSTRY")
    private String industry;

    /**
     * 总股本
     */
    @Column(name = "TOTAL_SHARE_CAPITAL")
    private BigDecimal totalShareCapital;

    /**
     * 分红率
     */
    @Column(name = "DIVIDEND_RATIO")
    private BigDecimal dividendRatio;

    /**
     * 归属于母公司普通股股东加权ROE
     */
    @Column(name = "PH_WEIGHTED_ROE")
    private BigDecimal phWeightedRoe;

    /**
     * 净利润率
     */
    @Column(name = "NET_PROFIT_RATIO")
    private BigDecimal netProfitRatio;

    /**
     * 毛利率GM
     */
    @Column(name = "GROSS_MARGIN_GM")
    private BigDecimal grossMarginGm;

    /**
     * 杠杆倍数
     */
    @Column(name = "LEVERAGE")
    private BigDecimal leverage;

    /**
     * 资产负债率
     */
    @Column(name = "ASSETS_LIA_RATIO")
    private BigDecimal assetsLiaRatio;

    /**
     * 有息负债率
     */
    @Column(name = "IB_DEBT_RATIO")
    private BigDecimal ibDebtRatio;

    /**
     * 流动比率
     */
    @Column(name = "CURRENT_RATIO")
    private BigDecimal currentRatio;

    /**
     * 速动比率
     */
    @Column(name = "QUICK_RATIO")
    private BigDecimal quickRatio;

    /**
     * 销售费用率
     */
    @Column(name = "SALES_COSTS_RATIO")
    private BigDecimal salesCostsRatio;

    /**
     * 管理费用率
     */
    @Column(name = "MAG_COSTS_RATIO")
    private BigDecimal magCostsRatio;

    /**
     * 财务费用率
     */
    @Column(name = "FIN_COSTS_RATIO")
    private BigDecimal finCostsRatio;

    /**
     * 研发费用率
     */
    @Column(name = "RD_COSTS_RATIO")
    private BigDecimal rdCostsRatio;

    /**
     * 资产周转率
     */
    @Column(name = "ASSET_TURNOVER_RATIO")
    private BigDecimal assetTurnoverRatio;

    /**
     * 存货周转天数(天)
     */
    @Column(name = "INVENTORY_TD")
    private BigDecimal inventoryTd;

    /**
     * 应付账款周转天数(天)
     */
    @Column(name = "ACCOUNTS_PAYABLE_TD")
    private BigDecimal accountsPayableTd;

    /**
     * 应收账款周转天数(天)
     */
    @Column(name = "ACCOUNTS_RECEIVABLE_TD")
    private BigDecimal accountsReceivableTd;

    /**
     * 预付账款周转天数(天)
     */
    @Column(name = "PREPAYMENTS_TD")
    private BigDecimal prepaymentsTd;

    /**
     * 预收账款周转天数(天)
     */
    @Column(name = "ADVANCE_PAYMENT_TD")
    private BigDecimal advancePaymentTd;

    /**
     * 创建时间
     */
    @Column(name = "CREATION_DATE")
    private Date creationDate;

    /**
     * 最后更新时间
     */
    @Column(name = "LAST_UPDATE_DATE")
    private Date lastUpdateDate;

    /**
     * 是否删除 0不删除 1删除
     */
    @Column(name = "DELETE_FLAG")
    private Byte deleteFlag;

    /**
     * 版本号
     */
    @Column(name = "VERSION")
    private Long version;
}