package com.jsonyao.mystock.common.job;

import org.springframework.beans.factory.InitializingBean;

/**
 * TuShare大数据平台API 定时任务基类
 *
 * @author yaocs2
 * @since 20220502
 */
public interface TushareBaseJob extends InitializingBean {

    /**
     * 获取定时任务描述
     *
     * @return
     */
    String getDesc();
}
