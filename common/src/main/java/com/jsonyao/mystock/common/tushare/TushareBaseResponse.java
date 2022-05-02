package com.jsonyao.mystock.common.tushare;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jsonyao.mystock.common.util.TushareDataColumnParseUtil;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * TuShare大数据平台 响应结果
 *
 * @author yaocs2
 * @since 20220502
 */
@Data
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
        JSONObject data = response.getJSONObject("data");
        JSONArray fields = data.getJSONArray("fields");
        JSONArray itemsArray = data.getJSONArray("items");
        List<JSONArray> items = new ArrayList<>(itemsArray.size());
        for (int i = 0; i < itemsArray.size(); i++) {
            items.add(itemsArray.getJSONArray(i));
        }

        // 解析data数据成List<Bean>
        this.data = TushareDataColumnParseUtil.parseData2ListBean(fields, items, tClass);
    }
}
