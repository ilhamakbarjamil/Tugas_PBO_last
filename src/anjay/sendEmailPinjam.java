package anjay;

// import java.util.ArrayList;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

// import Data.Student;

public class sendEmailPinjam {
    public static void kirimEmail(String kirim){

        final String username = "ilhamakbarjamil8@gmail.com";
        final String password = "iycx ojhe cmmc hxqf";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com"); 
        props.put("mail.smtp.port", "587"); 
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");


        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(kirim));
            message.setSubject("Pinjam Buku");
            message.setText("Buku yang anda pinjam");
            

            Transport.send(message);
            System.out.println("email berhasil dikirim");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
