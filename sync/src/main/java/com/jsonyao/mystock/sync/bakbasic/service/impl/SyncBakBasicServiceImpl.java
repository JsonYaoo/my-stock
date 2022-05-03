package com.jsonyao.mystock.sync.bakbasic.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.jsonyao.mystock.common.client.TushareClientProxy;
import com.jsonyao.mystock.common.enums.TushareApiEnum;
import com.jsonyao.mystock.common.util.CommonDateUtil;
import com.jsonyao.mystock.common.util.CommonObjectUtil;
import com.jsonyao.mystock.sync.bakbasic.entity.SyncBakBasic;
import com.jsonyao.mystock.sync.bakbasic.mapper.SyncBakBasicMapper;
import com.jsonyao.mystock.sync.bakbasic.service.SyncBakBasicService;
import com.jsonyao.mystock.sync.bakbasic.tushare.BakBasicFields;
import com.jsonyao.mystock.sync.bakbasic.tushare.BakBasicParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * 沪深股票_基础数据_备用列表_5000 业务实现层
 *
 * @author yaocs2
 * @since 20220502
 */
@Service
@Slf4j
public class SyncBakBasicServiceImpl implements SyncBakBasicService {

    public static final BakBasicFields BAK_BASIC_FIELDS = new BakBasicFields();

    @Autowired
    private TushareClientProxy tushareClientProxy;
    @Autowired
    private SyncBakBasicMapper syncBakBasicMapper;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
    @Override
    public void syncBakBasics(BakBasicParams bakBasicParams) {
        log.info("同步沪深股票_基础数据_备用列表_5000: bakBasicParams={}", JSONObject.toJSONString(bakBasicParams));

        // 通用TuShare请求查询 => 沪深股票_基础数据_备用列表_5000
        List<BakBasicFields> bakBasicFields = tushareClientProxy.queryOn120(
                TushareApiEnum.BAK_BASIC, bakBasicParams, BAK_BASIC_FIELDS, BakBasicFields.class);
        if(CollectionUtils.isEmpty(bakBasicFields)) {
            log.info("同步沪深股票_基础数据_备用列表_5000 <= 同步不到任何数据, 本次作业已结束！");
            return;
        }

        // 构建实体列表
        List<SyncBakBasic> entityList = buildSyncBakBasics(bakBasicFields);
        if(CollectionUtils.isEmpty(entityList)) {
            log.info("同步沪深股票_基础数据_备用列表_5000 <= 构建不到任何实体列表, 本次作业已结束！");
            return;
        }

        // 删除旧的数据
        deleteIfExists(entityList);

        // 按照size进行分批插入
        CommonObjectUtil.batchInsertBySize(entityList, 1000, list -> syncBakBasicMapper.batchInsertSelective(list));

    }

    /** 删除旧的数据, 如果存在的话 */
    private void deleteIfExists(List<SyncBakBasic> entityList) {
        if (CollectionUtils.isEmpty(entityList)) {
            return;
        }

        // 获取TS股票代码
        List<Map<String, Object>> mapList = new ArrayList<>();
        for (SyncBakBasic entity : entityList) {
            Map<String, Object> map = new HashMap<>();
            map.put("tradeDate", CommonDateUtil.formatDate2yyyyMMdd(entity.getTradeDate()));
            map.put("tsCode", entity.getTsCode());
            mapList.add(map);
        }
        if (CollectionUtils.isEmpty(mapList)) {
            return;
        }

        syncBakBasicMapper.batchDeleteByCode(mapList);
    }

    /** 构建实体列表 */
    private List<SyncBakBasic> buildSyncBakBasics(List<BakBasicFields> stockBasicFields) {
        if(CollectionUtils.isEmpty(stockBasicFields)) {
            return null;
        }

        List<SyncBakBasic> entityList = new ArrayList<>(stockBasicFields.size());
        for (BakBasicFields fields : stockBasicFields) {
            SyncBakBasic entity = new SyncBakBasic();
            entityList.add(entity);

            entity.setBakId(null);
            entity.setTradeDate(CommonDateUtil.parseyyyyMMdd2Date(fields.getTrade_date()));
            entity.setTsCode(fields.getTs_code());
            entity.setStockName(fields.getName());
            entity.setIndustry(fields.getIndustry());
            entity.setArea(fields.getArea());
            entity.setPe(fields.getPe());
            entity.setFloatShare(fields.getFloat_share());
            entity.setTotalShare(fields.getTotal_share());
            entity.setTotalAssets(fields.getTotal_assets());
            entity.setLiquidAssets(fields.getLiquid_assets());
            entity.setFixedAssets(fields.getFixed_assets());
            entity.setReserved(fields.getReserved());
            entity.setReservedPershare(fields.getReserved_pershare());
            entity.setEps(fields.getEps());
            entity.setBvps(fields.getBvps());
            entity.setPb(fields.getPb());
            entity.setListDate(CommonDateUtil.parseyyyyMMdd2Date(fields.getList_date()));
            entity.setUndp(fields.getUndp());
            entity.setPerUndp(fields.getPer_undp());
            entity.setRevYoy(fields.getRev_yoy());
            entity.setProfitYoy(fields.getProfit_yoy());
            entity.setGpr(fields.getGpr());
            entity.setNpr(fields.getNpr());
            entity.setHolderNum(fields.getHolder_num());
            CommonObjectUtil.setValue4Create(entity);
        }

        return entityList;
    }
}
