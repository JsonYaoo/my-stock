package com.jsonyao.mystock.sync.cashflow.mapper;

import com.jsonyao.mystock.sync.cashflow.entity.SyncCashFlow;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 现金流量表 持久层
 *
 * @author yaocs2
 * @since 20220502
 */
public interface SyncCashFlowMapper {

    int deleteByPrimaryKey(Long flowId);

    void batchDeleteByCode(@Param("list") List<Map<String, Object>> list);

    int insert(SyncCashFlow record);

    int insertSelective(SyncCashFlow record);

    void batchInsertSelective(@Param("list") List<SyncCashFlow> list);

    SyncCashFlow selectByPrimaryKey(Long flowId);

    int updateByPrimaryKeySelective(SyncCashFlow record);

    int updateByPrimaryKey(SyncCashFlow record);
}