package com.kc6379.zarzadzaniemagazynem.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotificationEmail {
    private String subject;
    private String recipient;
    private String body;


}
