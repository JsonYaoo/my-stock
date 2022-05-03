package com.jsonyao.mystock.sync.tradeCal.tushare;

import com.jsonyao.mystock.common.tushare.TushareBaseParams;
import lombok.Data;

/**
 * 沪深股票_基础数据_交易日历_免费 Params
 *
 * @author yaocs2
 * @since 20220502
 */
@Data
public class TradeCalParams extends TushareBaseParams {

    /**
     * 交易所代码 SSE上交所,SZSE深交所,CFFEX 中金所,SHFE 上期所,CZCE 郑商所,DCE 大商所,INE 上能源
     */
    private String exchange;

    /**
     * 开始日期 （格式：YYYYMMDD）
     */
    private String start_date;

    /**
     * 结束日期 （格式：YYYYMMDD）
     */
    private String end_date;

    /**
     * 是否交易 '0'休市 '1'交易
     */
    private String is_open;
}