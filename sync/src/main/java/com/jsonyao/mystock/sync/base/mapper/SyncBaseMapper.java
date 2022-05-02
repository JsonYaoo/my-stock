package com.jsonyao.mystock.sync.base.mapper;

import com.jsonyao.mystock.sync.base.entity.SyncBase;

/**
 * 股票基本信息表 持久层
 *
 * @author yaocs2
 * @since 20220502
 */
public interface SyncBaseMapper {

    int deleteByPrimaryKey(Long baseId);

    int insert(SyncBase record);

    int insertSelective(SyncBase record);

    SyncBase selectByPrimaryKey(Long baseId);

    int updateByPrimaryKeySelective(SyncBase record);

    int updateByPrimaryKey(SyncBase record);
}