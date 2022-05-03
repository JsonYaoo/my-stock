package com.jsonyao.mystock.sync.bakbasic.tushare;

import com.jsonyao.mystock.common.tushare.TushareBaseParams;
import lombok.Data;

/**
 * 沪深股票_基础数据_备用列表_5000 Params
 *
 * @author yaocs2
 * @since 20220502
 */
@Data
public class BakBasicParams extends TushareBaseParams {

    /**
     * 交易日期
     */
    private String trade_date;

    /**
     * TS股票代码
     */
    private String ts_code;
}