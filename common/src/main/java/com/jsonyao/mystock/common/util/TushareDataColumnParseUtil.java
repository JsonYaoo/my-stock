package com.jsonyao.mystock.common.util;

import com.alibaba.fastjson.JSONArray;
import com.jsonyao.mystock.common.annotation.TushareDataColumn;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 自定义Tushare结果注解 解析工具类
 *
 * @author yaocs2
 * @since 20220502
 */
@Slf4j
public class TushareDataColumnParseUtil {

    /**
     * 解析data数据成List<Bean>
     *
     * @param fields
     * @param items
     * @param beanClass
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> parseData2ListBean(JSONArray fields, List<JSONArray> items, Class<T> beanClass) {
        assert beanClass != null;
        if (fields == null || fields.isEmpty() || items == null || items.isEmpty()) {
            return null;
        }

        // 建立fields名称索引映射
        Map<String, Integer> nameIndexMap = new HashMap<>();
        for (int i = 0; i < fields.size(); i++) {
            nameIndexMap.put(String.valueOf(fields.get(i)), i);
        }

        // 遍历、解析每个结果集
        List<T> res = new ArrayList<>();
        for (JSONArray dataItem : items) {
            res.add(parseData2Bean(nameIndexMap, dataItem, beanClass));
        }

        return res;
    }

    /**
     * 解析data数据成Bean
     *
     * @param nameIndexMap
     * @param items
     * @param beanClass
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    private static <T> T parseData2Bean(final Map<String, Integer> nameIndexMap, final JSONArray items, final Class<T> beanClass) {
        try {
            Object obj = beanClass.newInstance();
            for (Field declaredField : beanClass.getDeclaredFields()) {
                // 常量、静态变量, 则跳过赋值
                if (Modifier.isStatic(declaredField.getModifiers()) || Modifier.isFinal(declaredField.getModifiers())) {
                    continue;
                }

                // 设置@TushareDataColumn列值
                declaredField.setAccessible(true);
                if (declaredField.isAnnotationPresent(TushareDataColumn.class)) {
                    TushareDataColumn annotation = declaredField.getAnnotation(TushareDataColumn.class);
                    Class<?> type = annotation.type() != void.class ? annotation.type() : declaredField.getType();
                    String name = StringUtils.isNoneBlank(annotation.name()) ? annotation.name() : declaredField.getName();
                    declaredField.set(obj, getValueByType(items, type, nameIndexMap.get(name)));
                }
            }

            return (T) obj;
        } catch (Exception e) {
            log.error("parseData2Bean exception: ", e);
            return null;
        }
    }

    /**
     * 根据类型设置值
     */
    private static Object getValueByType(JSONArray items, Class<?> type, Integer index) {
        if (index == null) {
            return null;
        }

        // String 类型
        if (type == String.class) {
            return items.getString(index);
        }
        // Byte 类型
        else if (type == Byte.class) {
            return items.getByte(index);
        }
        // byte 类型
        else if (type == byte.class) {
            return items.getByteValue(index);
        }
        // Short 类型
        else if (type == Short.class) {
            return items.getShort(index);
        }
        // short 类型
        else if (type == short.class) {
            return items.getShortValue(index);
        }
        // Integer 类型
        else if (type == Integer.class) {
            return items.getInteger(index);
        }
        // int 类型
        else if (type == int.class) {
            return items.getIntValue(index);
        }
        // Long 类型
        else if (type == Long.class) {
            return items.getLong(index);
        }
        // long 类型
        else if (type == long.class) {
            return items.getLongValue(index);
        }
        // Float 类型
        else if (type == Float.class) {
            return items.getFloat(index);
        }
        // float 类型
        else if (type == float.class) {
            return items.getFloatValue(index);
        }
        // Double 类型
        else if (type == Double.class) {
            return items.getDouble(index);
        }
        // double 类型
        else if (type == double.class) {
            return items.getDoubleValue(index);
        }
        // Boolean 类型
        else if (type == Boolean.class) {
            return items.getBoolean(index);
        }
        // boolean 类型
        else if (type == boolean.class) {
            return items.getBooleanValue(index);
        }
        // BigDecimal 类型
        else if (type == BigDecimal.class) {
            return items.getBigDecimal(index);
        }

        return null;
    }
}