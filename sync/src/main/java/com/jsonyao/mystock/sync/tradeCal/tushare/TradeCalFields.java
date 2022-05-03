package com.jsonyao.mystock.sync.tradeCal.tushare;

import com.jsonyao.mystock.common.annotation.TushareDataColumn;
import com.jsonyao.mystock.common.tushare.TushareBaseFields;
import lombok.Getter;

/**
 * 沪深股票_基础数据_交易日历_免费 Fields
 *
 * @author yaocs2
 * @since 20220502
 */
@Getter
public class TradeCalFields extends TushareBaseFields {

    /**
     * 交易所代码 SSE上交所,SZSE深交所,CFFEX 中金所,SHFE 上期所,CZCE 郑商所,DCE 大商所,INE 上能源
     */
    @TushareDataColumn
    private String exchange;

    /**
     * 日历日期 （格式：YYYYMMDD）
     */
    @TushareDataColumn
    private String cal_date;

    /**
     * 是否交易 '0'休市 '1'交易
     */
    @TushareDataColumn
    private String is_open;

    /**
     * 上一个交易日 （格式：YYYYMMDD）
     */
    @TushareDataColumn
    private String pretrade_date;
}
