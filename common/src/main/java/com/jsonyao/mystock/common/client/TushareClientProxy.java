package com.jsonyao.mystock.common.client;

import com.alibaba.fastjson.JSONObject;
import com.jsonyao.mystock.common.constant.TushareConstant;
import com.jsonyao.mystock.common.enums.TushareApiEnum;
import com.jsonyao.mystock.common.tushare.TushareBaseFields;
import com.jsonyao.mystock.common.tushare.TushareBaseParams;
import com.jsonyao.mystock.common.tushare.TushareBaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.List;

/**
 * TuShare大数据平台 客户端请求代理增强类
 *
 * @author yaocs2
 * @since 20220502
 */
@Slf4j
@Component
public class TushareClientProxy {

    @Autowired
    private TushareClient tushareClient;

    /**
     * 增强式TuShare请求查询120积分接口
     *
     * @param apiEnum
     * @param params
     * @param fields
     * @param tClass
     * @param <T>
     * @return
     */
    public <T> List<T> queryOn120(TushareApiEnum apiEnum, TushareBaseParams params, TushareBaseFields fields, Class<T> tClass) {
        return doQuery(apiEnum, TushareConstant.TOKEN_VALUE_120, params, fields, tClass);
    }

    /**
     * 增强式TuShare请求查询
     *
     * @param apiEnum
     * @param token
     * @param params
     * @param fields
     * @param tClass
     * @param <T>
     * @return
     */
    private <T> List<T> doQuery(TushareApiEnum apiEnum, String token, TushareBaseParams params, TushareBaseFields fields, Class<T> tClass) {
        Assert.notNull(apiEnum, "apiEnum不能为空!");
        Assert.hasText(token, "token不能为空!");
        Assert.notNull(tClass, "tClass不能为空!");

        log.info("增强式TuShare请求查询: apiName={}, token={}, params={}, fields={}, tClass={}",
                JSONObject.toJSONString(apiEnum.getName()), token, JSONObject.toJSONString(params), JSONObject.toJSONString(fields), tClass.getName());

        // 通用TuShare请求查询: 缺少params、fields参数版
        JSONObject response = null;
        if (params == null && fields == null) {
            response = tushareClient.doQuery(apiEnum.getValue(), token);
        }
        // 通用TuShare请求查询: 缺少params参数版
        else if (params == null) {
            response = tushareClient.doQuery(apiEnum.getValue(), token, fields.getFieldsJoinedStr());
        }
        // 通用TuShare请求查询: 全参数版
        else {
            response = tushareClient.doQuery(apiEnum.getValue(), token, JSONObject.toJSONString(params), fields.getFieldsJoinedStr());
        }

        // 解析增强式TuShare请求结果
        return parseResponse(response, tClass);
    }

    /**
     * 解析增强式TuShare请求结果
     */
    private <T> List<T> parseResponse(JSONObject response, Class<T> tClass) {
        log.info("解析增强式TuShare请求结果: response={}, tClass={}", JSONObject.toJSONString(tClass), tClass.getName());
        if (response == null) {
            return null;
        }

        // 解析成TuShare形式的结果
        TushareBaseResponse<T> tushareResponse = new TushareBaseResponse<>(response, tClass);
        if(tushareResponse == null) {
            return null;
        }

        log.info("解析增强式TuShare请求结果完毕 => tushareResponse={}", JSONObject.toJSONString(tushareResponse));
        return tushareResponse.getData();
    }
}