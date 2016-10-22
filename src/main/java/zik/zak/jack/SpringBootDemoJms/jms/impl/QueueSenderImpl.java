package zik.zak.jack.SpringBootDemoJms.jms.impl;

import javax.jms.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import zik.zak.jack.SpringBootDemoJms.domain.TransactionAlert;
import zik.zak.jack.SpringBootDemoJms.jms.QueueSender;

/**
 * Generic sender class that sends messages to EmailQueue
 * 
 * @author kalyan
 *
 */
@Component
public class QueueSenderImpl implements QueueSender {

  private static final Logger logger = LoggerFactory.getLogger(QueueSenderImpl.class);

  JmsTemplate queueJmsTemplate;

  @Autowired
  public QueueSenderImpl(JmsTemplate queueJmsTemplate) {
    this.queueJmsTemplate = queueJmsTemplate;
    this.queueJmsTemplate.setPubSubDomain(false);
  }

  @Override
  public void send(Queue queue, TransactionAlert alert) {
    logger.info("Message posted to the Queue : " + queue);
    queueJmsTemplate.convertAndSend(queue, alert);
  }

}
