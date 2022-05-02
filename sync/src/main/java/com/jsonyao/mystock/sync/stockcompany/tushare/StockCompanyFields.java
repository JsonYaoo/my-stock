package com.jsonyao.mystock.sync.stockcompany.tushare;

import com.jsonyao.mystock.common.annotation.TushareDataColumn;
import com.jsonyao.mystock.common.tushare.TushareBaseFields;
import lombok.Getter;

import java.util.Date;

/**
 * 沪深股票_基础数据_上市公司基本信息_120 Fields
 *
 * @author yaocs2
 * @since 20220502
 */
@Getter
public class StockCompanyFields extends TushareBaseFields {

    /**
     * TS股票代码
     */
    @TushareDataColumn()
    private String ts_code;

    /**
     * 交易所代码: SSE上交所 SZSE深交所
     */
    @TushareDataColumn()
    private String exchange;

    /**
     * 法人代表
     */
    @TushareDataColumn()
    private String chairman;

    /**
     * 总经理
     */
    @TushareDataColumn()
    private String manager;

    /**
     * 董秘
     */
    @TushareDataColumn()
    private String secretary;

    /**
     * 注册资本
     */
    @TushareDataColumn()
    private Double reg_capital;

    /**
     * 注册日期
     */
    @TushareDataColumn()
    private Date setup_date;

    /**
     * 所在省份
     */
    @TushareDataColumn()
    private String province;

    /**
     * 所在城市
     */
    @TushareDataColumn()
    private String city;

    /**
     * 公司介绍
     */
    @TushareDataColumn()
    private String introduction;

    /**
     * 公司主页
     */
    @TushareDataColumn()
    private String website;

    /**
     * 电子邮件
     */
    @TushareDataColumn()
    private String email;

    /**
     * 办公室
     */
    @TushareDataColumn()
    private String office;

    /**
     * 员工人数
     */
    @TushareDataColumn()
    private Long employees;

    /**
     * 主要业务及产品
     */
    @TushareDataColumn()
    private String main_business;

    /**
     * 经营范围
     */
    @TushareDataColumn()
    private String business_scope;
}
