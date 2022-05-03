package com.jsonyao.mystock.sync.assetsdebt.job;

import com.jsonyao.mystock.common.enums.LixingrenTableEnum;
import com.jsonyao.mystock.common.job.LixingrenBaseJob;
import com.jsonyao.mystock.common.util.LixingrenExcelUtil;
import com.jsonyao.mystock.sync.assetsdebt.dto.SyncAssetsDebtDTO;
import com.jsonyao.mystock.sync.assetsdebt.handler.AssetsDebtElevenImportHandler;
import com.jsonyao.mystock.sync.assetsdebt.service.SyncAssetsDebtService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.List;

/**
 * 资产负债表(11字段)初始化任务
 *
 * @author yaocs2
 * @since 20220502
 */
@Slf4j
@Component
public class SyncAssetsDebtElevenJob implements LixingrenBaseJob {

    @Autowired
    private SyncAssetsDebtService syncAssetsDebtService;

    public void doJob() {
        log.info("{} => 开始同步!", getDesc());
        StopWatch stopWatch = new StopWatch(getDesc());
        stopWatch.start();

        // 扫描所有资产负债表的excel
        List<SyncAssetsDebtDTO> syncAssetsDebtDTOList = scanElevenExcelByTable(LixingrenTableEnum.SYNC_ASSETS_DEBT.getValue());

        // 同步资产负债表
        syncAssetsDebtService.syncAssetsDebts(syncAssetsDebtDTOList);

        stopWatch.stop();
        log.info("{} <= 同步已结束! \n {}", getDesc(), stopWatch.prettyPrint());
    }

    /** 扫描所有资产负债表的excel */
    private List<SyncAssetsDebtDTO> scanElevenExcelByTable(String tableName) {
        log.info("扫描所有资产负债表的excel: tableName={}", tableName);
        if(StringUtils.isBlank(tableName)) {
            return null;
        }

        List<LixingrenExcelUtil.ElevenExcel> elevenExcelList = LixingrenExcelUtil.scanElevenExcelByTable(tableName);
        try {
            AssetsDebtElevenImportHandler handler = new AssetsDebtElevenImportHandler();
            return handler.handle(elevenExcelList);
        } finally {
            LixingrenExcelUtil.closeAllWorkbooks(elevenExcelList);
        }
    }

    @Override
    public String getDesc() {
        return LixingrenTableEnum.SYNC_ASSETS_DEBT.getName() + "(11字段)";
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        doJob();
    }
}