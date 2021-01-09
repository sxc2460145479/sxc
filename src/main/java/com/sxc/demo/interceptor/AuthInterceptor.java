package com.sxc.demo.interceptor;

import com.sxc.demo.annotation.Token;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Component
@Order(value = 1)
public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            // 获取方法上的注解
            Token annotation = handlerMethod.getMethod().getAnnotation(Token.class);

            // 如果方法上的注解为空，则取类上的注解
            if (annotation == null) {
                annotation = handlerMethod.getMethod().getDeclaringClass().getAnnotation(Token.class);
            }
            String annotationType = annotation.value();
            if (!annotationType.isEmpty() && Objects.equals(annotationType, "aksk")) {
                String method = request.getMethod();
                String contentType = request.getContextPath();
                String authorization = request.getHeader("authorization");
                String authType = request.getAuthType();
                checkAkSkPermission(request);
            }
            return true;
        }
        return false;
    }

    private void checkAkSkPermission(HttpServletRequest request) {
        // TODO 执行权限校验逻辑
    }
}
