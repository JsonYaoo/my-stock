package com.jsonyao.mystock.sync.cashflow.handler;

import com.google.common.collect.Lists;
import com.jsonyao.mystock.common.enums.LixingrenFiledEnum;
import com.jsonyao.mystock.common.lixingren.ElevenImportBean;
import com.jsonyao.mystock.common.lixingren.ElevenImportHandler;
import com.jsonyao.mystock.common.util.ExcelColumnParseUtil;
import com.jsonyao.mystock.common.util.ImportExcelUtil;
import com.jsonyao.mystock.common.util.LixingrenExcelUtil;
import com.jsonyao.mystock.sync.cashflow.dto.SyncCashFlowDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 现金流量表 理杏仁11字段 Excel处理程序
 *
 * @author yaocs2
 * @since 20220502
 */
@Slf4j
public class CashFlowElevenImportHandler implements ElevenImportHandler<SyncCashFlowDTO> {

    private static final String SPLITTER = "#$#";

    /** 季度+股票代码缓存 */
    private static ThreadLocal<Map<String, SyncCashFlowDTO>> QUA_CODE_CACHE = ThreadLocal.withInitial(HashMap::new);

    @Override
    public List<SyncCashFlowDTO> handle(List<LixingrenExcelUtil.ElevenExcel> elevenExcelList) {
        log.info("处理所有现金流量表所有的excel~");
        try {
            if(CollectionUtils.isEmpty(elevenExcelList)) {
                return null;
            }

            for (LixingrenExcelUtil.ElevenExcel elevenExcel : elevenExcelList) {
                if(elevenExcel.getWorkbook() == null) {
                    continue;
                }

                // 解析Excel得到Bean对象
                List<LinkedHashMap<String, Object>> mapList = ImportExcelUtil.importExcel(elevenExcel.getWorkbook(), 0);

                // 解析dataList数据成List<Bean>
                List<ElevenImportBean> beanList = ExcelColumnParseUtil.parseData2ListBean(mapList, ElevenImportBean.class);

                // 处理现金流量表的Bean
                doHandle(elevenExcel.getInitYear(), elevenExcel.getQuarter(), elevenExcel.getFiledDesc(), beanList);
            }

            // 返回更新好的缓存数
            Map<String, SyncCashFlowDTO> cacheMap = QUA_CODE_CACHE.get();
            if(!CollectionUtils.isEmpty(cacheMap)) {
                return Lists.newArrayList(cacheMap.values());
            }
        } finally {
            // 清空缓存
            QUA_CODE_CACHE.remove();
        }

        return null;
    }


    /** 处理现金流量表的Bean */
    private static void doHandle(Integer year, Integer quarter, String filedDesc, List<ElevenImportBean> beanList) {
        if(year == null || quarter == null || StringUtils.isBlank(filedDesc) || CollectionUtils.isEmpty(beanList)) {
            return;
        }

        // 通过字段描述, 获取对应的枚举类
        LixingrenFiledEnum filedEnum = LixingrenFiledEnum.getEnumsByDesc(filedDesc);
        if(filedEnum == null) {
            return;
        }

        // 更新缓存数据
        for (int i = 0; i < 11; i++) {
            log.info("处理现金流量表的Bean: year={}, quarter={}, filedDesc={}", year, quarter, filedDesc);

            for (ElevenImportBean bean : beanList) {
                if(StringUtils.isBlank(bean.getSymbol())) {
                    continue;
                }

                String key = year + SPLITTER + quarter + SPLITTER + bean.getSymbol();
                if(!QUA_CODE_CACHE.get().containsKey(key)) {
                    SyncCashFlowDTO initDto = new SyncCashFlowDTO();
                    initDto.setYear(year);
                    initDto.setQuarter(quarter);
                    initDto.setSymbol(bean.getSymbol());
                    initDto.setStockName(bean.getStockName());
                    initDto.setIndustry(bean.getIndustry());
                    initDto.setQuarter(quarter);
                    QUA_CODE_CACHE.get().put(key, initDto);
                }

                // 根据字段描述, 设置BigDecimal字段的值
                SyncCashFlowDTO dto = QUA_CODE_CACHE.get().get(key);
                dto.setBigDecimalField(year, filedEnum.getValue(), bean);
            }

            // 继续往前找剩余的年份
            year--;
        }
    }
}
