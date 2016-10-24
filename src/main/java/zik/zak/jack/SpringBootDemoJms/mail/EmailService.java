package zik.zak.jack.SpringBootDemoJms.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Service class to send email using Spring and Java Mail Note: # If you are intending to send email
 * from a gmail account, then below account settings need to be changed. # Goto
 * https://www.google.com/settings/security/lesssecureapps >> Access for less secure apps >> Turn on
 * or Goto My Account >> Sign-in & security >> Manage your account access and security settings >>
 * Allow less secure apps >> ON
 * 
 * @author kalyan
 *
 */
@Service
public class EmailService {

  private JavaMailSender mailSender;

  @Autowired
  public EmailService(JavaMailSender mailSender) {
    this.mailSender = mailSender;
  }

  public void prepareAndSend(String sender, String recipient, String message) {
    SimpleMailMessage msg = new SimpleMailMessage();
    msg.setSubject("Transaction Alert on your account !!!");
    msg.setFrom(sender);
    msg.setTo(recipient);
    msg.setText(message);
    try {
      this.mailSender.send(msg);
    } catch (MailException ex) {
      // simply log it and go on...
      System.err.println(ex.getMessage());
    }
  }

}
