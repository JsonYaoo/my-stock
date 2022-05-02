package com.jsonyao.mystock.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期 工具类
 *
 * @author yaocs2
 * @since 20220502
 */
@Slf4j
public class CommonDateUtil {

    public static final String DATE_FORMAT_yyyy_mm_dd = "yyyyMMdd";

    private static final SimpleDateFormat YYYY_MM_DD_FORMATER = new SimpleDateFormat(DATE_FORMAT_yyyy_mm_dd);

    /** 格式化date为yyyyMMdd字符串 */
    public static String formatDate2yyyyMMdd(Date date) {
        return YYYY_MM_DD_FORMATER.format(date);
    }

    /** 解析yyyyMMdd字符串成date */
    public static Date parseyyyyMMdd2Date(String dateStr) {
        if(StringUtils.isBlank(dateStr)) {
            return null;
        }

        try {
            return YYYY_MM_DD_FORMATER.parse(dateStr);
        } catch (Exception e) {
            log.error("获取当前 yyyyMMdd 格式日期失败, e=", e);
            return null;
        }
    }
}
