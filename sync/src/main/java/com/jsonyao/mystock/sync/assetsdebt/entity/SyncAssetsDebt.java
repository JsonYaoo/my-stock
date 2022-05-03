package com.jsonyao.mystock.sync.assetsdebt.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 资产负债表
 *
 * @author yaocs2
 * @since 20220502
 */
@Data
@Table(name = "sync_assets_debt")
public class SyncAssetsDebt {

    /**
     * 主键ID
     */
    @Id
    @Column(name = "DEBT_ID")
    private Long debtId;

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
     * 资产合计(元)
     */
    @Column(name = "TOTAL_ASSETS")
    private BigDecimal totalAssets;

    /**
     * 归属于母公司普通股股东权益合计(元)
     */
    @Column(name = "PH_TOTAL_EQUITY")
    private BigDecimal phTotalEquity;

    /**
     * 归属于母公司普通股股东的净利润(元)
     */
    @Column(name = "PH_NET_PROFIT")
    private BigDecimal phNetProfit;

    /**
     * 营业总收入(元)
     */
    @Column(name = "TOTAL_OP_INCOME")
    private BigDecimal totalOpIncome;

    /**
     * 营业收入(元)
     */
    @Column(name = "OP_INCOME")
    private BigDecimal opIncome;

    /**
     * 销售费用(元)
     */
    @Column(name = "SALES_COSTS")
    private BigDecimal salesCosts;

    /**
     * 管理费用(元)
     */
    @Column(name = "MANAGEMENT_COSTS")
    private BigDecimal managementCosts;

    /**
     * 财务费用(元)
     */
    @Column(name = "FINANCIAL_COSTS")
    private BigDecimal financialCosts;

    /**
     * 研发费用(元)
     */
    @Column(name = "RD_COSTS")
    private BigDecimal rdCosts;

    /**
     * 存货(元)
     */
    @Column(name = "INVENTORY")
    private BigDecimal inventory;

    /**
     * 应付账款(元)
     */
    @Column(name = "ACCOUNTS_PAYABLE")
    private BigDecimal accountsPayable;

    /**
     * 应收账款(元)
     */
    @Column(name = "ACCOUNTS_RECEIVABLE")
    private BigDecimal accountsReceivable;

    /**
     * 预付款项(元)
     */
    @Column(name = "PREPAYMENTS")
    private BigDecimal prepayments;

    /**
     * 预收账款(元)
     */
    @Column(name = "ADVANCE_PAYMENT")
    private BigDecimal advancePayment;

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