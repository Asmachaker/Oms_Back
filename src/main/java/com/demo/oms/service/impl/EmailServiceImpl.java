package com.demo.oms.service.impl;

import com.demo.oms.entity.Client;
import com.demo.oms.entity.Facture;
import com.demo.oms.entity.FactureAvoir;
import com.demo.oms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.sql.Date;
import java.time.LocalDate;

@Service
public class EmailServiceImpl implements UserService.EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private MessageSource messages;

    @Autowired
    private Environment env;




    public void constructEmailMessage(String codeVerify, String destinationMail) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

        final String message = messages.getMessage("message.regSuccLink", null, "You registered successfully. To confirm your registration, please click on the below link.", null);
        helper.setText("Verify your account with this code : " + codeVerify); // Use this or above line.
        helper.setTo(destinationMail);
        helper.setSubject("Verify your account");
        helper.setFrom(env.getProperty("support.email"));
        mailSender.send(mimeMessage);
    }

    public void ResetPasswordMail (String email,String username) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
        // InternetAddress addressFrom = new InternetAddress(from);
        helper.setFrom("chekerasma10@gmail.com");
        String url = "http://localhost:8888/auth/reset-password/"+username;
        helper.setText("Voici le lien");
        helper.setText(url); // Use this or above line.
        helper.setTo(email);
        helper.setSubject("Reset Password Email");
        mailSender.send(mimeMessage);
    }

    public void ActivationMail (String email, String name ,String username) throws MessagingException {
      MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
        helper.setFrom("chekerasma10@gmail.com");
        String url = "http://localhost:8888/auth/activate/"+username;;
        helper.setText("Welcome to the team", name);
        helper.setText(url); // Use this or above line.
        helper.setTo(email);

        helper.setSubject("Activation Email");
        mailSender.send(mimeMessage);
    }

    public void ClientEmail (FactureAvoir facture, Client client) throws Exception {

        String path ="C:\\Users\\Asma\\Desktop\\PFE\\ngx-admin-master\\src\\assets\\documents\\factureAvoir\\"+facture.getName();

        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper = getHelper(path, mimeMessage);
        helper.setTo(client.getEmail());
        helper.setSubject("Facture");
        helper.setFrom("chekerasma10@gmail.com");
        mailSender.send(mimeMessage);

    }


    private MimeMessageHelper getHelper(String path, MimeMessage mimeMessage) throws MessagingException {
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "utf-8");
        Date now = Date.valueOf(LocalDate.now());
        File attach = new File(path);
        helper.addAttachment(attach.getName(), attach);
        helper.setText("Voila la facture de"+now);
        return helper;
    }


}




