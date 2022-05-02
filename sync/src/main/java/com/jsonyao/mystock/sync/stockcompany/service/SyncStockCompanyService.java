package com.jsonyao.mystock.sync.stockcompany.service;

import com.jsonyao.mystock.sync.stockcompany.tushare.StockCompanyParams;

/**
 * 沪深股票_基础数据_上市公司基本信息_120 业务层
 *
 * @author yaocs2
 * @since 20220502
 */
public interface SyncStockCompanyService {

    /**
     * 同步沪深股票_基础数据_上市公司基本信息_120
     */
    void syncStockCompany(StockCompanyParams stockCompanyParams);
}
