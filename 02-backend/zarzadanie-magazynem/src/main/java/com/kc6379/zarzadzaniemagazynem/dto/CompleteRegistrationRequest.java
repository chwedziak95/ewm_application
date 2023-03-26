package com.kc6379.zarzadzaniemagazynem.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompleteRegistrationRequest {
    private String token;
    private String password;
    private String confirmPassword;
}
