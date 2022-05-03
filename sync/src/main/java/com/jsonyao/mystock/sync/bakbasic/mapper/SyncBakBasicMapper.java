package com.jsonyao.mystock.sync.bakbasic.mapper;

import com.jsonyao.mystock.sync.bakbasic.entity.SyncBakBasic;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 沪深股票_基础数据_备用列表_5000 持久层
 *
 * @author yaocs2
 * @since 20220502
 */
public interface SyncBakBasicMapper {

    int deleteByPrimaryKey(Long bakId);

    void batchDeleteByCode(@Param("list") List<Map<String, Object>> list);

    int insert(SyncBakBasic record);

    int insertSelective(SyncBakBasic record);

    void batchInsertSelective(@Param("list") List<SyncBakBasic> list);

    SyncBakBasic selectByPrimaryKey(Long bakId);

    int updateByPrimaryKeySelective(SyncBakBasic record);

    int updateByPrimaryKey(SyncBakBasic record);
}