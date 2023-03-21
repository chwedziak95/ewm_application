package com.kc6379.zarzadzaniemagazynem.exceptions;

public class EwmAppException extends RuntimeException {
    public EwmAppException(String exMessage, Exception exception) {
        super(exMessage, exception);
    }
    public EwmAppException(String exMessage) {
        super(exMessage);
    }
}
