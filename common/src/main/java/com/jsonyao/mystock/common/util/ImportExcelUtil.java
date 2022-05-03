package com.jsonyao.mystock.common.util;

import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Excel导入 工具类
 *
 * @author yaocs2
 * @since 20220502
 */
public class ImportExcelUtil {

    protected final static Logger logger = LoggerFactory.getLogger(ImportExcelUtil.class);

    /** 总行数 */
    private static final ThreadLocal<Integer> totalRows = ThreadLocal.withInitial(() -> Integer.valueOf(0));
    /** 总列数 */
    private static final ThreadLocal<Integer> totalCells = ThreadLocal.withInitial(() -> Integer.valueOf(0));
    /** 错误信息 */
    private static final ThreadLocal<String> errorInfo = new ThreadLocal<>();
    /** 总行数 */
    private static int getTotalRows() { return totalRows.get(); }
    /** 总列数 */
    private static int getTotalCells() { return totalCells.get(); }
    /** 得到错误消息 */
    private static String getErrorInfo() { return errorInfo.get(); }

    /**
     * 获取总行数
     *
     * @param file     文件（如果文件已存在则从存在的文件中获取）
     * @param sheetNum sheet索引 从0开始
     * @return 总行数
     */
    private static Integer getTotalRows(MultipartFile file, int sheetNum) throws IOException {
        Workbook workbook = null;
        try {
            //获取工作薄 不存在则创建
            workbook = WorkbookFactory.create(file.getInputStream());
            //设置行数返回
            Sheet sheet = workbook.getSheetAt(sheetNum);
            Optional.of(sheet.getPhysicalNumberOfRows()).ifPresent(ImportExcelUtil.totalRows::set);
            return totalRows.get();
        } catch (IOException | InvalidFormatException e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (workbook != null) {
                workbook.close();
            }
        }
        return 0;
    }

    /**
     * 根据文件名读取excel文件
     *
     * @param file
     * @return
     */
    public static List<LinkedHashMap<String, Object>> importExcel(MultipartFile file) {
        Workbook workbook = null;
        try {
            //获取工作薄 不存在则创建
            workbook = WorkbookFactory.create(file.getInputStream());
            return importExcel(workbook, 0);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            /** 返回最后读取的结果 */
            return Collections.emptyList();
        } finally {
            if (workbook != null) {
                try {
                    workbook.close();
                } catch (IOException e) {

                }
            }
        }

    }

    /**
     * 读取数据
     *
     * @param wb
     * @return
     */
    public static List<LinkedHashMap<String, Object>> importExcel(Workbook wb, int sheetNum) {
        List<LinkedHashMap<String, Object>> dataLst = new ArrayList<>();
        /** 得到第一个shell */
        Sheet sheet = wb.getSheetAt(sheetNum);
        /** 得到Excel的行数 */
        totalRows.set(sheet.getPhysicalNumberOfRows());
        /** 得到Excel的列数 */
        if (totalRows.get() >= 1 && sheet.getRow(0) != null) {
            totalCells.set(sheet.getRow(0).getPhysicalNumberOfCells());
        }
        /** 得到列头列表 **/
        String[] head = new String[totalCells.get()];
        Row headRow = sheet.getRow(0);
        for (int i = 0; i < totalCells.get(); i++) {
            Cell cell = headRow.getCell(i);
            head[i] = getCellValue(cell);
        }

        /** 循环Excel的行 */
        for (int r = 1; r < totalRows.get(); r++) {
            headRow = sheet.getRow(r);
            if (null == headRow) { // 过滤空行
                sheet.shiftRows(r + 1, sheet.getLastRowNum(), -1);
                r--;
                continue;
            }
            int count = 0;
            for (int i = 0; i < totalCells.get(); i++) {
                Cell cell = headRow.getCell(i);
                String cellValue = getCellValue(cell);
                if (null == cellValue || "".equals(cellValue)) {
                    count++;
                    if (i == totalCells.get() - 1) {
                        break;
                    }
                }
            }
            if (count == totalCells.get()) {
                if (r + 1 > sheet.getLastRowNum()) {
                    break;
                }
                sheet.shiftRows(r + 1, sheet.getLastRowNum(), -1);
                r--;
                continue;

            }
            LinkedHashMap<String, Object> map = new LinkedHashMap<>();
            /** 循环Excel的列 */
            for (int c = 0; c < getTotalCells(); c++) {
                Cell cell = headRow.getCell(c);
                String cellValue = getCellValue(cell);
                map.put(head[c], cellValue);
            }
            /** 保存第r行的第c列 */
            dataLst.add(map);
        }
        return dataLst;
    }

