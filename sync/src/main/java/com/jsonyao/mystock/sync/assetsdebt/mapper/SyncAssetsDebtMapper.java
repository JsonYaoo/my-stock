package com.jsonyao.mystock.sync.assetsdebt.mapper;

import com.jsonyao.mystock.sync.assetsdebt.entity.SyncAssetsDebt;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 资产负债表 持久层
 *
 * @author yaocs2
 * @since 20220502
 */
public interface SyncAssetsDebtMapper {

    int deleteByPrimaryKey(Long debtId);

    void batchDeleteByCode(@Param("list") List<Map<String, Object>> list);

    int insert(SyncAssetsDebt record);

    int insertSelective(SyncAssetsDebt record);

    void batchInsertSelective(@Param("list") List<SyncAssetsDebt> list);

    SyncAssetsDebt selectByPrimaryKey(Long debtId);

    int updateByPrimaryKeySelective(SyncAssetsDebt record);

    int updateByPrimaryKey(SyncAssetsDebt record);
}