package com.jsonyao.mystock.sync.financeindex.service;

import com.jsonyao.mystock.sync.financeindex.dto.SyncFinanceIndexDTO;

import java.util.List;

/**
 * 财务指标表 业务层
 *
 * @author yaocs2
 * @since 20220502
 */
public interface SyncFinanceIndexService {

    /**
     * 同步财务指标表
     *
     * @param syncFinanceIndexDTOList
     */
    void syncFinanceIndexs(List<SyncFinanceIndexDTO> syncFinanceIndexDTOList);
}
