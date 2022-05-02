package com.jsonyao.mystock.common.tushare;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jsonyao.mystock.common.util.TushareDataColumnParseUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * TuShare大数据平台 响应结果
 *
 * @author yaocs2
 * @since 20220502
 */
@Data
@Slf4j
public class TushareBaseResponse<T> {

    private String requestId;

    private String code;

    private String msg;

    private List<T> data;

    public TushareBaseResponse() {
    }

    public TushareBaseResponse(JSONObject response, Class<T> tClass) {
        this.requestId = response.getString("request_id");
        this.code = response.getString("code");
        this.msg = response.getString("msg");

        // fileds + items
        JSONArray fields = null;
        JSONArray itemsArray = null;
        try {
            JSONObject data = response.getJSONObject("data");
            fields = data.getJSONArray("fields");
            itemsArray = data.getJSONArray("items");
        } catch (Exception e) {
            log.error("构造Tushare响应结果异常! response=" + JSONObject.toJSONString(response), e);
            throw e;
        }

        List<JSONArray> items = new ArrayList<>(itemsArray.size());
        for (int i = 0; i < itemsArray.size(); i++) {
            items.add(itemsArray.getJSONArray(i));
        }

        // 解析data数据成List<Bean>
        this.data = TushareDataColumnParseUtil.parseData2ListBean(fields, items, tClass);
    }
}
