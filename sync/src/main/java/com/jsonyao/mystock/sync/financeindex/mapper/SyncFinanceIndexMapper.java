package com.jsonyao.mystock.sync.financeindex.mapper;

import com.jsonyao.mystock.sync.financeindex.entity.SyncFinanceIndex;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 财务指标表 持久层
 *
 * @author yaocs2
 * @since 20220502
 */
public interface SyncFinanceIndexMapper {

    int deleteByPrimaryKey(Long indexId);

    void batchDeleteByCode(@Param("list") List<Map<String, Object>> list);

    int insert(SyncFinanceIndex record);

    int insertSelective(SyncFinanceIndex record);

    void batchInsertSelective(@Param("list") List<SyncFinanceIndex> list);

    SyncFinanceIndex selectByPrimaryKey(Long indexId);

    int updateByPrimaryKeySelective(SyncFinanceIndex record);

    int updateByPrimaryKey(SyncFinanceIndex record);
}