package com.jsonyao.mystock.sync.tradeCal.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.jsonyao.mystock.common.client.TushareClientProxy;
import com.jsonyao.mystock.common.enums.TushareApiEnum;
import com.jsonyao.mystock.common.util.CommonDateUtil;
import com.jsonyao.mystock.sync.tradeCal.service.TradeCalService;
import com.jsonyao.mystock.sync.tradeCal.tushare.TradeCalFields;
import com.jsonyao.mystock.sync.tradeCal.tushare.TradeCalParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

/**
 * 沪深股票_基础数据_股票列表_120 业务实现层
 *
 * @author yaocs2
 * @since 20220502
 */
@Service
@Slf4j
public class TradeCalServiceImpl implements TradeCalService {

    public static final TradeCalFields TRADE_CAL_FIELDS = new TradeCalFields();

    @Autowired
    private TushareClientProxy tushareClientProxy;
    
    @Override
    public Date queryPreTradeDate(Date date) {
        log.info("查询上一个交易日: date={}", JSONObject.toJSONString(date));
        Assert.notNull(date, "date不能为空!");

        // 设置查询参数
        TradeCalParams tradeCalParams = new TradeCalParams();
        tradeCalParams.setStart_date(CommonDateUtil.formatDate2yyyyMMdd(date));
        tradeCalParams.setEnd_date(CommonDateUtil.formatDate2yyyyMMdd(date));

        // 通用TuShare请求查询 => 沪深股票_基础数据_交易日历_免费
        List<TradeCalFields> oneTradeCalFields = tushareClientProxy.queryOn120(
                TushareApiEnum.TRADE_CAL, tradeCalParams, TRADE_CAL_FIELDS, TradeCalFields.class);
        if(CollectionUtils.isEmpty(oneTradeCalFields)) {
            log.info("同步沪深股票_基础数据_交易日历_免费 <= 同步不到任何数据, 本次作业已结束！");
            return null;
        }

        String pretrade_date = oneTradeCalFields.get(0).getPretrade_date();
        return CommonDateUtil.parseyyyyMMdd2Date(pretrade_date);
    }
}
