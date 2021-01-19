package com.sxc.demo.validation;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ParamsValidChain {

    private List<ParamsValidService> paramsValidServiceList;

    public void addChain(ParamsValidService paramsValidService) {
        paramsValidServiceList.add(paramsValidService);
    }

    public void processChain(JSONObject params){
        paramsValidServiceList.forEach(paramsValidService->{
            paramsValidService.checkLength(params);
            paramsValidService.checkSpecialChar(params);
        });
    }

}
