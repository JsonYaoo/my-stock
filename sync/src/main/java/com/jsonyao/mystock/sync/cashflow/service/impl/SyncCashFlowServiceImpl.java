package com.jsonyao.mystock.sync.cashflow.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.jsonyao.mystock.common.util.CommonObjectUtil;
import com.jsonyao.mystock.sync.cashflow.dto.SyncCashFlowDTO;
import com.jsonyao.mystock.sync.cashflow.entity.SyncCashFlow;
import com.jsonyao.mystock.sync.cashflow.mapper.SyncCashFlowMapper;
import com.jsonyao.mystock.sync.cashflow.service.SyncCashFlowService;
import com.jsonyao.mystock.sync.stockbasic.service.SyncStockBasicService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
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
 * 现金流量表 业务实现层
 *
 * @author yaocs2
 * @since 20220502
 */
@Service
@Slf4j
public class SyncCashFlowServiceImpl implements SyncCashFlowService {

    @Autowired
    private SyncStockBasicService syncStockBasicService;
    @Autowired
    private SyncCashFlowMapper syncCashFlowMapper;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
    @Override
    public void syncCashFlows(List<SyncCashFlowDTO> SyncCashFlowDTOList) {
        log.info("同步现金流量表: SyncCashFlowDTOList={}", JSONObject.toJSONString(SyncCashFlowDTOList));

        // 构造Entity列表
        List<SyncCashFlow> entityList = buildSyncCashFlowList(SyncCashFlowDTOList);

        // 删除旧的数据
        deleteIfExists(entityList);

        // 按照size进行分批插入
        CommonObjectUtil.batchInsertBySize(entityList, 1000, list -> syncCashFlowMapper.batchInsertSelective(list));
    }

    /** 构造Entity列表 */
    private List<SyncCashFlow> buildSyncCashFlowList(List<SyncCashFlowDTO> dtoList) {
        if(CollectionUtils.isEmpty(dtoList)) {
            return null;
        }

        // 查询所有股票代码-TS股票代码缓存
        Map<String, String> tsCodeCache = syncStockBasicService.queryTsCodeCache();

        // 构造Entity列表
        List<SyncCashFlow> entityList = new ArrayList<>(dtoList.size());
        for (SyncCashFlowDTO dto : dtoList) {
            SyncCashFlow entity = new SyncCashFlow();
            BeanUtils.copyProperties(dto, entity);
            entityList.add(entity);

            entity.setFlowId(null);
            String tsCode = tsCodeCache.get(dto.getSymbol());
            entity.setTsCode(StringUtils.isNotBlank(tsCode)? tsCode : dto.getSymbol());
            CommonObjectUtil.setValue4Create(entity);
        }

        return entityList;
    }

    /** 删除旧的数据, 如果存在的话 */
    private void deleteIfExists(List<SyncCashFlow> entityList) {
        if (CollectionUtils.isEmpty(entityList)) {
            return;
        }

        // 获取TS股票代码
        List<Map<String, Object>> mapList = new ArrayList<>();
        for (SyncCashFlow entity : entityList) {
            Map<String, Object> map = new HashMap<>();
            map.put("year", entity.getYear());
            map.put("quarter", entity.getQuarter());
            map.put("tsCode", entity.getTsCode());
            mapList.add(map);
        }

        CommonObjectUtil.batchDeleteBySize(mapList, 1000, list -> syncCashFlowMapper.batchDeleteByCode(list));
    }
}
