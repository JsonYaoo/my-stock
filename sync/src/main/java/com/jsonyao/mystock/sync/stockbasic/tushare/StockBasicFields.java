package com.jsonyao.mystock.sync.stockbasic.tushare;

import com.jsonyao.mystock.common.annotation.TushareDataColumn;
import com.jsonyao.mystock.common.tushare.TushareBaseFields;
import lombok.Getter;

/**
 * 沪深股票_基础数据_股票列表_120 Fields
 *
 * @author yaocs2
 * @since 20220502
 */
@Getter
public class StockBasicFields extends TushareBaseFields {

    /**
     * TS股票代码
     */
    @TushareDataColumn()
    private String ts_code;

    /**
     * 股票代码
     */
    @TushareDataColumn()
    private String symbol;

    /**
     * 股票名称
     */
    @TushareDataColumn()
    private String name;

    /**
     * 地域
     */
    @TushareDataColumn()
    private String area;

    /**
     * 所属行业
     */
    @TushareDataColumn()
    private String industry;

    /**
     * 股票全称
     */
    @TushareDataColumn()
    private String fullname;

    /**
     * 英文全称
     */
    @TushareDataColumn()
    private String enname;

    /**
     * 拼音缩写
     */
    @TushareDataColumn()
    private String cnspell;

    /**
     * 市场类型（主板/创业板/科创板/CDR）
     */
    @TushareDataColumn()
    private String market;

    /**
     * 交易所代码
     */
    @TushareDataColumn()
    private String exchange;

    /**
     * 交易货币
     */
    @TushareDataColumn()
    private String curr_type;

    /**
     * 上市状态 L上市 D退市 P暂停上市
     */
    @TushareDataColumn()
    private String list_status;

    /**
     * 上市日期
     */
    @TushareDataColumn()
    private String list_date;

    /**
     * 退市日期
     */
    @TushareDataColumn()
    private String delist_date;

    /**
     * 是否沪深港通标的，N否 H沪股通 S深股通
     */
    @TushareDataColumn()
    private String is_hs;
}
