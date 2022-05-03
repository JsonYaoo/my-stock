package com.jsonyao.mystock.sync.bakbasic.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 沪深股票_基础数据_备用列表_5000
 *
 * @author yaocs2
 * @since 20220502
 */
@Data
@Table(name = "sync_bak_basic")
public class SyncBakBasic {

    /**
     * 主键ID
     */
    @Id
    @Column(name = "BAK_ID")
    private Long bakId;

    /**
     * 交易日期
     */
    @Column(name = "TRADE_DATE")
    private Date tradeDate;

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
     * 地域
     */
    @Column(name = "AREA")
    private String area;

    /**
     * 动态市盈率
     */
    @Column(name = "PE")
    private Double pe;

    /**
     * 流通股本（亿）
     */
    @Column(name = "FLOAT_SHARE")
    private Double floatShare;

    /**
     * 总股本（亿）
     */
    @Column(name = "TOTAL_SHARE")
    private Double totalShare;

    /**
     * 总资产（亿）
     */
    @Column(name = "TOTAL_ASSETS")
    private Double totalAssets;

    /**
     * 流动资产（亿）
     */
    @Column(name = "LIQUID_ASSETS")
    private Double liquidAssets;

    /**
     * 固定资产（亿）
     */
    @Column(name = "FIXED_ASSETS")
    private Double fixedAssets;

    /**
     * 公积金
     */
    @Column(name = "RESERVED")
    private Double reserved;

    /**
     * 每股公积金
     */
    @Column(name = "RESERVED_PERSHARE")
    private Double reservedPershare;

    /**
     * 每股收益
     */
    @Column(name = "EPS")
    private Double eps;

    /**
     * 每股净资产
     */
    @Column(name = "BVPS")
    private Double bvps;

    /**
     * 市净率
     */
    @Column(name = "PB")
    private Double pb;

    /**
     * 上市日期
     */
    @Column(name = "LIST_DATE")
    private Date listDate;

    /**
     * 未分配利润
     */
    @Column(name = "UNDP")
    private Double undp;

    /**
     * 每股未分配利润
     */
    @Column(name = "PER_UNDP")
    private Double perUndp;

    /**
     * 收入同比（%）
     */
    @Column(name = "REV_YOY")
    private Double revYoy;

    /**
     * 利润同比（%）
     */
    @Column(name = "PROFIT_YOY")
    private Double profitYoy;

    /**
     * 毛利率（%）
     */
    @Column(name = "GPR")
    private Double gpr;

    /**
     * 净利润率（%）
     */
    @Column(name = "NPR")
    private Double npr;

    /**
     * 股东人数
     */
    @Column(name = "HOLDER_NUM")
    private Long holderNum;

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