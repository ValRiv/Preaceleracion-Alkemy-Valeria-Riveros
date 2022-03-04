package com.challenge.prealkemy.service.impl;

import com.challenge.prealkemy.exceptionsMensaje.ExceptionMensaje;
import com.challenge.prealkemy.service.EmailService;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 *
 * @author river
 */
@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private Environment env;

    @Value("${alkemy.disney.email.sender}")
    private String emailSender;

    public void sendWelcomeEmailTo(String to) throws IOException {

        String apiKey = env.getProperty("EMAIL_API_KEY");

        Email fromEmail = new Email(emailSender);
        Email toEmail = new Email(to);
        Content content = new Content(
                "text/plain",
                "Bienvenido/a a Alkemy "
        );
        String subject = "Alkemy";

        Mail mail = new Mail(fromEmail, subject, toEmail, content);
        SendGrid sendGrid = new SendGrid(apiKey);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sendGrid.api(request);

        } catch (IOException ex) {
            throw new IOException(ExceptionMensaje.EMAIL_NO_VALIDO);        
        }
    }
}