package com.kc6379.zarzadaniemagazynem.exceptions;

import org.springframework.mail.MailException;

public class EwmAppException extends RuntimeException {
    public EwmAppException(String exMessage, Exception exception) {
        super(exMessage, exception);
    }
    public EwmAppException(String exMessage) {
        super(exMessage);
    }
}
