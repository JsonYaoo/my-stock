package com.jsonyao.mystock.common.job;

/**
 * 理性仁 定时任务基类
 *
 * @author yaocs2
 * @since 20220502
 */
public interface LixingrenBaseJob extends BaseJob {

    /**
     * 获取定时任务描述
     *
     * @return
     */
    String getDesc();
}
