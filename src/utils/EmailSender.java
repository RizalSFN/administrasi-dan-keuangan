package utils;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailSender {

    private static final String FROM_EMAIL = "rizalsfn321@gmail.com";
    private static final String FROM_PASSWORD = "xypn jxxf bpml baip";

    public static void sendEmail(String toEmail, String subject, String body) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM_EMAIL, FROM_PASSWORD);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(FROM_EMAIL));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);
            System.out.println("Email terkirim ke :" + toEmail);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String testEmail = "rizalsofiana1976@gmail.com";
        String testSubject = "TEST: Pengujian Email Sender";
        // String testBody = "Halo! Ini adalah email uji coba dari aplikasi Java kamu.";

        String testBody = "Halo, ini adalah tagihan pembayaran sekolah untuk siswa dengan identitas sebagai berikut : \n\n"
                + "Nama : Rizal Sofiana \n"
                + "Kelas : 12 IPA 3 \n"
                + "NISN : 123123123 \n"
                + "Jumlah Tagihan : Rp 500.000,00 \n"
                + "Tanggal jatuh tempo : 20 Juni 2025 \n"
                + "Pembayaran tunai : langsung datang ke sekolah (ke staff keuangan) \n"
                + "Pembayaran non tunai : Transfer ke : 123321123 a/n SMK Tadika Mesra \n\n"
                + "Hormat kami,\n"
                + "Staff administrasi dan keuangan \n"
                + "SMA Tadika Mesra";

        EmailSender.sendEmail(testEmail, testSubject, testBody);
    }
}
