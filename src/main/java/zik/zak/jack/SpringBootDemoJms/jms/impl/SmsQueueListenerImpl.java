package zik.zak.jack.SpringBootDemoJms.jms.impl;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import zik.zak.jack.SpringBootDemoJms.domain.TransactionAlert;
import zik.zak.jack.SpringBootDemoJms.jms.QueueListener;

/**
 * Listener class that reads messages from SmsQueue
 * 
 * @author kalyan
 *
 */
@Component
public class SmsQueueListenerImpl implements QueueListener {

  private static final Logger logger = LoggerFactory.getLogger(SmsQueueListenerImpl.class);

  @PostConstruct
  public void init() {
    logger.info("SmsQueueListenerImpl Listener is Initialized...");
  }

  @JmsListener(destination = "${trans.alert.sms.queue}", containerFactory = "queueJmsListenerContainerFactory")
  public void receiveMessage(TransactionAlert alert) {
    logger.info("Sending SMS => <" + alert + ">");
  }

}
