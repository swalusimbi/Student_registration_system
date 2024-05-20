package Software;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Scanner;

public class Email extends DataTransfer {
        private final String senderEmail = "ab@gmail.com"; // Change to your Gmail address
        private final String senderPassword = "****"; // Change to your Gmail password

        @Override
        public void sendData(String data) {
            // Get recipient email address from the user
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter recipient email address:");
            String recipientEmail = scanner.nextLine();

            // Set up properties for sending email using SMTP
            Properties properties = new Properties();
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", "587");

            // Create a session with authentication
            Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(senderEmail, senderPassword);
                }
            });

            try {
                // Create a MimeMessage object
                Message message = new MimeMessage(session);
                // Set sender email address
                message.setFrom(new InternetAddress(senderEmail));
                // Set recipient email address
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
                // Set email subject
                message.setSubject("Data Transfer");
                // Set email content
                message.setText("Data to be sent:\n" + data);

                // Send the email
                Transport.send(message);
                System.out.println("Email sent successfully to " + recipientEmail);
            } catch (MessagingException e) {
                System.out.println("Failed to send email. Error: " + e.getMessage());
            }
        }
    }


