package zik.zak.jack.SpringBootDemoJms.tasks;

import java.util.Date;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import zik.zak.jack.SpringBootDemoJms.domain.TransactionAlert;

@Component
public class ScheduledTasks {

  private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

  @Scheduled(initialDelay = 5000, fixedRate = 10000)
  public void reportCurrentTime() {
    
    // Create Random Transaction Alerts as test data
    TransactionAlert alert = new TransactionAlert(TransactionAlert.TransactionType.get(new Random().nextInt(2)),
        (int) ((Math.random() * 900000) + 100) / 100.0, new Date());
    log.info("Transaction Posted => " + alert);
  }
}
