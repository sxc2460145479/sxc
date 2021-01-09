//package com.sxc.demo.aspect;
//
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.*;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.springframework.stereotype.Component;
//
//@Component
//@Aspect
//public class TokenAspect {
//    @Pointcut("@annotation(com.sxc.demo.annotation.Token)")
//    private void annotationPointCut() {
//    }
//
//    @Before("annotationPointCut()")
//    public void beforePointCut(JoinPoint joinPoint) {
//        // 此处进入到方法前，可以实现一些业务逻辑
//        System.out.println("执行beforePointCut下的逻辑");
//    }
//
//    @Around("annotationPointCut()")
//    public void doAround(ProceedingJoinPoint joinPoint) throws Throwable {
//        Object[] value = joinPoint.getArgs(); // 获取参数值
//        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
//        methodSignature.getParameterNames(); // 获取参数名称
//        methodSignature.getMethod().getName();
//        System.out.println("Around Begin");
//        joinPoint.proceed();//执行到这里开始走进来的方法体（必须声明）
//        System.out.println("Around End");
//    }
//
//    @AfterReturning("annotationPointCut()")
//    public void after(JoinPoint joinPoint) {
//
//    }
//
//}
