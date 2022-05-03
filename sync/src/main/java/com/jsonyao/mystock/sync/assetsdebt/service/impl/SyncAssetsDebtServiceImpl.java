package com.jsonyao.mystock.sync.assetsdebt.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.jsonyao.mystock.common.util.CommonObjectUtil;
import com.jsonyao.mystock.sync.assetsdebt.dto.SyncAssetsDebtDTO;
import com.jsonyao.mystock.sync.assetsdebt.entity.SyncAssetsDebt;
import com.jsonyao.mystock.sync.assetsdebt.mapper.SyncAssetsDebtMapper;
import com.jsonyao.mystock.sync.assetsdebt.service.SyncAssetsDebtService;
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
 * 资产负债表 业务实现层
 *
 * @author yaocs2
 * @since 20220502
 */
@Service
@Slf4j
public class SyncAssetsDebtServiceImpl implements SyncAssetsDebtService {

    @Autowired
    private SyncStockBasicService syncStockBasicService;
    @Autowired
    private SyncAssetsDebtMapper syncAssetsDebtMapper;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
    @Override
    public void syncAssetsDebts(List<SyncAssetsDebtDTO> syncAssetsDebtDTOList) {
        log.info("同步资产负债表: syncAssetsDebtDTOList={}", JSONObject.toJSONString(syncAssetsDebtDTOList));

        // 构造Entity列表
        List<SyncAssetsDebt> entityList = buildSyncAssetsDebtList(syncAssetsDebtDTOList);

        // 删除旧的数据
        deleteIfExists(entityList);

        // 按照size进行分批插入
        CommonObjectUtil.batchInsertBySize(entityList, 1000, list -> syncAssetsDebtMapper.batchInsertSelective(list));
    }

    /** 构造Entity列表 */
    private List<SyncAssetsDebt> buildSyncAssetsDebtList(List<SyncAssetsDebtDTO> dtoList) {
        if(CollectionUtils.isEmpty(dtoList)) {
            return null;
        }

        // 查询所有股票代码-TS股票代码缓存
        Map<String, String> tsCodeCache = syncStockBasicService.queryTsCodeCache();

        // 构造Entity列表
        List<SyncAssetsDebt> entityList = new ArrayList<>(dtoList.size());
        for (SyncAssetsDebtDTO dto : dtoList) {
            SyncAssetsDebt entity = new SyncAssetsDebt();
            BeanUtils.copyProperties(dto, entity);
            entityList.add(entity);

            entity.setDebtId(null);
            String tsCode = tsCodeCache.get(dto.getSymbol());
            entity.setTsCode(StringUtils.isNotBlank(tsCode)? tsCode : dto.getSymbol());
            CommonObjectUtil.setValue4Create(entity);
        }

        return entityList;
    }

    /** 删除旧的数据, 如果存在的话 */
    private void deleteIfExists(List<SyncAssetsDebt> entityList) {
        if (CollectionUtils.isEmpty(entityList)) {
            return;
        }

        // 获取TS股票代码
        List<Map<String, Object>> mapList = new ArrayList<>();
        for (SyncAssetsDebt entity : entityList) {
            Map<String, Object> map = new HashMap<>();
            map.put("year", entity.getYear());
            map.put("quarter", entity.getQuarter());
            map.put("tsCode", entity.getTsCode());
            mapList.add(map);
        }

        CommonObjectUtil.batchDeleteBySize(mapList, 1000, list -> syncAssetsDebtMapper.batchDeleteByCode(list));
    }
}
