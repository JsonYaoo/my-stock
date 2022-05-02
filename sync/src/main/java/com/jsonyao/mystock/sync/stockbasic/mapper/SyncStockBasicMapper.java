package com.jsonyao.mystock.sync.stockbasic.mapper;

import com.jsonyao.mystock.sync.stockbasic.entity.SyncStockBasic;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 沪深股票_基础数据_股票列表_120 持久层
 *
 * @author yaocs2
 * @since 20220502
 */
public interface SyncStockBasicMapper {

    int deleteByPrimaryKey(Long stockBasicId);

    void batchDeleteByCode(@Param("list") List<String> list);

    int insert(SyncStockBasic record);

    int insertSelective(SyncStockBasic record);

    void batchInsertSelective(@Param("list") List<SyncStockBasic> list);

    SyncStockBasic selectByPrimaryKey(Long stockBasicId);

    int updateByPrimaryKeySelective(SyncStockBasic record);

    int updateByPrimaryKey(SyncStockBasic record);
}