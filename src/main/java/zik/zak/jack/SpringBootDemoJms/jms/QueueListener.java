package zik.zak.jack.SpringBootDemoJms.jms;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import zik.zak.jack.SpringBootDemoJms.domain.TransactionAlert;

@Component
public class QueueListener {

  private static final Logger logger = LoggerFactory.getLogger(QueueListener.class);

  JmsTemplate jmsTemplate;

  @Autowired
  public QueueListener(JmsTemplate jmsTemplate) {
    this.jmsTemplate = jmsTemplate;
  }

  @PostConstruct
  public void checkThisOut() {
    logger.info("Listener is working...");
  }

  @JmsListener(destination = "${trans.topic}")
  public void receiveMessage(TransactionAlert alert) {
    logger.info("Received <" + alert + ">");
  }

}
