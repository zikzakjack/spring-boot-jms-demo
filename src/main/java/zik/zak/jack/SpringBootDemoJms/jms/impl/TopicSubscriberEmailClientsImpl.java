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
 * Subscriber1- Listener class that reads messages from topic
 * 
 * @author kalyan
 *
 */
@Component
public class TopicSubscriberEmailClientsImpl implements TopicSubscriber {

  private static final Logger logger = LoggerFactory.getLogger(TopicSubscriberEmailClientsImpl.class);

  private QueueSender queueSender;
  private Queue emailNotificationQueue;

  @Autowired
  public TopicSubscriberEmailClientsImpl(Queue emailNotificationQueue, QueueSender queueSender) {
    this.emailNotificationQueue = emailNotificationQueue;
    this.queueSender = queueSender;
  }

  @PostConstruct
  public void init() {
    logger.info("TopicSubscriberEmailClientsImpl Listener is Initialized...");
  }

  @JmsListener(destination = "${trans.alert.topic}", containerFactory = "topicJmsListenerContainerFactory")
  @Override
  public void subscribe(TransactionAlert alert) {
    logger.info("Alert received for Email Clients => <" + alert + ">");
    queueSender.send(emailNotificationQueue, alert);
  }


}
