package com.jsonyao.mystock.sync.cashflow.service;

import com.jsonyao.mystock.sync.cashflow.dto.SyncCashFlowDTO;

import java.util.List;

/**
 * 现金流量表 业务层
 *
 * @author yaocs2
 * @since 20220502
 */
public interface SyncCashFlowService {

    /**
     * 同步现金流量表
     *
     * @param SyncCashFlowDTOList
     */
    void syncCashFlows(List<SyncCashFlowDTO> SyncCashFlowDTOList);
}
