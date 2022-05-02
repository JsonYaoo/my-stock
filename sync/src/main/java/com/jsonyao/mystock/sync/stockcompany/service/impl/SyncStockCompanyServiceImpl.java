package com.jsonyao.mystock.sync.stockcompany.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.jsonyao.mystock.common.client.TushareClientProxy;
import com.jsonyao.mystock.common.enums.TushareApiEnum;
import com.jsonyao.mystock.common.util.CommonObjectUtil;
import com.jsonyao.mystock.sync.stockcompany.entity.SyncStockCompany;
import com.jsonyao.mystock.sync.stockcompany.mapper.SyncStockCompanyMapper;
import com.jsonyao.mystock.sync.stockcompany.service.SyncStockCompanyService;
import com.jsonyao.mystock.sync.stockcompany.tushare.StockCompanyFields;
import com.jsonyao.mystock.sync.stockcompany.tushare.StockCompanyParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 沪深股票_基础数据_上市公司基本信息_120 业务实现层
 *
 * @author yaocs2
 * @since 20220502
 */
@Service
@Slf4j
public class SyncStockCompanyServiceImpl implements SyncStockCompanyService {

    public static final StockCompanyFields STOCK_COMPANY_FIELDS = new StockCompanyFields();

    @Autowired
    private TushareClientProxy tushareClientProxy;
    @Autowired
    private SyncStockCompanyMapper syncStockCompanyMapper;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
    @Override
    public void syncStockCompany(StockCompanyParams stockCompanyParams) {
        log.info("同步沪深股票_基础数据_上市公司基本信息_120: stockCompanyParams={}", JSONObject.toJSONString(stockCompanyParams));

        // 通用TuShare请求查询 => 同步沪深股票_基础数据_上市公司基本信息_120
        List<StockCompanyFields> stockCompanyFields = tushareClientProxy.queryOn120(
                TushareApiEnum.STOCK_COMPANY, stockCompanyParams, STOCK_COMPANY_FIELDS, StockCompanyFields.class);
        if(CollectionUtils.isEmpty(stockCompanyFields)) {
            log.info("同步沪深股票_基础数据_上市公司基本信息_120 <= 同步不到任何数据, 本次作业已结束！");
            return;
        }

        // 构建实体列表
        List<SyncStockCompany> entityList = buildSyncStockCompanys(stockCompanyFields);
        if(CollectionUtils.isEmpty(entityList)) {
            log.info("同步沪深股票_基础数据_上市公司基本信息_120 <= 构建不到任何实体列表, 本次作业已结束！");
            return;
        }

        // 删除旧的数据
        deleteIfExists(entityList);

        // 按照size进行分批插入
        CommonObjectUtil.batchInsertBySize(entityList, 1000, list -> syncStockCompanyMapper.batchInsertSelective(list));

    }

    /** 删除旧的数据, 如果存在的话 */
    private void deleteIfExists(List<SyncStockCompany> entityList) {
        if(CollectionUtils.isEmpty(entityList)) {
            return;
        }

        // 获取TS股票代码
        Set<String> tsCodeSet = new HashSet<>();
        for (SyncStockCompany entity : entityList) {
            tsCodeSet.add(entity.getTsCode());
        }
        if(CollectionUtils.isEmpty(tsCodeSet)) {
            return;
        }

        syncStockCompanyMapper.batchDeleteByCode(Lists.newArrayList(tsCodeSet));
    }

    /** 构建实体列表 */
    private List<SyncStockCompany> buildSyncStockCompanys(List<StockCompanyFields> stockCompanyFields) {
        if(CollectionUtils.isEmpty(stockCompanyFields)) {
            return null;
        }

        List<SyncStockCompany> entityList = new ArrayList<>(stockCompanyFields.size());
        for (StockCompanyFields fields : stockCompanyFields) {
            SyncStockCompany entity = new SyncStockCompany();
            entityList.add(entity);

            entity.setCompanyId(null);
            entity.setTsCode(fields.getTs_code());
            entity.setExchange(fields.getExchange());
            entity.setChairMan(fields.getChairman());
            entity.setManager(fields.getManager());
            entity.setSecretary(fields.getSecretary());
            entity.setRegCapital(fields.getReg_capital());
            entity.setSetupDate(fields.getSetup_date());
            entity.setProvince(fields.getProvince());
            entity.setCity(fields.getCity());
            entity.setIntroduction(fields.getIntroduction());
            entity.setWebsite(fields.getWebsite());
            entity.setEmail(fields.getEmail());
            entity.setOffice(fields.getOffice());
            entity.setEmployees(fields.getEmployees());
            entity.setMainBusiness(fields.getMain_business());
            entity.setBusinessScope(fields.getBusiness_scope());
            CommonObjectUtil.setValue4Create(entity);
        }

        return entityList;
    }
}
