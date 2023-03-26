package com.kc6379.zarzadzaniemagazynem.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationEmail extends NotificationEmail {

    private String firstName;
    private String lastName;

    public RegistrationEmail(String subject, String recipient, String body, String firstName, String lastName) {
        super(subject, recipient, body);
        this.firstName = firstName;
        this.lastName = lastName;
    }

}
