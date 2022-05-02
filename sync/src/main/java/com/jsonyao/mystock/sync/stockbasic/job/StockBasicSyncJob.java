package com.jsonyao.mystock.sync.stockbasic.job;

import com.jsonyao.mystock.common.enums.TushareApiEnum;
import com.jsonyao.mystock.common.job.TushareBaseJob;
import com.jsonyao.mystock.sync.stockbasic.service.StockBasicSyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * 沪深股票_基础数据_股票列表_120 定时任务
 *
 * @author yaocs2
 * @since 20220502
 */
@Slf4j
@Component
public class StockBasicSyncJob implements TushareBaseJob {

    @Autowired
    private StockBasicSyncService stockBasicSyncService;

    /**
     * 定时同步沪深股票_基础数据_股票列表_120
     */
//	@Scheduled(cron = "0/1 * * * * ? ")
    public void doJob() {
        log.info("{} => 开始同步!", getDesc());

        StopWatch stopWatch = new StopWatch(getDesc());
        stopWatch.start();
        stockBasicSyncService.syncStockBasics(null);
        stopWatch.stop();

        log.info("{} <= 同步已结束! \n {}", getDesc(), stopWatch.prettyPrint());
    }

    @Override
    public String getDesc() {
        return TushareApiEnum.STOCK_BASIC.getName();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        doJob();
    }
}