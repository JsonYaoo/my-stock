package com.jsonyao.mystock.sync.base.service;

import com.jsonyao.mystock.sync.base.bo.SyncBaseBO;
import com.jsonyao.mystock.sync.base.vo.SyncBaseVO;

/**
 * 股票基本信息表 业务层
 *
 * @author yaocs2
 * @since 20220502
 */
public interface SyncBaseService {

    /**
     * 根据参数查询股票基本信息
     *
     * @param syncBaseBO
     * @return
     */
    SyncBaseVO querySyncBaseByParams(SyncBaseBO syncBaseBO);
}