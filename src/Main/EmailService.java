package Main;

import com.sun.jdi.connect.Connector;
import org.apache.log4j.Logger;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;

/**
 * EmailService.java
 *
 * @author g106108
 * @since 9/11/18
 */
public class EmailService {
    final static Logger logger = Logger.getLogger( EmailService.class );

    private static final String SEND_FROM_EMAIL = "gebremedhin.mehert@heb.com";
    private static final String SEND_FROM_NAME = "Mehert Gebremedhin";

    /**
     * send Email method to send simple email
     *
     * @param session
     * @param toEmail
     * @param subject
     * @param body
     */
    public static void sendEmail(Session session, String toEmail, String subject, String body) throws Exception {
        try
        {

        MimeMessage message = new MimeMessage(session);
            message.addHeader("Content-type", "text/HTML; charset=UTF-8");
            message.addHeader("format", "flowed");
            message.addHeader("Content-Transfer-Encoding", "8bit");

            message.setFrom(new InternetAddress(SEND_FROM_EMAIL, SEND_FROM_NAME));
            message.setReplyTo(InternetAddress.parse(SEND_FROM_EMAIL, false));
            message.setSubject(subject, "UTF-8");
            message.setText(body, "UTF-8");
            message.setSentDate(new Date());

            message.setRecipients( Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
        Transport.send(message);

    }catch (Exception e){
            logger.error( "Got an exception! " );
            logger.error( e.getMessage() );
        }
    }
}