    /**
     * 读取数据
     *
     * @param wb
     * @param sheetNum
     * @param rowNum   从第几行开始取-默认0
     * @return
     */
    public static List<LinkedHashMap<String, Object>> importExcel(Workbook wb, int sheetNum, int rowNum) {
        if (rowNum < 1) {
            rowNum = 1;
        }
        List<LinkedHashMap<String, Object>> dataLst = new ArrayList<>();
        /** 得到第一个shell */
        Sheet sheet = wb.getSheetAt(sheetNum);
        /** 得到Excel的行数 */
        totalRows.set(sheet.getPhysicalNumberOfRows());
        /** 得到Excel的列数 */
        if (totalRows.get() >= 1 && sheet.getRow(0) != null) {
            totalCells.set(sheet.getRow(0).getPhysicalNumberOfCells());
        }
        /** 得到列头列表 **/
        String[] head = new String[totalCells.get()];
        Row headRow = sheet.getRow(rowNum - 1);
        for (int i = 0; i < totalCells.get(); i++) {
            Cell cell = headRow.getCell(i);
            head[i] = getCellValue(cell);
        }

        /** 循环Excel的行 */
        for (int r = rowNum; r < totalRows.get(); r++) {
            headRow = sheet.getRow(r);
            if (null == headRow) { // 过滤空行
                sheet.shiftRows(r + 1, sheet.getLastRowNum(), -1);
                r--;
                continue;
            }
            int count = 0;
            for (int i = 0; i < totalCells.get(); i++) {
                Cell cell = headRow.getCell(i);
                String cellValue = getCellValue(cell);
                if (null == cellValue || "".equals(cellValue)) {
                    count++;
                    if (i == totalCells.get() - 1) {
                        break;
                    }
                }
            }
            if (count == totalCells.get()) {
                if (r + 1 > sheet.getLastRowNum()) {
                    break;
                }
                sheet.shiftRows(r + 1, sheet.getLastRowNum(), -1);
                r--;
                continue;

            }
            LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
            /** 循环Excel的列 */
            for (int c = 0; c < getTotalCells(); c++) {
                Cell cell = headRow.getCell(c);
                String cellValue = getCellValue(cell);
                map.put(head[c], cellValue);
            }
            /** 保存第r行的第c列 */
            dataLst.add(map);
        }
        return dataLst;
    }

    private static String getCellValue(Cell cell) {
        String cellValue = "";
        if (null != cell) {
            // 以下是判断数据的类型
            switch (cell.getCellTypeEnum()) {
                case NUMERIC: // 数字
                    cellValue = cell.getNumericCellValue() + "";
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {// 处理日期格式、时间格式
                        SimpleDateFormat sdf = null;
                        if (cell.getCellStyle().getDataFormat() == HSSFDataFormat.getBuiltinFormat("h:mm")) {
                            sdf = new SimpleDateFormat("HH:mm");
                        } else {// 日期
                            sdf = new SimpleDateFormat("yyyy-MM-dd");
                        }
                        Date date = cell.getDateCellValue();
                        cellValue = sdf.format(date);
                    } else if (cell.getCellStyle().getDataFormat() == 58) {
                        // 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        double value = cell.getNumericCellValue();
                        Date date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value);
                        cellValue = sdf.format(date);
                    } else {
                        double value = cell.getNumericCellValue();
                        CellStyle style = cell.getCellStyle();
                        DecimalFormat format = new DecimalFormat();
                        String temp = style.getDataFormatString();
                        // 单元格设置成常规
                        if (temp.equals("General")) {
                            format.applyPattern("#.##########");
                            cellValue = format.format(value);
                        } else if (temp.equals("000000")) {
                            format.applyPattern("000000");
                            cellValue = format.format(value);
                        } else {
                            DataFormatter formatter = new DataFormatter();
                            cellValue = formatter.formatCellValue(cell);
                        }
                    }
                    break;

                case STRING: // 字符串
                    cellValue = cell.getStringCellValue();
                    break;

                case BOOLEAN: // Boolean
                    cellValue = cell.getBooleanCellValue() + "";
                    break;

                case FORMULA: // 公式
                    //公式的话，获取公式里面的值
                    //cellValue = cell.getCellFormula() + "";
                    cellValue = ((XSSFCell) cell).getRawValue();
                    break;

                case BLANK: // 空值
                    cellValue = "";
                    break;

                case ERROR: // 故障
                    cellValue = "非法字符";
                    break;
                default:
                    cellValue = "未知类型";
                    break;
            }
        }
        return cellValue;
    }
}
