package anjay;

// import java.util.ArrayList;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

import Books.Book;
import Data.Student;

// import Data.Student;

public class sendEmailPinjam {
    public static void kirimEmail(Student student){
        // Student student = new Student("", "", "", "", "");
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
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(student.getEmail()));
            message.setSubject("Pinjam Buku");
        
            StringBuilder emailContent = new StringBuilder();
            emailContent.append("----------------------------------------------------------------------\n");
            emailContent.append("Buku yang diPinjam\n");
            emailContent.append("----------------------------------------------------------------------\n");
            for(Book book : student.getBukuBorrowed()){
                emailContent.append("Judul\t: "+book.getJudul()+"\n");
                emailContent.append("Author\t: "+book.getAuthor()+"\n");
                emailContent.append("BookId\t: "+book.getBookId()+"\n");
                emailContent.append("Kategori\t: "+book.getCategory()+"\n");
                emailContent.append("Jumlah\t: "+book.getStock()+"\n");
                emailContent.append("durasi\t: "+book.getDurasi()+" hari\n");
                emailContent.append("\n");
            }
            emailContent.append("----------------------------------------------------------------------");

            message.setText(emailContent.toString());
            Transport.send(message);
            
            System.out.println("email berhasil dikirim");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
