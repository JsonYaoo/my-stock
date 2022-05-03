package com.jsonyao.mystock.sync.bakbasic.tushare;

import com.jsonyao.mystock.common.annotation.TushareDataColumn;
import com.jsonyao.mystock.common.tushare.TushareBaseFields;
import lombok.Getter;

/**
 * 沪深股票_基础数据_备用列表_5000 Fields
 *
 * @author yaocs2
 * @since 20220502
 */
@Getter
public class BakBasicFields extends TushareBaseFields {

    /**
     * 交易日期
     */
    @TushareDataColumn
    private String trade_date;

    /**
     * TS股票代码
     */
    @TushareDataColumn
    private String ts_code;

    /**
     * 股票名称
     */
    @TushareDataColumn
    private String name;

    /**
     * 行业
     */
    @TushareDataColumn
    private String industry;

    /**
     * 地域
     */
    @TushareDataColumn
    private String area;

    /**
     * 动态市盈率
     */
    @TushareDataColumn
    private Double pe;

    /**
     * 流通股本（亿）
     */
    @TushareDataColumn
    private Double float_share;

    /**
     * 总股本（亿）
     */
    @TushareDataColumn
    private Double total_share;

    /**
     * 总资产（亿）
     */
    @TushareDataColumn
    private Double total_assets;

    /**
     * 流动资产（亿）
     */
    @TushareDataColumn
    private Double liquid_assets;

    /**
     * 固定资产（亿）
     */
    @TushareDataColumn
    private Double fixed_assets;

    /**
     * 公积金
     */
    @TushareDataColumn
    private Double reserved;

    /**
     * 每股公积金
     */
    @TushareDataColumn
    private Double reserved_pershare;

    /**
     * 每股收益
     */
    @TushareDataColumn
    private Double eps;

    /**
     * 每股净资产
     */
    @TushareDataColumn
    private Double bvps;

    /**
     * 市净率
     */
    @TushareDataColumn
    private Double pb;

    /**
     * 上市日期
     */
    @TushareDataColumn
    private String list_date;

    /**
     * 未分配利润
     */
    @TushareDataColumn
    private Double undp;

    /**
     * 每股未分配利润
     */
    @TushareDataColumn
    private Double per_undp;

    /**
     * 收入同比（%）
     */
    @TushareDataColumn
    private Double rev_yoy;

    /**
     * 利润同比（%）
     */
    @TushareDataColumn
    private Double profit_yoy;

    /**
     * 毛利率（%）
     */
    @TushareDataColumn
    private Double gpr;

    /**
     * 净利润率（%）
     */
    @TushareDataColumn
    private Double npr;

    /**
     * 股东人数
     */
    @TushareDataColumn
    private Long holder_num;
}
