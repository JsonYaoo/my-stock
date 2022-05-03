package com.jsonyao.mystock.sync.bakbasic.job;

import com.jsonyao.mystock.common.enums.TushareApiEnum;
import com.jsonyao.mystock.common.job.TushareBaseJob;
import com.jsonyao.mystock.common.util.CommonDateUtil;
import com.jsonyao.mystock.sync.bakbasic.service.SyncBakBasicService;
import com.jsonyao.mystock.sync.bakbasic.tushare.BakBasicParams;
import com.jsonyao.mystock.sync.tradeCal.service.TradeCalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.Date;

/**
 * 沪深股票_基础数据_备用列表_5000 定时任务
 *
 * @author yaocs2
 * @since 20220502
 */
@Slf4j
@Component
public class SyncBakBasicJob implements TushareBaseJob {

    @Autowired
    private SyncBakBasicService syncBakBasicService;
    @Autowired
    private TradeCalService tradeCalService;

    /**
     * 定时沪深股票_基础数据_备用列表_5000
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
        BakBasicParams bakBasicParams = new BakBasicParams();
        bakBasicParams.setTrade_date(CommonDateUtil.formatDate2yyyyMMdd(preTradeDate));
        syncBakBasicService.syncBakBasics(bakBasicParams);

        stopWatch.stop();
        log.info("{} <= 同步已结束! \n {}", getDesc(), stopWatch.prettyPrint());
    }

    @Override
    public String getDesc() {
        return TushareApiEnum.BAK_BASIC.getName();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        doJob();
    }
}