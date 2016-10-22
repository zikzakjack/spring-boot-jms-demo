package zik.zak.jack.SpringBootDemoJms.services;

import java.util.Date;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import zik.zak.jack.SpringBootDemoJms.domain.TransactionAlert;
import zik.zak.jack.SpringBootDemoJms.jms.TopicPublisher;

/**
 * Implementation for ATMService
 * 
 * @author kalyan
 *
 */
@Component
public class ATMServiceImpl implements ATMService {
  private static final Logger logger = LoggerFactory.getLogger(ATMServiceImpl.class);

  TopicPublisher topicPublisher;

  @Autowired
  public ATMServiceImpl(TopicPublisher topicPublisher) {
    this.topicPublisher = topicPublisher;
  }

  @Override
  public void transact() {
    // Create Random Transaction Alerts as test data
    TransactionAlert alert = new TransactionAlert(TransactionAlert.TransactionType.get(new Random().nextInt(2)),
        (int) ((Math.random() * 900000) + 100) / 100.0, new Date());
    logger.info("<=============================================> ");
    logger.info("Transaction Detected => " + alert);

    // publish the transaction alerts
    topicPublisher.publish(alert);
  }

}
