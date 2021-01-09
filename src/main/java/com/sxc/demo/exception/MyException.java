package com.sxc.demo.exception;

import lombok.Data;

@Data
public class MyException extends RuntimeException {
    private String errorCode;
    private String errorMsg;

    public MyException(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }
}
