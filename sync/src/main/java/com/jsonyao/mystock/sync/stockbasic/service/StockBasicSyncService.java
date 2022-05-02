package com.jsonyao.mystock.sync.stockbasic.service;

import com.jsonyao.mystock.sync.stockbasic.tushare.SyncStockBasicParams;

/**
 * 沪深股票_基础数据_股票列表_120 业务层
 *
 * @author yaocs2
 * @since 20220502
 */
public interface StockBasicSyncService {

    /**
     * 同步沪深股票_基础数据_股票列表_120
     */
    void syncStockBasics(SyncStockBasicParams syncStockBasicParams);
}
