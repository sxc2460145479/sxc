package com.sxc.demo.annotation;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Token {
    String value() default "";

    interface Type{
        String TOKEN="token";
        String AKSK="aksk";
    }
}
