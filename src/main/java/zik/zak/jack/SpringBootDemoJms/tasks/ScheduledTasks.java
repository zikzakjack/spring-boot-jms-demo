package zik.zak.jack.SpringBootDemoJms.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import zik.zak.jack.SpringBootDemoJms.services.ATMService;

/**
 * ScheduledTask that generates random transactions for testdata
 * 
 * @author kalyan
 *
 */
@Component
public class ScheduledTasks {

  ATMService atmService;

  @Autowired
  public ScheduledTasks(ATMService atmService) {
    this.atmService = atmService;
  }

  @Scheduled(initialDelay = 5000, fixedRate = 10000)
  public void generateDummyTransactions() {
    atmService.transact();
  }

}
