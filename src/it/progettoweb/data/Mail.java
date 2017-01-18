package it.progettoweb.data;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

/**
 * User Class
 * Class which collects all user's infos
 * @author Luca, Riccardo, Mario
 */
public class Mail {

    protected String to;
    protected String message;


    public boolean send(){
        Properties props = System.getProperties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.port", "465");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // Get a Session object
        Session session = Session.getInstance(props, null);

        //Create a new message
        Message msg = new MimeMessage(session);

        Transport t = null;
        try {
            t = session.getTransport("smtps");
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
            return false;
        }

        //Set the FROM and TO fields
        try {
            msg.setFrom(new InternetAddress("team.at.foodie@gmail.com"));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to,false));
            msg.setSubject("Peni blu");
            msg.setText(message);
            msg.setSentDate(new Date());
            t.connect("smtp.gmail.com", 465,"team.at.foodie@gmail.com", "scC-Tvt-Upt-X5C");
            t.sendMessage(msg, msg.getAllRecipients());
            //Transport.send(msg, "team.at.foodie@gmail.com", "scC-Tvt-Upt-X5C");
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                t.close();
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }

        return true;
    }

}
