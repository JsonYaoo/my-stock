package com.jsonyao.mystock.sync.bakbasic.service;

import com.jsonyao.mystock.sync.bakbasic.tushare.BakBasicParams;
import com.jsonyao.mystock.sync.stockbasic.entity.SyncStockBasic;
import com.jsonyao.mystock.sync.stockbasic.tushare.StockBasicParams;

import java.util.List;

/**
 * 沪深股票_基础数据_备用列表_5000 业务层
 *
 * @author yaocs2
 * @since 20220502
 */
public interface SyncBakBasicService {

    /**
     * 同步沪深股票_基础数据_备用列表_5000
     */
    void syncBakBasics(BakBasicParams bakBasicParams);
}
