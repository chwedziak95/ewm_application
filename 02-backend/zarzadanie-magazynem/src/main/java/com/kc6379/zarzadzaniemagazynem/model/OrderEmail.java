package com.kc6379.zarzadzaniemagazynem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderEmail {
    private String subject;
    private String recipient;
    private String body;
}
