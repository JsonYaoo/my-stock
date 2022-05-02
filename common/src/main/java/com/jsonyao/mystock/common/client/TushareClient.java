package com.jsonyao.mystock.common.client;

import com.alibaba.fastjson.JSONObject;
import com.dtflys.forest.annotation.DataParam;
import com.dtflys.forest.annotation.Request;
import com.jsonyao.mystock.common.constant.ClientConstant;
import com.jsonyao.mystock.common.constant.TushareConstant;

/**
 * TuShare大数据平台 客户端请求类
 *
 * @author yaocs2
 * @since 20220502
 */
public interface TushareClient {

    /**
     * 通用TuShare请求查询: 全参数版
     *
     * @param apiName
     * @param token
     * @param params
     * @param fields
     * @return
     */
    @Request(
            url = TushareConstant.HOST_URL_VALUE,
            headers = { ClientConstant.REQUEST_CONTENT_TYPE_JSON },
            dataType = ClientConstant.REQUEST_DATA_TYPE_JSON,
            type = ClientConstant.REQUEST_TYPE_POST
    )
    JSONObject doQuery(@DataParam(TushareConstant.BODY_API_NAME_TEMPLATE) String apiName,
                       @DataParam(TushareConstant.BODY_TOKEN_TEMPLATE) String token,
                       @DataParam(TushareConstant.BODY_PARAMS_TEMPLATE) Object params,
                       @DataParam(TushareConstant.BODY_FIELDS_TEMPLATE) String fields
    );

    /**
     * 通用TuShare请求查询: 缺少params参数版
     *
     * @param apiName
     * @param token
     * @param fields
     * @return
     */
    @Request(
            url = TushareConstant.HOST_URL_VALUE,
            headers = {ClientConstant.REQUEST_CONTENT_TYPE_JSON },
            dataType = ClientConstant.REQUEST_DATA_TYPE_JSON,
            type = ClientConstant.REQUEST_TYPE_POST
    )
    JSONObject doQuery(@DataParam(TushareConstant.BODY_API_NAME_TEMPLATE) String apiName,
                       @DataParam(TushareConstant.BODY_TOKEN_TEMPLATE) String token,
                       @DataParam(TushareConstant.BODY_FIELDS_TEMPLATE) String fields
    );

    /**
     * 通用TuShare请求查询: 缺少params、fields参数版
     *
     * @param apiName
     * @param token
     * @return
     */
    @Request(
            url = TushareConstant.HOST_URL_VALUE,
            headers = {ClientConstant.REQUEST_CONTENT_TYPE_JSON },
            dataType = ClientConstant.REQUEST_DATA_TYPE_JSON,
            type = ClientConstant.REQUEST_TYPE_POST
    )
    JSONObject doQuery(@DataParam(TushareConstant.BODY_API_NAME_TEMPLATE) String apiName,
                       @DataParam(TushareConstant.BODY_TOKEN_TEMPLATE) String token
    );
}
