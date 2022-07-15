package egovframework.com.infra.noty.service;

import java.util.Properties;
import javax.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;


@Component("NotificationUtil")
public class NotificationUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationUtil.class);

    private JavaMailSenderImpl sender = new JavaMailSenderImpl();

    public void send(String toEmail, String fromEmail, String subject, String content) {
        try {
            System.out.println("DAVID: SMTP....Start");
            System.out.println("DAVID: toEmail ]" + toEmail + "[");
            System.out.println("DAVID: subject ]" + subject + "[");
            System.out.println("DAVID: SMTP....Start");

            sender.setHost("10.98.0.14");
            sender.setPort(25);
            sender.setUsername("pisarnica.info");
            // sender.setUsername("pisarnica.info@apr.gov.rs");
            sender.setPassword("WindowsServer2012");

            Properties prop = new Properties();
            prop.setProperty("mail.smtp.auth", "true");
            // prop.setProperty("mail.smtp.starttls.enable", "true");
            sender.setJavaMailProperties(prop);

            MimeMessage message = sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);

            helper.setTo(toEmail);
            helper.setSubject(subject);
            helper.setText(content);

            sender.send(message);

            System.out.println("DAVID: SMTP....End");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
