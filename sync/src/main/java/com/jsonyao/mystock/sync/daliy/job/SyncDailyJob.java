package com.jsonyao.mystock.sync.daliy.job;

import com.jsonyao.mystock.common.enums.TushareApiEnum;
import com.jsonyao.mystock.common.job.TushareBaseJob;
import com.jsonyao.mystock.common.util.CommonDateUtil;
import com.jsonyao.mystock.sync.daliy.service.SyncDailyService;
import com.jsonyao.mystock.sync.daliy.tushare.DailyParams;
import com.jsonyao.mystock.sync.tradeCal.service.TradeCalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.Date;

/**
 * 沪深股票_行情数据_A股日线行情_120 定时任务
 *
 * @author yaocs2
 * @since 20220502
 */
@Slf4j
@Component
public class SyncDailyJob implements TushareBaseJob {

    @Autowired
    private SyncDailyService syncDailyService;
    @Autowired
    private TradeCalService tradeCalService;

    /**
     * 定时同步 沪深股票_行情数据_A股日线行情_120
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

        // 上一个交易日
        Date now = new Date();
        Date preTradeDate = tradeCalService.queryPreTradeDate(now);

        // 更新数据至上一个交易日
        DailyParams dailyParams = new DailyParams();
        dailyParams.setTrade_date(CommonDateUtil.formatDate2yyyyMMdd(preTradeDate));
        syncDailyService.syncDailys(dailyParams);

        stopWatch.stop();
        log.info("{} <= 同步已结束! \n {}", getDesc(), stopWatch.prettyPrint());
    }

    @Override
    public String getDesc() {
        return TushareApiEnum.DAILY.getName();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
//        doJob();
    }
}