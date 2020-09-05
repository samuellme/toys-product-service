package com.jsglobe.toys.api.model;

import lombok.Data;

import java.util.Date;

@Data
public class ErrorInfo {
    private Date timestamp;
    private String code;
    private String message;

    public static ErrorInfo create(String code, String message) {
        final var errorInfo = new ErrorInfo();
        errorInfo.timestamp  = new Date();
        errorInfo.code = code;
        errorInfo.message = message;

        return errorInfo;
    }
}
