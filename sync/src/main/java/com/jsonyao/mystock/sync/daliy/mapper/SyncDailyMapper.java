package com.jsonyao.mystock.sync.daliy.mapper;


import com.jsonyao.mystock.sync.daliy.entity.SyncDaily;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 沪深股票_行情数据_A股日线行情_120 持久层
 *
 * @author yaocs2
 * @since 20220502
 */
public interface SyncDailyMapper {

    int deleteByPrimaryKey(Long dailyId);

    void batchDeleteByCode(@Param("list") List<Map<String, Object>> list);

    int insert(SyncDaily record);

    int insertSelective(SyncDaily record);

    void batchInsertSelective(@Param("list") List<SyncDaily> list);

    SyncDaily selectByPrimaryKey(Long dailyId);

    int updateByPrimaryKeySelective(SyncDaily record);

    int updateByPrimaryKey(SyncDaily record);
}