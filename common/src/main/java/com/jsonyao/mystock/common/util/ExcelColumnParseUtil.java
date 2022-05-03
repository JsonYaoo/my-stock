package com.jsonyao.mystock.common.util;

import com.google.common.collect.Lists;
import com.jsonyao.mystock.common.annotation.ExcelColumn;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 自定义Excel注解类 解析工具类
 *
 * @author yaocs2
 * @since 20220502
 */
@Slf4j
public class ExcelColumnParseUtil {

    private static final Map<Class<?>, List<Field>> BEAN_DEFINITIONS_CACHE = new ConcurrentHashMap<>();

    /** 获取Excel Bean的beanDefinitions缓存 */
    private static <T> List<Field> getBeanDefinitions(Class<T> beanClass) {
        if (BEAN_DEFINITIONS_CACHE.containsKey(beanClass)) {
            return BEAN_DEFINITIONS_CACHE.get(beanClass);
        }

        PriorityQueue<Field> fieldQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o.getAnnotation(ExcelColumn.class).order()));
        for (Field declaredField : beanClass.getDeclaredFields()) {
            // 常量、静态变量, 则跳过赋值
            if (Modifier.isStatic(declaredField.getModifiers()) || Modifier.isFinal(declaredField.getModifiers())) {
                continue;
            }

            // 顺序添加@ExcelColumn列
            declaredField.setAccessible(true);
            if (declaredField.isAnnotationPresent(ExcelColumn.class)) {
                fieldQueue.add(declaredField);
            }
        }

        List<Field> beanDefinitions = Lists.newArrayList(fieldQueue.toArray(new Field[0]));
        BEAN_DEFINITIONS_CACHE.put(beanClass, beanDefinitions);
        return beanDefinitions;
    }

    /**
     * 解析dataList数据成List<Bean>
     *
     * @param dataList
     * @param beanClass
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> parseData2ListBean(List<LinkedHashMap<String, Object>> dataList, Class<T> beanClass) {
        if (dataList == null || dataList.isEmpty()) {
            return null;
        }

        // 遍历、解析每个结果集
        List<T> res = new ArrayList<>();
        for (LinkedHashMap<String, Object> dataItemMap : dataList) {
            res.add(parseData2Bean(dataItemMap, beanClass));
        }

        return res;
    }

    /**
     * 解析data数据成Bean
     *
     * @param dataItemMap
     * @param beanClass
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    private static <T> T parseData2Bean(LinkedHashMap<String, Object> dataItemMap, Class<T> beanClass) {
        assert beanClass != null;
        List<Field> beanDefinitions = getBeanDefinitions(beanClass);
        if(CollectionUtils.isEmpty(beanDefinitions)) {
            return null;
        }

        Assert.isTrue(dataItemMap.size() == beanDefinitions.size(),
                String.format("Bean %s @ExcelColumn配置不正确, 请重新配置! bean col size = %d, data size = %d",
                        beanClass.getName(), beanDefinitions.size(), dataItemMap.size()));

        try {
            Object obj = beanClass.newInstance();

            int index = 0;
            for (Map.Entry<String, Object> entry : dataItemMap.entrySet()) {
                Field field = beanDefinitions.get(index++);
                field.set(obj, getValueByType(field.getType(), entry.getValue()));
            }

            return (T) obj;
        } catch (Exception e) {
            log.error("parseData2Bean exception: ", e);
            return null;
        }
    }

    /** 根据类型设置值 */
    private static Object getValueByType(Class<?> type, Object value) {
        if (value == null) {
            return null;
        }

        // 先转换为字符串类型
        String strValue = String.valueOf(value);

        // String 类型
        if (type == String.class) {
            return strValue;
        }
        // Byte/byte 类型
        else if(StringUtils.isBlank(strValue)) {
            return null;
        }
        else if (type == Byte.class || type == byte.class) {
            return Byte.valueOf(strValue);
        }
        // Short/short 类型
        else if (type == Short.class || type == short.class) {
            return Short.valueOf(strValue);
        }
        // Integer/int 类型
        else if (type == Integer.class || type == int.class) {
            return Integer.valueOf(strValue);
        }
        // Long/long 类型
        else if (type == Long.class || type == long.class) {
            return Long.valueOf(strValue);
        }
        // Float/float 类型
        else if (type == Float.class || type == float.class) {
            return Float.valueOf(strValue);
        }
        // Double/double 类型
        else if (type == Double.class || type == double.class) {
            return Double.valueOf(strValue);
        }
        // Boolean/boolean 类型
        else if (type == Boolean.class || type == boolean.class) {
            return Boolean.valueOf(strValue);
        }
        // BigDecimal 类型
        else if (type == BigDecimal.class) {
            return new BigDecimal(strValue);
        }

        return null;
    }
}