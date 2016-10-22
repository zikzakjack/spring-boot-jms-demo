package zik.zak.jack.SpringBootDemoJms.jms.impl;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import zik.zak.jack.SpringBootDemoJms.domain.TransactionAlert;
import zik.zak.jack.SpringBootDemoJms.jms.QueueListener;

/**
 * Listener class that reads messages from EmailQueue
 * 
 * @author kalyan
 *
 */
@Component
public class EmailQueueListenerImpl implements QueueListener {

  private static final Logger logger = LoggerFactory.getLogger(EmailQueueListenerImpl.class);

  @PostConstruct
  public void init() {
    logger.info("EmailQueueListenerImpl Listener is Initialized...");
  }

  @JmsListener(destination = "${trans.alert.email.queue}", containerFactory = "queueJmsListenerContainerFactory")
  public void receiveMessage(TransactionAlert alert) {
    logger.info("Sending Email => <" + alert + ">");
  }

}
