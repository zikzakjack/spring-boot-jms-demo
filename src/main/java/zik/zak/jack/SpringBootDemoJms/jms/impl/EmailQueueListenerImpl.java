package zik.zak.jack.SpringBootDemoJms.jms.impl;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import zik.zak.jack.SpringBootDemoJms.domain.TransactionAlert;
import zik.zak.jack.SpringBootDemoJms.jms.QueueListener;
import zik.zak.jack.SpringBootDemoJms.mail.EmailService;

/**
 * Listener class that reads messages from EmailQueue
 * 
 * @author kalyan
 *
 */
@Component
public class EmailQueueListenerImpl implements QueueListener {

  private static final Logger logger = LoggerFactory.getLogger(EmailQueueListenerImpl.class);

  @Autowired
  EmailService emailService;
  
  @PostConstruct
  public void init() {
    logger.info("EmailQueueListenerImpl Listener is Initialized...");
  }

  @JmsListener(destination = "${trans.alert.email.queue}", containerFactory = "queueJmsListenerContainerFactory")
  public void receiveMessage(TransactionAlert alert) {
    logger.info("Sending Email => <" + alert + ">");
    // In a typical case, from address would be configured in application.properties
    // to address would be retrieved from user or account details.
    // I am hardcoding in this case for simplicity 
    emailService.prepareAndSend("from_changeme@gmail.com", "to_changeme@gmail.com", alert.toString());
  }

}
