package com.jsonyao.mystock.sync.base.vo;

import lombok.Data;

import java.util.Date;

/**
 * 股票基本信息表 VO
 *
 * @author yaocs2
 * @since 20220502
 */
@Data
public class SyncBaseVO {


    /**
     * 主键ID
     */
    private Long baseId;

    /**
     * TS代码
     */
    private String tsCode;

    /**
     * 市场类型
     */
    private String market;

    /**
     * 股票代码
     */
    private String stockCode;

    /**
     * 股票名称
     */
    private String stockName;

    /**
     * 股票全称
     */
    private String fullName;

    /**
     * 英文全称
     */
    private String enName;

    /**
     * 拼音缩写
     */
    private String cnSpell;

    /**
     * 所属行业
     */
    private String industry;

    /**
     * 地域
     */
    private String area;

    /**
     * 上市状态(L上市 D退市 P暂停上市)
     */
    private String listStatus;

    /**
     * 上市日期
     */
    private Date listDate;

    /**
     * 退市日期
     */
    private Date delistDate;
}
