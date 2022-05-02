package com.jsonyao.mystock.sync.stockbasic.service;

import com.jsonyao.mystock.sync.stockbasic.entity.SyncStockBasic;
import com.jsonyao.mystock.sync.stockbasic.tushare.StockBasicParams;

import java.util.List;

/**
 * 沪深股票_基础数据_股票列表_120 业务层
 *
 * @author yaocs2
 * @since 20220502
 */
public interface SyncStockBasicService {

    /**
     * 同步沪深股票_基础数据_股票列表_120
     */
    void syncStockBasics(StockBasicParams stockBasicParams);

    /**
     * 查询所有股票列表
     *
     * @return
     */
    List<SyncStockBasic> queryAllSyncStockBasics();
}
