package com.sxc.demo.MyService;

import com.alibaba.fastjson.JSONObject;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.sxc.demo.validation.LengthValidImpl;
import com.sxc.demo.validation.ParamsValidChain;
import com.sxc.demo.validation.SpecialCharValidImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Component
public class LocalCacheService {
    private Cache<String, Set<String>> userPermsCache = null;

    private static final int userPermsCacheCapacity = 512;

    private static final int getUserPermsCacheExpire = 1;

    private String vvalue;

    private static final int CONCUNRRENCY_LEVEL = 16;

    public LocalCacheService() {
        System.out.println("执行构造函数");
    }

    @Autowired
    private ParamsValidChain paramsValidChain;

    /**
     * 使用 @PostConstruct注解，执行顺序，构造方法 > @Autowired > @PostConstruct
     */
    @PostConstruct
    public void init() {
        System.out.println("执行 @PostConstruct注解的方法的逻辑");
        this.userPermsCache = CacheBuilder.newBuilder()
                //设置cache的初始大小为10，要合理设置该值
                .initialCapacity(userPermsCacheCapacity)
                //设置并发数为5，即同一时间最多只能有5个线程往cache执行写入操作
                .concurrencyLevel(CONCUNRRENCY_LEVEL)
                //设置cache中的数据在写入之后的存活时间为10秒
                .expireAfterWrite(getUserPermsCacheExpire, TimeUnit.MINUTES)
                //构建cache实例
                .build();

    }

    public String getValue(JSONObject params) throws ExecutionException {
        // 校验参数
        paramsValidChain.addChain(new LengthValidImpl());
        paramsValidChain.addChain(new SpecialCharValidImpl());
        paramsValidChain.processChain(params);

        // 使用谷歌本地缓存
        Set<String> value = userPermsCache.get("cacheTest", () -> {
            // 如果缓存为null则执行以下逻辑
            Set<String> newSet = new HashSet<>();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("张三", "1");
            jsonObject.put("李四", "2");
            newSet.add(jsonObject.toJSONString());
            return newSet;
        });
        return value.toString();
    }
}
