package com.jsonyao.mystock.sync.stockcompany.job;

import com.jsonyao.mystock.common.enums.TushareApiEnum;
import com.jsonyao.mystock.common.job.TushareBaseJob;
import com.jsonyao.mystock.sync.stockbasic.service.SyncStockBasicService;
import com.jsonyao.mystock.sync.stockcompany.service.SyncStockCompanyService;
import com.jsonyao.mystock.sync.stockcompany.tushare.StockCompanyParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * 沪深股票_基础数据_上市公司基本信息_120 定时任务
 *
 * @author yaocs2
 * @since 20220502
 */
@Slf4j
@Component
public class SyncStockCompanyJob implements TushareBaseJob {

    @Autowired
    private SyncStockBasicService syncStockBasicService;
    @Autowired
    private SyncStockCompanyService syncStockCompanyService;

    /**
     * 定时同步沪深股票_基础数据_股票列表_120
     *
     * 上市公司季报披露时间：
     * 　　1季报：每年4月1日——4月30日
     * 　　2季报（中报）：每年7月1日——8月30日
     * 　　3季报： 每年10月1日——10月31日
     * 　　4季报 （年报）：每年1月1日——4月30日
     *
     * 同步时间：
     *    5月1日 06:00:00、9月1日 06:00:00、11月1日 06:00:00
     */
    @Scheduled(cron = "0 0 6 1 5,9,11 ?")
    public void doJob() {
        log.info("{} => 开始同步!", getDesc());
        StopWatch stopWatch = new StopWatch(getDesc());
        stopWatch.start();

        // SSE 上交所
        StockCompanyParams stockCompanyParams = new StockCompanyParams();
        stockCompanyParams.setExchange("SSE");
        syncStockCompanyService.syncStockCompany(stockCompanyParams);

        // SZSE 深交所
        stockCompanyParams.setExchange("SZSE");
        syncStockCompanyService.syncStockCompany(stockCompanyParams);

        stopWatch.stop();
        log.info("{} <= 同步已结束! \n {}", getDesc(), stopWatch.prettyPrint());
    }

    @Override
    public String getDesc() {
        return TushareApiEnum.STOCK_COMPANY.getName();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
//        doJob();
    }
}