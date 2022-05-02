package com.jsonyao.mystock.common.tushare;

import java.lang.reflect.Field;

/**
 * TuShare大数据平台 Fields基类
 *
 * @author yaocs2
 * @since 20220502
 */
public class TushareBaseFields {

    /**
     * 获取"," 组装后的属性字符串
     *
     * @return
     */
    public String getFieldsJoinedStr() {
        StringBuilder res = new StringBuilder();
        for (Field field : this.getClass().getDeclaredFields()) {
            res.append(field.getName()).append(",");
        }
        return res.toString().substring(0, res.length() - 1);
    };
}
