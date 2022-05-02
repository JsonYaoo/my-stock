package com.jsonyao.mystock.sync.stockcompany.tushare;

import com.jsonyao.mystock.common.tushare.TushareBaseParams;
import lombok.Data;

/**
 * 沪深股票_基础数据_上市公司基本信息_120 Params
 *
 * @author yaocs2
 * @since 20220502
 */
@Data
public class StockCompanyParams extends TushareBaseParams {

    /**
     * TS股票代码
     */
    private String ts_code;

    /**
     * 交易所代码
     */
    private String exchange;
}