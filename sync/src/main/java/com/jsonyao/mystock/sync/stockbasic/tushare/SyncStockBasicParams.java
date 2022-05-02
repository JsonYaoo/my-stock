package com.jsonyao.mystock.sync.stockbasic.tushare;

import com.jsonyao.mystock.common.tushare.TushareBaseParams;
import lombok.Data;

/**
 * 沪深股票_基础数据_股票列表_120 Params
 *
 * @author yaocs2
 * @since 20220502
 */
@Data
public class SyncStockBasicParams extends TushareBaseParams {

    /**
     * 是否沪深港通标的，N否 H沪股通 S深股通
     */
    private String isHs;

    /**
     * 上市状态 L上市 D退市 P暂停上市
     */
    private String listStatus;

    /**
     * 交易所代码
     */
    private String stockExchange;

    /**
     * TS股票代码
     */
    private String tsCode;

    /**
     * 市场类型（主板/创业板/科创板/CDR）
     */
    private String market;

    /**
     * 股票名称
     */
    private String stockName;

    /**
     * 页容量
     */
    private Integer limit;

    /**
     * 偏移量
     */
    private Integer offset;
}