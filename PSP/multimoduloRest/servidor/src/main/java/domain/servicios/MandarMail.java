package domain.servicios;


import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.log4j.Log4j2;

import java.util.Properties;


/**
 *
 * @author oscar
 */
@Log4j2
public class MandarMail {


//    public void mandarMail(String to, String msg, String subject) {
//        try {
//            Email email = new SimpleEmail();
//
//            email.setHostName("smtp.gmail.com");
//            email.setSmtpPort(Integer.parseInt("587"));
//            email.setAuthentication("alumnosdamquevedo@gmail.com", "quevedo2020");
//            //email.setSSLOnConnect(true);
//            email.setStartTLSEnabled(true);
//            email.setFrom("alumnosDamQuevedo@gmail.com");
//            email.setSubject(subject);
//            email.setContent(msg,"text/html");
//            email.addTo(to);
//
//            email.send();
//        } catch (EmailException ex) {
//
//            Logger.getLogger(MandarMail.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }


    public void generateAndSendEmail(String to, String msg, String subject) throws MessagingException {
        Properties mailServerProperties;
        Session getMailSession;
        MimeMessage generateMailMessage;

        // Step1

        mailServerProperties = System.getProperties();
        mailServerProperties.put("mail.smtp.host", "smtp.gmail.com");
        mailServerProperties.put("mail.smtp.port", Integer.parseInt("587"));
        mailServerProperties.put("mail.smtp.auth", "true");
        mailServerProperties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        mailServerProperties.put("mail.smtp.starttls.enable", "true");

        // Step2



        //getDefaultInstance
        getMailSession = Session.getInstance(mailServerProperties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("alumnosdamquevedo@gmail.com", "uyhqfbbfmszvuykt");
            }
        });
        generateMailMessage = new MimeMessage(getMailSession);
        generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        generateMailMessage.setSubject(subject);
        String emailBody = msg;
        generateMailMessage.setContent(emailBody, "text/html");


        // Step3

        //Transport transport = getMailSession.getTransport("smtp");
        Transport.send(generateMailMessage);
        // Enter your correct gmail UserID and Password
        // if you have 2FA enabled then provide App Specific Password
//        transport.connect("smtp.gmail.com",
//                "alumnosdamquevedo@gmail.com",
//                "uyhqfbbfmszvuykt");
        //transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
        //transport.close();
    }
}
