package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SimpleEmailService {

    private final JavaMailSender javaMailSender;
    private MailCreatorService mailCreatorService;

    public void send(final Mail mail, final MailCreatorService mailCreatorService) {
        log.info("Starting email preparation");
        try {
            javaMailSender.send(createMimeMessage(mail,mailCreatorService));
            log.info("Email has been sent.");
        } catch (MailException mailException) {
            log.error("Failed to process email sending" + mailException.getMessage(), mailException);
        }
    }

    private MimeMessagePreparator createMimeMessage(final Mail mail, final MailCreatorService mailCreatorService){
        return  mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(mail.getMailTo());
            messageHelper.setSubject(mail.getSubject());
            messageHelper.setText(mailCreatorService.buildEmail(mail.getMessage()),true);
        };
    }

/*    private SimpleMailMessage createMailMessage(final Mail mail) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mailCreatorService.builtTrelloCardEmail((mail.getMessage())));
        return mailMessage;
    }*/
}
