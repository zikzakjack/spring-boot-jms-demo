package zik.zak.jack.SpringBootDemoJms.jms.impl;

import javax.annotation.PostConstruct;
import javax.jms.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import zik.zak.jack.SpringBootDemoJms.domain.TransactionAlert;
import zik.zak.jack.SpringBootDemoJms.jms.QueueSender;
import zik.zak.jack.SpringBootDemoJms.jms.TopicSubscriber;

/**
 * Subscriber2- Listener class that reads messages from topic
 * 
 * @author kalyan
 *
 */
@Component
public class TopicSubscriberSmsClientsImpl implements TopicSubscriber {

  private static final Logger logger = LoggerFactory.getLogger(TopicSubscriberSmsClientsImpl.class);

  private QueueSender queueSender;
  private Queue smsNotificationQueue;

  @Autowired
  public TopicSubscriberSmsClientsImpl(Queue smsNotificationQueue, QueueSender queueSender) {
    this.smsNotificationQueue = smsNotificationQueue;
    this.queueSender = queueSender;
  }

  @PostConstruct
  public void init() {
    logger.info("TopicSubscriberSmsClientsImpl Listener is Initialized...");
  }

  @JmsListener(destination = "${trans.alert.topic}", containerFactory = "topicJmsListenerContainerFactory")
  public void subscribe(TransactionAlert alert) {
    logger.info("Alert received for SMS Clients => <" + alert + ">");
    queueSender.send(smsNotificationQueue, alert);
  }

}
