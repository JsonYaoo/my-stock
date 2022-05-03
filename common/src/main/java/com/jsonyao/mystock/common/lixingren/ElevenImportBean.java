package com.jsonyao.mystock.common.lixingren;

import com.jsonyao.mystock.common.annotation.ExcelColumn;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.math.BigDecimal;

/**
 * 理性人11字段 导入通用Bean
 *
 * @author yaocs2
 * @since 20220503
 */
@Data
@Slf4j
public class ElevenImportBean {

    public static final Integer CUR_INIT_YEAR = 2022;
    public static final String FILED_PROTOTYPE_NAME = "filed";

    /**
     * 交易所代码
     */
    @ExcelColumn(order = 0)
    private String exchange;

    /**
     * 股票代码
     */
    @ExcelColumn(order = 1)
    private String symbol;

    /**
     * 股票名称
     */
    @ExcelColumn(order = 2)
    private String stockName;

    /**
     * 行业
     */
    @ExcelColumn(order = 3)
    private String industry;

    /**
     * 字段1
     */
    @ExcelColumn(order = 4)
    private BigDecimal filed0;

    /**
     * 字段2
     */
    @ExcelColumn(order = 5)
    private BigDecimal filed1;

    /**
     * 字段3
     */
    @ExcelColumn(order = 6)
    private BigDecimal filed2;

    /**
     * 字段4
     */
    @ExcelColumn(order = 7)
    private BigDecimal filed3;

    /**
     * 字段5
     */
    @ExcelColumn(order = 8)
    private BigDecimal filed4;

    /**
     * 字段6
     */
    @ExcelColumn(order = 9)
    private BigDecimal filed5;

    /**
     * 字段7
     */
    @ExcelColumn(order = 10)
    private BigDecimal filed6;

    /**
     * 字段8
     */
    @ExcelColumn(order = 11)
    private BigDecimal filed7;

    /**
     * 字段9
     */
    @ExcelColumn(order = 12)
    private BigDecimal filed8;

    /**
     * 字段10
     */
    @ExcelColumn(order = 13)
    private BigDecimal filed9;

    /**
     * 字段11
     */
    @ExcelColumn(order = 14)
    private BigDecimal filed10;

    /**
     * 字段1
     */
    @ExcelColumn(order = 15)
    private String attention;

    /**
     * 理性人url
     */
    @ExcelColumn(order = 16)
    private String url;

    /**
     * 根据年份, 获取字段的值
     *
     * @param year
     * @return
     */
    public Object getValueByYear(Integer year) {
        String filedName = FILED_PROTOTYPE_NAME + (CUR_INIT_YEAR - year);
        try {
            Field field = this.getClass().getDeclaredField(filedName);
            field.setAccessible(true);
            return field.get(this);
        } catch (Exception e) {
            log.error("根据年份, 获取"+ filedName +"字段的值异常! e=", e);
            return null;
        }
    }
}