package com.jsonyao.mystock.sync.tradeCal.service;

import java.util.Date;

/**
 * 沪深股票_基础数据_股票列表_120 业务层
 *
 * @author yaocs2
 * @since 20220502
 */
public interface TradeCalService {

    /**
     * 查询上一个交易日
     *
     * @param date
     * @return
     */
    Date queryPreTradeDate(Date date);
}
