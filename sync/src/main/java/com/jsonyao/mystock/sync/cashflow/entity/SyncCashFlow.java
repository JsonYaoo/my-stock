package com.jsonyao.mystock.sync.cashflow.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 现金流量表
 *
 * @author yaocs2
 * @since 20220502
 */
@Data
@Table(name = "sync_cash_flow")
public class SyncCashFlow {
    
    /**
     * 主键ID
     */
    @Id
    @Column(name = "FLOW_ID")
    private Long flowId;

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
     * 购建固定资产、无形资产及其他长期资产所支付的现金(元)
     */
    @Column(name = "FIL_ASSETS_COSTS")
    private BigDecimal filAssetsCosts;

    /**
     * 销售商品提供劳务收到的现金(元)
     */
    @Column(name = "CF_FROM_SALES")
    private BigDecimal cfFromSales;

    /**
     * 自由现金流量(元)
     */
    @Column(name = "FREE_CASH_FLOW")
    private BigDecimal freeCashFlow;

    /**
     * 经营活动产生的现金流量净额(元)
     */
    @Column(name = "NCF_FROM_BUSINESS")
    private BigDecimal ncfFromBusiness;

    /**
     * 投资活动产生的现金流量净额(元)
     */
    @Column(name = "NCF_FROM_INVESTMENT")
    private BigDecimal ncfFromInvestment;

    /**
     * 筹资活动产生的现金流量净额(元)
     */
    @Column(name = "NCF_FROM_FINANCING")
    private BigDecimal ncfFromFinancing;

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