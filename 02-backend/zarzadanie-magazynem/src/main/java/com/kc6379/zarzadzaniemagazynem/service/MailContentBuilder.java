package com.kc6379.zarzadzaniemagazynem.service;

import com.kc6379.zarzadzaniemagazynem.dto.OrderItemDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.List;

@Service
@AllArgsConstructor
public class MailContentBuilder {

    private final TemplateEngine templateEngine;

    public String build(String message) {
        Context context = new Context();
        context.setVariable("message", message);
        return templateEngine.process("mailTemplate", context);
    }

    public String order(String message, List<OrderItemDto> orderItems) {
        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("orderItems", orderItems);
        return templateEngine.process("orderTemplate", context);
    }

}
