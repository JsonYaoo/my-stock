package com.jsonyao.mystock.sync.daliy.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.jsonyao.mystock.common.client.TushareClientProxy;
import com.jsonyao.mystock.common.enums.TushareApiEnum;
import com.jsonyao.mystock.common.util.CommonDateUtil;
import com.jsonyao.mystock.common.util.CommonObjectUtil;
import com.jsonyao.mystock.sync.daliy.entity.SyncDaily;
import com.jsonyao.mystock.sync.daliy.mapper.SyncDailyMapper;
import com.jsonyao.mystock.sync.daliy.service.SyncDailyService;
import com.jsonyao.mystock.sync.daliy.tushare.DailyFields;
import com.jsonyao.mystock.sync.daliy.tushare.DailyParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 沪深股票_行情数据_A股日线行情_120 业务实现层
 *
 * @author yaocs2
 * @since 20220502
 */
@Service
@Slf4j
public class SyncDailyServiceImpl implements SyncDailyService {

    public static final DailyFields DAILY_FIELDS = new DailyFields();

    @Autowired
    private TushareClientProxy tushareClientProxy;
    @Autowired
    private SyncDailyMapper syncDailyMapper;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
    @Override
    public void syncDailys(DailyParams dailyParams) {
        log.info("同步沪深股票_行情数据_A股日线行情_120: dailyParams={}", JSONObject.toJSONString(dailyParams));

        // 通用TuShare请求查询 => 沪深股票_行情数据_A股日线行情_120
        List<DailyFields> dailyFields = tushareClientProxy.queryOn120(
                TushareApiEnum.DAILY, dailyParams, DAILY_FIELDS, DailyFields.class);
        if(CollectionUtils.isEmpty(dailyFields)) {
            log.info("同步沪深股票_行情数据_A股日线行情_120 <= 同步不到任何数据, 本次作业已结束！");
            return;
        }

        // 构建实体列表
        List<SyncDaily> entityList = buildDailyFields(dailyFields);
        if(CollectionUtils.isEmpty(entityList)) {
            log.info("同步沪深股票_行情数据_A股日线行情_120 <= 构建不到任何实体列表, 本次作业已结束！");
            return;
        }

        // 删除旧的数据
        deleteIfExists(entityList);

        // 按照size进行分批插入
        CommonObjectUtil.batchInsertBySize(entityList, 1000, list -> syncDailyMapper.batchInsertSelective(list));

    }

    /** 删除旧的数据, 如果存在的话 */
    private void deleteIfExists(List<SyncDaily> entityList) {
        if (CollectionUtils.isEmpty(entityList)) {
            return;
        }

        // 获取TS股票代码
        List<Map<String, Object>> mapList = new ArrayList<>();
        for (SyncDaily entity : entityList) {
            Map<String, Object> map = new HashMap<>();
            map.put("tradeDate", CommonDateUtil.formatDate2yyyyMMdd(entity.getTradeDate()));
            map.put("tsCode", entity.getTsCode());
            mapList.add(map);
        }
        if (CollectionUtils.isEmpty(mapList)) {
            return;
        }

        syncDailyMapper.batchDeleteByCode(mapList);
    }

    /** 构建实体列表 */
    private List<SyncDaily> buildDailyFields(List<DailyFields> dailyFields) {
        if(CollectionUtils.isEmpty(dailyFields)) {
            return null;
        }

        List<SyncDaily> entityList = new ArrayList<>(dailyFields.size());
        for (DailyFields fields : dailyFields) {
            SyncDaily entity = new SyncDaily();
            entityList.add(entity);

            entity.setDailyId(null);
            entity.setTsCode(fields.getTs_code());
            entity.setTradeDate(CommonDateUtil.parseyyyyMMdd2Date(fields.getTrade_date()));
            entity.setOpen(fields.getOpen());
            entity.setHigh(fields.getHigh());
            entity.setLow(fields.getLow());
            entity.setClose(fields.getClose());
            entity.setPreClose(fields.getPre_close());
            entity.setChange(fields.getChange());
            entity.setPctChg(fields.getPct_chg());
            entity.setVol(fields.getVol());
            entity.setAmount(fields.getAmount());
            CommonObjectUtil.setValue4Create(entity);
        }

        return entityList;
    }
}
