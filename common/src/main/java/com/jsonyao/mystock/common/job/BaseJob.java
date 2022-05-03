package com.jsonyao.mystock.common.job;

import org.springframework.beans.factory.InitializingBean;

/**
 * 定时任务基类
 *
 * @author yaocs2
 * @since 20220502
 */
public interface BaseJob extends InitializingBean {

    /**
     * 获取定时任务描述
     *
     * @return
     */
    String getDesc();
}
