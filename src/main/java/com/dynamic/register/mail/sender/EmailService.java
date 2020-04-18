package com.dynamic.register.mail.sender;

import com.dynamic.register.RegisterResource.UserDetailController;
import com.dynamic.register.model.email.Emailmodel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
public class EmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailService.class);
    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(Emailmodel emailmodel) throws MessagingException {
        LOGGER.info("the email is sending...");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailmodel.getEmail());
        message.setSubject(emailmodel.getSubject());
        message.setText(emailmodel.getText() +"  " +  emailmodel.getPassword());
        mailSender.send(message);
    }

}
