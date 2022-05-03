package com.jsonyao.mystock.common.lixingren;

import com.jsonyao.mystock.common.util.LixingrenExcelUtil;

import java.util.List;

/**
 * 理杏仁11字段 Excel处理程序
 *
 * @author yaocs2
 * @since 20220502
 */
public interface ElevenImportHandler<T> extends ImportHandler {

    /**
     * 处理所有excel
     *
     * @param elevenExcelList
     * @return
     */
    List<T> handle(List<LixingrenExcelUtil.ElevenExcel> elevenExcelList);
}
