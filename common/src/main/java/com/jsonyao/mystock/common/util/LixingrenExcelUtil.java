package com.jsonyao.mystock.common.util;

import com.jsonyao.mystock.common.enums.LixingrenFiledEnum;
import com.jsonyao.mystock.common.enums.QuarterEnum;
import com.jsonyao.mystock.common.lixingren.ElevenImportBean;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 理杏仁Excel扫描工具
 *
 * @author yaocs2
 * @since 20220503
 */
@Slf4j
public class LixingrenExcelUtil {

    public static final String EXCEL_PATH = File.separator + "lixingren";
    public static final String ELEVEN_EXCEL_NAME_TEMPLATE = "沪深_%s_连续10年%s季度累加和_20220501.xlsx";

    @Getter
    public static class ElevenExcel {
        private Integer initYear;
        private Integer quarter;
        private String tableName;
        private String filedDesc;
        private Workbook workbook;

        public ElevenExcel(Integer initYear, Integer quarter, String tableName, String filedDesc, Workbook workbook) {
            this.initYear = initYear;
            this.quarter = quarter;
            this.tableName = tableName;
            this.filedDesc = filedDesc;
            this.workbook = workbook;
        }
    }

    /**
     * 通过表名, 扫描11字段格式的Excel
     *
     * @param tableName
     * @return
     */
    public static List<ElevenExcel> scanElevenExcelByTable(String tableName) {
        List<LixingrenFiledEnum> filedEnumList = LixingrenFiledEnum.getEnumsByTable(tableName);
        if (CollectionUtils.isEmpty(filedEnumList)) {
            return null;
        }

        // 遍历tableName下所有的Workbook
        List<ElevenExcel> elevenExcelList = new ArrayList<>();
        for (LixingrenFiledEnum fe : filedEnumList) {
            for (QuarterEnum qe : QuarterEnum.values()) {
                ElevenExcel elevenExcel = scanElevenExcelByTableFiled(tableName, fe.getDesc(), qe);
                if(elevenExcel != null) {
                    elevenExcelList.add(elevenExcel);
                }
            }
        }

        return elevenExcelList;
    }

    /**
     * 通过字段名+季度, 扫描11字段格式的Excel
     *
     * @param filedName
     * @param qe
     * @return
     */
    public static ElevenExcel scanElevenExcelByTableFiled(String tableName, String filedName, QuarterEnum qe) {
        if (StringUtils.isBlank(filedName) || qe == null) {
            return null;
        }

        try {
            Workbook workbook = getWorkbookByName(EXCEL_PATH + File.separator + String.format(ELEVEN_EXCEL_NAME_TEMPLATE, filedName, qe.getShortName()));
            return new ElevenExcel(ElevenImportBean.CUR_INIT_YEAR, qe.getValue(), tableName, filedName, workbook);
        } catch (Exception e) {
            log.error("通过字段名+季度, 扫描11字段格式的Excel异常! e=", e);
            return null;
        }
    }

    /**
     * 关闭所有Workbook
     *
     * @param elevenExcelList
     */
    public static void closeAllWorkbooks(List<ElevenExcel> elevenExcelList) {
        if (CollectionUtils.isEmpty(elevenExcelList)) {
            return;
        }

        for (ElevenExcel elevenExcel : elevenExcelList) {
            if(elevenExcel.getWorkbook() != null) {
                try {
                    closeWorkbook(elevenExcel.getWorkbook());
                } catch (IOException e) {
                    log.error("关闭workbook失败! e=", e);
                }
            }
        }
    }

    /**
     * 根据路径名称, 获取Excel
     */
    private static Workbook getWorkbookByName(String fullName) throws IOException, InvalidFormatException {
        InputStream is = LixingrenExcelUtil.class.getClassLoader().getResourceAsStream(fullName);
        return WorkbookFactory.create(is);
    }

    /**
     * 关闭workbook
     */
    private static void closeWorkbook(Workbook workbook) throws IOException {
        workbook.close();
    }
}
