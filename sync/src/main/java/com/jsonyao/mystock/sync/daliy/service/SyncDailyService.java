package com.jsonyao.mystock.sync.daliy.service;

import com.jsonyao.mystock.sync.bakbasic.tushare.BakBasicParams;
import com.jsonyao.mystock.sync.daliy.tushare.DailyParams;

/**
 * 沪深股票_行情数据_A股日线行情_120 业务层
 *
 * @author yaocs2
 * @since 20220502
 */
public interface SyncDailyService {

    /**
     * 同步沪深股票_行情数据_A股日线行情_120
     */
    void syncDailys(DailyParams dailyParams);
}
