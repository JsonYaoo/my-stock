package com.jsonyao.mystock.sync.assetsdebt.service;

import com.jsonyao.mystock.sync.assetsdebt.dto.SyncAssetsDebtDTO;

import java.util.List;

/**
 * 资产负债表 业务层
 *
 * @author yaocs2
 * @since 20220502
 */
public interface SyncAssetsDebtService {

    /**
     * 同步资产负债表
     *
     * @param syncAssetsDebtDTOList
     */
    void syncAssetsDebts(List<SyncAssetsDebtDTO> syncAssetsDebtDTOList);
}
