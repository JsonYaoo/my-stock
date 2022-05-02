package com.jsonyao.mystock.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义Tushare结果注解类
 *
 * @author yaocs2
 * @since 20220502
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface TushareDataColumn {

    /**
     * Tushare data名称
     *
     * @return
     */
    String name() default "";

    /**
     * 列类型
     *
     * @return
     */
    Class<?> type() default void.class;
}
