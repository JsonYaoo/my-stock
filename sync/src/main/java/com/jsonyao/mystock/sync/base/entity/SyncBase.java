package com.jsonyao.mystock.sync.base.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 股票基本信息表
 *
 * @author yaocs2
 * @since 20220502
 */
@Data
@Table(name = "sync_base")
public class SyncBase {

    /**
     * 主键ID
     */
    @Id
    @Column(name = "BASE_ID")
    private Long baseId;

    /**
     * TS代码
     */
    @Column(name = "TS_CODE")
    private String tsCode;

    /**
     * 市场类型
     */
    @Column(name = "MARKET")
    private String market;

    /**
     * 股票代码
     */
    @Column(name = "STOCK_CODE")
    private String stockCode;

    /**
     * 股票名称
     */
    @Column(name = "STOCK_NAME")
    private String stockName;

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
     * 所属行业
     */
    @Column(name = "INDUSTRY")
    private String industry;

    /**
     * 地域
     */
    @Column(name = "AREA")
    private String area;

    /**
     * 上市状态(L上市 D退市 P暂停上市)
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