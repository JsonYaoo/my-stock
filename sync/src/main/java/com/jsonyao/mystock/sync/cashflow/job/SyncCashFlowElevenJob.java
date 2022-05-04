package com.jsonyao.mystock.sync.cashflow.job;

import com.jsonyao.mystock.common.enums.LixingrenTableEnum;
import com.jsonyao.mystock.common.job.LixingrenBaseJob;
import com.jsonyao.mystock.common.util.LixingrenExcelUtil;
import com.jsonyao.mystock.sync.cashflow.dto.SyncCashFlowDTO;
import com.jsonyao.mystock.sync.cashflow.handler.CashFlowElevenImportHandler;
import com.jsonyao.mystock.sync.cashflow.service.SyncCashFlowService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.List;

/**
 * 现金流量表(11字段)初始化任务
 *
 * @author yaocs2
 * @since 20220502
 */
@Slf4j
@Component
public class SyncCashFlowElevenJob implements LixingrenBaseJob {

    @Autowired
    private SyncCashFlowService syncCashFlowService;

    public void doJob() {
        log.info("{} => 开始同步!", getDesc());
        StopWatch stopWatch = new StopWatch(getDesc());
        stopWatch.start();

        // 扫描所有现金流量表的excel
        List<SyncCashFlowDTO> SyncCashFlowDTOList = scanElevenExcelByTable(LixingrenTableEnum.SYNC_CASH_FLOW.getValue());

        // 同步现金流量表
        syncCashFlowService.syncCashFlows(SyncCashFlowDTOList);

        stopWatch.stop();
        log.info("{} <= 同步已结束! \n {}", getDesc(), stopWatch.prettyPrint());
    }

    /** 扫描所有现金流量表的excel */
    private List<SyncCashFlowDTO> scanElevenExcelByTable(String tableName) {
        log.info("扫描所有现金流量表的excel: tableName={}", tableName);
        if(StringUtils.isBlank(tableName)) {
            return null;
        }

        List<LixingrenExcelUtil.ElevenExcel> elevenExcelList = LixingrenExcelUtil.scanElevenExcelByTable(tableName);
        try {
            CashFlowElevenImportHandler handler = new CashFlowElevenImportHandler();
            return handler.handle(elevenExcelList);
        } finally {
            LixingrenExcelUtil.closeAllWorkbooks(elevenExcelList);
        }
    }

    @Override
    public String getDesc() {
        return LixingrenTableEnum.SYNC_CASH_FLOW.getName() + "(11字段)";
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        doJob();
    }
}