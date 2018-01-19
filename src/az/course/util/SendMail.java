package az.course.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: fuadp
 * Date: 3/19/16
 * Time: 1:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class SendMail {

    public static void sendMail(String subject,String text,String mailTo) {

        final String username = "orient.testt@gmail.com";
        final String password = "adobeadobe1";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("orient.testt@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(mailTo));
            message.setSubject(subject);
            message.setText(text);

            Transport.send(message);

            System.out.println("Mail gonderildi!");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }




    }

}
