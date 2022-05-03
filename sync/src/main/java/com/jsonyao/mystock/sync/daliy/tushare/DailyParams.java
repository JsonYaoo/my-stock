package com.jsonyao.mystock.sync.daliy.tushare;

import com.jsonyao.mystock.common.tushare.TushareBaseParams;
import lombok.Data;

/**
 * 沪深股票_行情数据_A股日线行情_120 Params
 *
 * @author yaocs2
 * @since 20220502
 */
@Data
public class DailyParams extends TushareBaseParams {

    /**
     * TS股票代码
     */
    private String ts_code;

    /**
     * 交易日期 （格式：YYYYMMDD）
     */
    private String trade_date;

    /**
     * 开始日期 （格式：YYYYMMDD）
     */
    private String start_date;

    /**
     * 结束日期 （格式：YYYYMMDD）
     */
    private String end_date;
}