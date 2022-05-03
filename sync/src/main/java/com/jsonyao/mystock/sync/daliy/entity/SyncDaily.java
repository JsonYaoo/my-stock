package com.jsonyao.mystock.sync.daliy.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 沪深股票_行情数据_A股日线行情_120
 *
 * @author yaocs2
 * @since 20220502
 */
@Data
@Table(name = "sync_daily")
public class SyncDaily {

    /**
     * 主键ID
     */
    @Id
    @Column(name = "DAILY_ID")
    private Long dailyId;

    /**
     * 股票代码
     */
    @Column(name = "TS_CODE")
    private String tsCode;

    /**
     * 交易日期
     */
    @Column(name = "TRADE_DATE")
    private Date tradeDate;

    /**
     * 开盘价
     */
    @Column(name = "OPEN")
    private Double open;

    /**
     * 最高价
     */
    @Column(name = "HIGH")
    private Double high;

    /**
     * 最低价
     */
    @Column(name = "LOW")
    private Double low;

    /**
     * 收盘价
     */
    @Column(name = "CLOSE")
    private Double close;

    /**
     * 昨收价
     */
    @Column(name = "PRE_CLOSE")
    private Double preClose;

    /**
     * 涨跌额
     */
    @Column(name = "CHANGE")
    private Double change;

    /**
     * 涨跌幅 （未复权）
     */
    @Column(name = "PCT_CHG")
    private Double pctChg;

    /**
     * 成交量 （手）
     */
    @Column(name = "VOL")
    private Double vol;

    /**
     * 成交额 （千元）
     */
    @Column(name = "AMOUNT")
    private Double amount;

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