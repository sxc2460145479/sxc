package com.sxc.demo.validation;

import com.alibaba.fastjson.JSONObject;

public interface ParamsValidService {
    void checkLength(JSONObject params);
    void checkSpecialChar(JSONObject params);
}
