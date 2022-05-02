package com.jsonyao.mystock.sync.stockcompany.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 沪深股票_基础数据_上市公司基本信息_120
 *
 * @author yaocs2
 * @since 20220502
 */
@Data
@Table(name = "sync_stock_company")
public class SyncStockCompany {
    /**
     * 主键ID
     */
    @Id
    @Column(name = "COMPANY_ID")
    private Long companyId;

    /**
     * TS股票代码
     */
    @Column(name = "TS_CODE")
    private String tsCode;

    /**
     * 交易所代码
     */
    @Column(name = "EXCHANGE")
    private String exchange;

    /**
     * 法人代表
     */
    @Column(name = "CHAIR_MAN")
    private String chairMan;

    /**
     * 总经理
     */
    @Column(name = "MANAGER")
    private String manager;

    /**
     * 董秘
     */
    @Column(name = "SECRETARY")
    private String secretary;

    /**
     * 注册资本
     */
    @Column(name = "REG_CAPITAL")
    private Double regCapital;

    /**
     * 注册日期
     */
    @Column(name = "SETUP_DATE")
    private Date setupDate;

    /**
     * 所在省份
     */
    @Column(name = "PROVINCE")
    private String province;

    /**
     * 所在城市
     */
    @Column(name = "CITY")
    private String city;

    /**
     * 公司介绍
     */
    @Column(name = "INTRODUCTION")
    private String introduction;

    /**
     * 公司主页
     */
    @Column(name = "WEBSITE")
    private String website;

    /**
     * 电子邮件
     */
    @Column(name = "EMAIL")
    private String email;

    /**
     * 办公室
     */
    @Column(name = "OFFICE")
    private String office;

    /**
     * 员工人数
     */
    @Column(name = "EMPLOYEES")
    private Long employees;

    /**
     * 主要业务及产品
     */
    @Column(name = "MAIN_BUSINESS")
    private String mainBusiness;

    /**
     * 经营范围
     */
    @Column(name = "BUSINESS_SCOPE")
    private String businessScope;

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