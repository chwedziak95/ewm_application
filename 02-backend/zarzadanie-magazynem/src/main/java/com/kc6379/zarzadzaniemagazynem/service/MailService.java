package com.kc6379.zarzadzaniemagazynem.service;

import com.kc6379.zarzadzaniemagazynem.dto.OrderItemDto;
import com.kc6379.zarzadzaniemagazynem.exceptions.EwmAppException;
import com.kc6379.zarzadzaniemagazynem.model.NotificationEmail;
import com.kc6379.zarzadzaniemagazynem.model.OrderEmail;
import com.kc6379.zarzadzaniemagazynem.model.RegistrationEmail;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
class MailService {

    private final JavaMailSender mailSender;
    private final MailContentBuilder mailContentBuilder;


    @Async
    void sendMail(RegistrationEmail registrationEmail) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("ewmapp@email.com");
            messageHelper.setTo(registrationEmail.getRecipient());
            messageHelper.setSubject(registrationEmail.getSubject());
            String htmlContent = mailContentBuilder.build(registrationEmail);
            messageHelper.setText(htmlContent, true);
        };
        try {
            mailSender.send(messagePreparator);
            log.info("Wysłano aktywacyjny email!");
        } catch (MailException e) {
            log.error("Wystąpił problem podczas wysyłania wiadomości email ", e);
            throw new EwmAppException("Wystąpił problem podczas wysyłania wiadomości email do " + registrationEmail.getRecipient(), e);
        }
    }

    @Async
    void sendOrderItemToVendor(NotificationEmail notificationEmail, List<OrderItemDto> orderItems) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("ewmapp@email.com");
            messageHelper.setTo(notificationEmail.getRecipient());
            messageHelper.setSubject(notificationEmail.getSubject());
            String htmlContent = mailContentBuilder.order(notificationEmail.getBody(), orderItems);
            messageHelper.setText(htmlContent, true);
        };
        try {
            mailSender.send(messagePreparator);
            log.info("Wysłano zamówienie do dostawcy!");
        } catch (MailException e) {
            log.error("Wystąpił problem podczas wysyłania wiadomości email ", e);
            throw new EwmAppException("Wystąpił problem podczas wysyłania wiadomości email do " + notificationEmail.getRecipient(), e);
        }
    }


}
