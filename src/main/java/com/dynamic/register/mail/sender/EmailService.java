package com.dynamic.register.mail.sender;

import com.dynamic.register.model.email.EmailModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
public class EmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailService.class);
    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(EmailModel emailmodel) throws MessagingException {
        LOGGER.info("the email is sending...");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailmodel.getEmail());
        message.setSubject(emailmodel.getSubject());
        message.setText(emailmodel.getText() +"  " +  emailmodel.getPassword());
        mailSender.send(message);
    }

}
