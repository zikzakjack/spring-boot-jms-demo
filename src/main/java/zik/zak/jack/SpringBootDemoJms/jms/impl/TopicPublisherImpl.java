package zik.zak.jack.SpringBootDemoJms.jms.impl;

import javax.jms.DeliveryMode;
import javax.jms.Session;
import javax.jms.Topic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import zik.zak.jack.SpringBootDemoJms.domain.TransactionAlert;
import zik.zak.jack.SpringBootDemoJms.jms.TopicPublisher;

/**
 * Generic sender class that publishes messages to EmailTopic
 * 
 * @author kalyan
 *
 */
@Component
public class TopicPublisherImpl implements TopicPublisher {

  private static final Logger logger = LoggerFactory.getLogger(TopicPublisherImpl.class);

  Topic transactionAlertTopic;
  JmsTemplate topicJmsTemplate;

  @Autowired
  public TopicPublisherImpl(Topic transactionAlertTopic, JmsTemplate topicJmsTemplate) {
    this.transactionAlertTopic = transactionAlertTopic;
    this.topicJmsTemplate = topicJmsTemplate;
  }

  public void publish(TransactionAlert alert) {

    // Indicates that the destination is a topic
    topicJmsTemplate.setPubSubDomain(true);

    // Sets the Delivery mode as Persistent
    topicJmsTemplate.setDeliveryPersistent(true);
    topicJmsTemplate.setDeliveryMode(DeliveryMode.PERSISTENT);

    // Sets the Highest Priority
    topicJmsTemplate.setPriority(9);

    // Sets lifetime for the message
    topicJmsTemplate.setTimeToLive(100000000000000000L);

    // Set the JMS acknowledgement mode that is used when creating a JMS Session to send a message
    topicJmsTemplate.setSessionAcknowledgeMode(Session.AUTO_ACKNOWLEDGE);

    logger.info("Alert Published to : " + transactionAlertTopic);
    topicJmsTemplate.convertAndSend(transactionAlertTopic, alert);
  }
}
