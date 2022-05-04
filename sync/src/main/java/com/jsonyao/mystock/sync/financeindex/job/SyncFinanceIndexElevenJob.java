package com.jsonyao.mystock.sync.financeindex.job;

import com.jsonyao.mystock.common.enums.LixingrenTableEnum;
import com.jsonyao.mystock.common.job.LixingrenBaseJob;
import com.jsonyao.mystock.common.util.LixingrenExcelUtil;
import com.jsonyao.mystock.sync.financeindex.dto.SyncFinanceIndexDTO;
import com.jsonyao.mystock.sync.financeindex.handler.FinanceIndexElevenImportHandler;
import com.jsonyao.mystock.sync.financeindex.service.SyncFinanceIndexService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.List;

/**
 * 财务指标表(11字段)初始化任务
 *
 * @author yaocs2
 * @since 20220502
 */
@Slf4j
@Component
public class SyncFinanceIndexElevenJob implements LixingrenBaseJob {

    @Autowired
    private SyncFinanceIndexService syncFinanceIndexService;

    public void doJob() {
        log.info("{} => 开始同步!", getDesc());
        StopWatch stopWatch = new StopWatch(getDesc());
        stopWatch.start();

        // 扫描所有财务指标表的excel
        List<SyncFinanceIndexDTO> SyncFinanceIndexDTOList = scanElevenExcelByTable(LixingrenTableEnum.SYNC_FINANCE_INDEX.getValue());

        // 同步财务指标表
        syncFinanceIndexService.syncFinanceIndexs(SyncFinanceIndexDTOList);

        stopWatch.stop();
        log.info("{} <= 同步已结束! \n {}", getDesc(), stopWatch.prettyPrint());
    }

    /** 扫描所有财务指标表的excel */
    private List<SyncFinanceIndexDTO> scanElevenExcelByTable(String tableName) {
        log.info("扫描所有财务指标表的excel: tableName={}", tableName);
        if(StringUtils.isBlank(tableName)) {
            return null;
        }

        List<LixingrenExcelUtil.ElevenExcel> elevenExcelList = LixingrenExcelUtil.scanElevenExcelByTable(tableName);
        try {
            FinanceIndexElevenImportHandler handler = new FinanceIndexElevenImportHandler();
            return handler.handle(elevenExcelList);
        } finally {
            LixingrenExcelUtil.closeAllWorkbooks(elevenExcelList);
        }
    }

    @Override
    public String getDesc() {
        return LixingrenTableEnum.SYNC_FINANCE_INDEX.getName() + "(11字段)";
    }

    @Override
    public void afterPropertiesSet() throws Exception {
//        doJob();
    }
}