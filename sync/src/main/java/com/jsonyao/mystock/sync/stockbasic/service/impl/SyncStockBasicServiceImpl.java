package com.jsonyao.mystock.sync.stockbasic.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.jsonyao.mystock.common.client.TushareClientProxy;
import com.jsonyao.mystock.common.enums.TushareApiEnum;
import com.jsonyao.mystock.common.util.CommonDateUtil;
import com.jsonyao.mystock.common.util.CommonObjectUtil;
import com.jsonyao.mystock.sync.stockbasic.entity.SyncStockBasic;
import com.jsonyao.mystock.sync.stockbasic.mapper.SyncStockBasicMapper;
import com.jsonyao.mystock.sync.stockbasic.service.SyncStockBasicService;
import com.jsonyao.mystock.sync.stockbasic.tushare.StockBasicFields;
import com.jsonyao.mystock.sync.stockbasic.tushare.StockBasicParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 沪深股票_基础数据_股票列表_120 业务实现层
 *
 * @author yaocs2
 * @since 20220502
 */
@Service
@Slf4j
public class SyncStockBasicServiceImpl implements SyncStockBasicService {

    public static final StockBasicFields STOCK_BASIC_FIELDS = new StockBasicFields();
    public static final Map<String, String> SYMBOL_TSCODE_CACHE = new ConcurrentHashMap<>();

    @Autowired
    private TushareClientProxy tushareClientProxy;
    @Autowired
    private SyncStockBasicMapper syncStockBasicMapper;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
    @Override
    public void syncStockBasics(StockBasicParams stockBasicParams) {
        log.info("同步沪深股票_基础数据_股票列表_120: stockBasicParams={}", JSONObject.toJSONString(stockBasicParams));

        // 通用TuShare请求查询 => 沪深股票_基础数据_股票列表_120
        List<StockBasicFields> stockBasicFields = tushareClientProxy.queryOn120(
                TushareApiEnum.STOCK_BASIC, stockBasicParams, STOCK_BASIC_FIELDS, StockBasicFields.class);
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
        CommonObjectUtil.batchInsertBySize(entityList, 1000, list -> syncStockBasicMapper.batchInsertSelective(list));

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

    /** 构建实体列表 */
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
            entity.setExchange(fields.getExchange());
            entity.setCurrType(fields.getCurr_type());
            entity.setListStatus(fields.getList_status());
            entity.setListDate(CommonDateUtil.parseyyyyMMdd2Date(fields.getList_date()));
            entity.setDelistDate(CommonDateUtil.parseyyyyMMdd2Date(fields.getDelist_date()));
            entity.setIsHs(fields.getIs_hs());
            CommonObjectUtil.setValue4Create(entity);
        }

        return entityList;
    }

    @Override
    public List<SyncStockBasic> queryAllSyncStockBasics() {
        log.info("查询所有股票列表~");
        return syncStockBasicMapper.selectAll();
    }

    @Override
    public Map<String, String> queryTsCodeCache() {
        log.info("查询所有股票代码-TS股票代码缓存~");
        if(!CollectionUtils.isEmpty(SYMBOL_TSCODE_CACHE)) {
            return Collections.unmodifiableMap(SYMBOL_TSCODE_CACHE);
        }

        List<SyncStockBasic> syncStockBasics = queryAllSyncStockBasics();
        if(CollectionUtils.isEmpty(syncStockBasics)) {
            SYMBOL_TSCODE_CACHE.clear();
            return null;
        }

        for (SyncStockBasic basic : syncStockBasics) {
            SYMBOL_TSCODE_CACHE.put(basic.getSymbol(), basic.getTsCode());
        }

        return Collections.unmodifiableMap(SYMBOL_TSCODE_CACHE);
    }
}