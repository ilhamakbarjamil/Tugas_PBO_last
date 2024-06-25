package anjay;

// import java.util.ArrayList;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

import Books.Book;
import Data.Student;
// import Data.User;
// import Data.Student;

public class emailSanksi {
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
            message.setSubject("Sanksi");
            
            StringBuilder emailContent = new StringBuilder();
            emailContent.append("Anda terkena denda,\nAnda terlambat mengembalikkan buku sehingga anda dikenakan denda,\berikut buku yang masih anda pinjam\n");

            for(Book book : student.getBukuBorrowed()){
                emailContent.append("Judul\t: "+book.getJudul()+"\n");
                emailContent.append("Author\t: "+book.getAuthor()+"\n");
                emailContent.append("BookId\t: "+book.getBookId()+"\n");
                emailContent.append("Kategori: "+book.getCategory()+"\n");
                emailContent.append("Jumlah\t: "+Student.jumlahkembali+"\n");
            }
            emailContent.append("----------------------------------------------------------------------");
            // for(Book display : User.booklist){

            // }
            
            message.setText(emailContent.toString());
            Transport.send(message);
            System.out.println("email berhasil dikirim");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
