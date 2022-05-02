package com.jsonyao.mystock.sync.stockcompany.mapper;

import com.jsonyao.mystock.sync.stockcompany.entity.SyncStockCompany;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 沪深股票_基础数据_上市公司基本信息_120 持久层
 *
 * @author yaocs2
 * @since 20220502
 */
public interface SyncStockCompanyMapper {

    int deleteByPrimaryKey(Long companyId);

    void batchDeleteByCode(@Param("list") List<String> list);

    int insert(SyncStockCompany record);

    int insertSelective(SyncStockCompany record);

    void batchInsertSelective(@Param("list") List<SyncStockCompany> list);

    SyncStockCompany selectByPrimaryKey(Long companyId);

    int updateByPrimaryKeySelective(SyncStockCompany record);

    int updateByPrimaryKey(SyncStockCompany record);
}