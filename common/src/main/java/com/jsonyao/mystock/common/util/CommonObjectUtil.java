package com.jsonyao.mystock.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

/**
 * 对象操作 工具类
 *
 * @author yaocs2
 * @since 20220502
 */
@Slf4j
public class CommonObjectUtil {

    /**
     * 为插入记录设置属性
     *
     * @param o
     */
    public static void setValue4Create(Object o) {
        if (o == null) {
            return;
        }

        Date now = new Date();
        Class<?> oClass = o.getClass();

        // 创建时间
        try {
            Field creationDate = oClass.getDeclaredField("creationDate");
            if(creationDate != null) {
                creationDate.setAccessible(true);
                creationDate.set(o, now);
            }
        } catch (Exception e) {
            log.error("设置创建时间失败! e=", e);
        }

        // 最后更新时间
        try {
            Field lastUpdateDate = oClass.getDeclaredField("lastUpdateDate");
            if(lastUpdateDate != null) {
                lastUpdateDate.setAccessible(true);
                lastUpdateDate.set(o, now);
            }
        } catch (Exception e) {
            log.error("设置创建时间失败! e=", e);
        }
    }

    /**
     * 为更新记录设置属性
     *
     * @param o
     */
    public static void setValue4Update(Object o) {
        if (o == null) {
            return;
        }

        Date now = new Date();
        Class<?> oClass = o.getClass();

        // 最后更新时间
        try {
            Field lastUpdateDate = oClass.getDeclaredField("lastUpdateDate");
            if(lastUpdateDate != null) {
                lastUpdateDate.setAccessible(true);
                lastUpdateDate.set(o, now);
            }
        } catch (Exception e) {
            log.error("设置创建时间失败! e=", e);
        }
    }

    /**
     * 按照size进行分批删除
     *
     * @param mapList
     * @param size
     * @param consumer
     * @param <T>
     */
    public static <T> void batchDeleteBySize(List<T> mapList, int size, Consumer<List<T>> consumer) {
        if (CollectionUtils.isEmpty(mapList)) {
            return;
        }

        // 获取分批组数
        int batchCount;
        int remaining = mapList.size() % size;
        if (remaining == 0) {
            batchCount = mapList.size() / size;
        } else {
            batchCount = mapList.size() / size + 1;
        }

        // 分批插入
        List<T> list;
        for (int i = 0; i < batchCount; i++) {
            // 最后一批
            if (i == batchCount - 1) {
                list = mapList.subList(size * i, mapList.size());
            }
            // 非最后一批
            else {
                list = mapList.subList(size * i, size * i + size);
            }

            // 批量插入
            consumer.accept(list);
        }
    }

    /**
     * 按照size进行分批插入
     *
     * @param entityList
     * @param size
     * @param consumer
     * @param <T>
     */
    public static <T> void batchInsertBySize(List<T> entityList, int size, Consumer<List<T>> consumer) {
        if (CollectionUtils.isEmpty(entityList)) {
            return;
        }

        // 获取分批组数
        int batchCount;
        int remaining = entityList.size() % size;
        if (remaining == 0) {
            batchCount = entityList.size() / size;
        } else {
            batchCount = entityList.size() / size + 1;
        }

        // 分批插入
        List<T> list;
        for (int i = 0; i < batchCount; i++) {
            // 最后一批
            if (i == batchCount - 1) {
                list = entityList.subList(size * i, entityList.size());
            }
            // 非最后一批
            else {
                list = entityList.subList(size * i, size * i + size);
            }

            // 批量插入
            consumer.accept(list);
        }
    }
}
