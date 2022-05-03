package com.jsonyao.mystock.sync.daliy.tushare;

import com.jsonyao.mystock.common.annotation.TushareDataColumn;
import com.jsonyao.mystock.common.tushare.TushareBaseFields;
import lombok.Getter;

/**
 * 沪深股票_行情数据_A股日线行情_120 Fields
 *
 * @author yaocs2
 * @since 20220502
 */
@Getter
public class DailyFields extends TushareBaseFields {

    /**
     * 股票代码
     */
    @TushareDataColumn
    private String ts_code;

    /**
     * 交易日期
     */
    @TushareDataColumn
    private String trade_date;

    /**
     * 开盘价
     */
    @TushareDataColumn
    private Double open;

    /**
     * 最高价
     */
    @TushareDataColumn
    private Double high;

    /**
     * 最低价
     */
    @TushareDataColumn
    private Double low;

    /**
     * 收盘价
     */
    @TushareDataColumn
    private Double close;

    /**
     * 昨收价
     */
    @TushareDataColumn
    private Double pre_close;

    /**
     * 涨跌额
     */
    @TushareDataColumn
    private Double change;

    /**
     * 涨跌幅 （未复权）
     */
    @TushareDataColumn
    private Double pct_chg;

    /**
     * 成交量 （手）
     */
    @TushareDataColumn
    private Double vol;

    /**
     * 成交额 （千元）
     */
    @TushareDataColumn
    private Double amount;
}
