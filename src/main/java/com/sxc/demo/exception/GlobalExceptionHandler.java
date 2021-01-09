package com.sxc.demo.exception;

import ch.qos.logback.core.joran.spi.ElementSelector;
import lombok.extern.log4j.Log4j2;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Log4j2
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    @ResponseBody
    public Map<String, Object> handleException(HttpServletRequest request, Exception ex) {
        log.error("MyExceptionHandler:Exception handle,request url={}, method={}",
                request.getRequestURL(),
                request.getMethod());
        Map<String,Object> response = new HashMap<>();
        if(ex instanceof MyException){
            MyException exception = (MyException) ex;
            response.put("retCode",exception.getErrorCode());
            response.put("errorMsg",exception.getErrorMsg());
        }else if (ex instanceof IOException){
            response.put("retCode",ErrorCode.IO_EXCEPTION);
            response.put("retMsg","IO exception occurred");
        }else if (ex instanceof SQLException){
            response.put("retCode",ErrorCode.SQL_EXCEPTION);
            response.put("retMsg","SQL exception occurred");
        }else {
            // 其他异常处理成位置异常
            response.put("retCode",ErrorCode.UNKNOWN_EXCEPTION);
            response.put("retMsg","Unknown exception occurred");
        }
        return response;
    }

}
