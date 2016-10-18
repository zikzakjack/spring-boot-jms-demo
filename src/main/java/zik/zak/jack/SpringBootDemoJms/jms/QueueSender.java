package zik.zak.jack.SpringBootDemoJms.jms;

import javax.jms.Topic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import zik.zak.jack.SpringBootDemoJms.domain.TransactionAlert;

@Component
public class QueueSender {

  private static final Logger logger = LoggerFactory.getLogger(QueueSender.class);

  @Value("${trans.alert.queue}")
  String transTopicName;

  JmsTemplate jmsTemplate;
  
  @Autowired
  public QueueSender(JmsTemplate jmsTemplate) {
    this.jmsTemplate = jmsTemplate;
  }

  public void send(TransactionAlert alert) {
//    jmsTemplate.setPubSubDomain(true);
    logger.info("Message posted to : " + transTopicName);
    jmsTemplate.convertAndSend(transTopicName, alert);
  }
}
