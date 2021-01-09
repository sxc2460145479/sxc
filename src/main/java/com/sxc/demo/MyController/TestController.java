package com.sxc.demo.MyController;

import com.alibaba.fastjson.JSONObject;
import com.sxc.demo.MyService.TestService;
import com.sxc.demo.annotation.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class TestController {
    @Autowired
    private TestService testService;

    @Token(Token.Type.AKSK)
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST,path = "/test")
    public JSONObject test(@RequestBody JSONObject params){
        return testService.test(params);
    }

    @Token(Token.Type.AKSK)
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET,path = "/out")
    public String out(){
        return "success";
    }
}
