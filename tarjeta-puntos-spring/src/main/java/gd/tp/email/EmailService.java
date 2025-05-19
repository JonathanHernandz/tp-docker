package gd.tp.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String to, String subject, String text) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom("contacto@miishelados.com");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, true); // false = texto plano, true = HTML

            mailSender.send(message);
            System.out.println("Correo enviado a: " + to);
        } catch (MessagingException e) {
            System.out.println("Error al enviar correo: " + e.getMessage());
        }
        /*SimpleMailMessage message =  new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        message.setFrom("contacto@miishelados.com");

        mailSender.send(message); */
    }

    public void sendResetCode(String to, String code){
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom("contacto@miishelados.com");
            helper.setTo(to);
            helper.setSubject("Código para restablecer tu contraseña");

            String htmlContent = "<p>Tu código es: <strong>" + code + "</strong></p>" +
                    "<p>Este código expirará en <strong>15 minutos ⌛</strong>.</p>";

            helper.setText(htmlContent, true); // true = HTML

            mailSender.send(message);
            System.out.println("Correo enviado a: " + to);
        } catch (MessagingException e) {
            System.out.println("Error al enviar correo: " + e.getMessage());
        }

    }
}
