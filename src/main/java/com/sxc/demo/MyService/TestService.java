package com.sxc.demo.MyService;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class TestService {
    public JSONObject test(JSONObject params){
        return params;

    }
}
