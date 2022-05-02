package com.jsonyao.mystock.sync.stockbasic.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.jsonyao.mystock.common.client.TushareClientProxy;
import com.jsonyao.mystock.common.enums.TushareApiEnum;
import com.jsonyao.mystock.common.util.CommonDateUtil;
import com.jsonyao.mystock.sync.stockbasic.entity.SyncStockBasic;
import com.jsonyao.mystock.sync.stockbasic.mapper.SyncStockBasicMapper;
import com.jsonyao.mystock.sync.stockbasic.service.StockBasicSyncService;
import com.jsonyao.mystock.sync.stockbasic.tushare.StockBasicFields;
import com.jsonyao.mystock.sync.stockbasic.tushare.SyncStockBasicParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * 沪深股票_基础数据_股票列表_120 业务实现层
 *
 * @author yaocs2
 * @since 20220502
 */
@Service
@Slf4j
public class StockBasicSyncServiceImpl implements StockBasicSyncService {

    public static final StockBasicFields STOCK_BASIC_FIELDS = new StockBasicFields();

    @Autowired
    private TushareClientProxy tushareClientProxy;
    @Autowired
    private SyncStockBasicMapper syncStockBasicMapper;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
    @Override
    public void syncStockBasics(SyncStockBasicParams syncStockBasicParams) {
        log.info("同步沪深股票_基础数据_股票列表_120: syncStockBasicBO={}", JSONObject.toJSONString(syncStockBasicParams));

        // 通用TuShare请求查询 => 沪深股票_基础数据_股票列表_120
        List<StockBasicFields> stockBasicFields = tushareClientProxy.queryOn120(
                TushareApiEnum.STOCK_BASIC, syncStockBasicParams, STOCK_BASIC_FIELDS, StockBasicFields.class);
        if(CollectionUtils.isEmpty(stockBasicFields)) {
            log.info("同步沪深股票_基础数据_股票列表_120 <= 同步不到任何数据, 本次作业已结束！");
            return;
        }

        // 构建实体列表
        List<SyncStockBasic> entityList = buildSyncStockBasics(stockBasicFields);
        if(CollectionUtils.isEmpty(entityList)) {
            log.info("同步沪深股票_基础数据_股票列表_120 <= 构建不到任何实体列表, 本次作业已结束！");
            return;
        }

        // 删除旧的数据
        deleteIfExists(entityList);

        // 按照size进行分批插入
        batchInsertBySize(entityList, 1000);
    }

    /** 按照size进行分批插入 */
    private void batchInsertBySize(List<SyncStockBasic> entityList, int size) {
        if(CollectionUtils.isEmpty(entityList)) {
            return;
        }

        // 获取分批组数
        int batchCount;
        int remaining = entityList.size() % size;
        if(remaining == 0) {
            batchCount = entityList.size() / size;
        } else {
            batchCount = entityList.size() / size + 1;
        }

        // 分批插入
        List<SyncStockBasic> syncStockBasics;
        for (int i = 0; i < batchCount; i++) {
            // 最后一批
            if (i == batchCount - 1) {
                syncStockBasics = entityList.subList(size * i, entityList.size());
            }
            // 非最后一批
            else {
                syncStockBasics = entityList.subList(size * i, size * i + size);
            }

            // 批量插入
            syncStockBasicMapper.batchInsertSelective(syncStockBasics);
        }
    }

    /** 删除旧的数据, 如果存在的话 */
    private void deleteIfExists(List<SyncStockBasic> entityList) {
        if(CollectionUtils.isEmpty(entityList)) {
            return;
        }

        // 获取TS股票代码
        Set<String> tsCodeSet = new HashSet<>();
        for (SyncStockBasic entity : entityList) {
            tsCodeSet.add(entity.getTsCode());
        }
        if(CollectionUtils.isEmpty(tsCodeSet)) {
            return;
        }

        syncStockBasicMapper.batchDeleteByCode(Lists.newArrayList(tsCodeSet));
    }

    private List<SyncStockBasic> buildSyncStockBasics(List<StockBasicFields> stockBasicFields) {
        if(CollectionUtils.isEmpty(stockBasicFields)) {
            return null;
        }

        List<SyncStockBasic> entityList = new ArrayList<>(stockBasicFields.size());
        for (StockBasicFields fields : stockBasicFields) {
            SyncStockBasic entity = new SyncStockBasic();
            entityList.add(entity);

            entity.setStockBasicId(null);
            entity.setTsCode(fields.getTs_code());
            entity.setSymbol(fields.getSymbol());
            entity.setStockName(fields.getName());
            entity.setArea(fields.getArea());
            entity.setIndustry(fields.getIndustry());
            entity.setFullName(fields.getFullname());
            entity.setEnName(fields.getEnname());
            entity.setCnSpell(fields.getCnspell());
            entity.setMarket(fields.getMarket());
            entity.setStockExchange(fields.getExchange());
            entity.setCurrType(fields.getCurr_type());
            entity.setListStatus(fields.getList_status());
            entity.setListDate(CommonDateUtil.parseyyyyMMdd2Date(fields.getList_date()));
            entity.setDelistDate(CommonDateUtil.parseyyyyMMdd2Date(fields.getDelist_date()));
            entity.setIsHs(fields.getIs_hs());
            entity.setCreationDate(new Date());
            entity.setLastUpdateDate(new Date());
        }

        return entityList;
    }
}
