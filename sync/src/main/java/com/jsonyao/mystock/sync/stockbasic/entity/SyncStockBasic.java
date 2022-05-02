package com.jsonyao.mystock.sync.stockbasic.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 沪深股票_基础数据_股票列表_120
 *
 * @author yaocs2
 * @since 20220502
 */
@Data
@Table(name = "sync_stock_basic")
public class SyncStockBasic {

    /**
     * 主键ID
     */
    @Id
    @Column(name = "STOCK_BASIC_ID")
    private Long stockBasicId;

    /**
     * TS股票代码
     */
    @Column(name = "TS_CODE")
    private String tsCode;

    /**
     * 股票代码
     */
    @Column(name = "SYMBOL")
    private String symbol;

    /**
     * 股票名称
     */
    @Column(name = "STOCK_NAME")
    private String stockName;

    /**
     * 地域
     */
    @Column(name = "AREA")
    private String area;

    /**
     * 所属行业
     */
    @Column(name = "INDUSTRY")
    private String industry;

    /**
     * 股票全称
     */
    @Column(name = "FULL_NAME")
    private String fullName;

    /**
     * 英文全称
     */
    @Column(name = "EN_NAME")
    private String enName;

    /**
     * 拼音缩写
     */
    @Column(name = "CN_SPELL")
    private String cnSpell;

    /**
     * 市场类型（主板/创业板/科创板/CDR）
     */
    @Column(name = "MARKET")
    private String market;

    /**
     * 交易所代码
     */
    @Column(name = "EXCHANGE")
    private String exchange;

    /**
     * 交易货币
     */
    @Column(name = "CURR_TYPE")
    private String currType;

    /**
     * 上市状态 L上市 D退市 P暂停上市
     */
    @Column(name = "LIST_STATUS")
    private String listStatus;

    /**
     * 上市日期
     */
    @Column(name = "LIST_DATE")
    private Date listDate;

    /**
     * 退市日期
     */
    @Column(name = "DELIST_DATE")
    private Date delistDate;

    /**
     * 是否沪深港通标的，N否 H沪股通 S深股通
     */
    @Column(name = "IS_HS")
    private String isHs;

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