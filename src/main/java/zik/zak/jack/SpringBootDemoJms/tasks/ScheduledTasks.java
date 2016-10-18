package zik.zak.jack.SpringBootDemoJms.tasks;

import java.util.Date;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import zik.zak.jack.SpringBootDemoJms.domain.TransactionAlert;
import zik.zak.jack.SpringBootDemoJms.jms.QueueSender;

@Component
public class ScheduledTasks {

  private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);

  QueueSender queueSender;
  
  @Autowired
  public void setQueueSender(QueueSender queueSender) {
    this.queueSender = queueSender;
  }


  @Scheduled(initialDelay = 5000, fixedRate = 10000)
  public void reportCurrentTime() {
    
    // Create Random Transaction Alerts as test data
    TransactionAlert alert = new TransactionAlert(TransactionAlert.TransactionType.get(new Random().nextInt(2)),
        (int) ((Math.random() * 900000) + 100) / 100.0, new Date());
    logger.info("<=============================================> ");
    logger.info("Transaction Detected => " + alert);
    
    // Post the Transaction to a Destination
    queueSender.send(alert);
  }
}
