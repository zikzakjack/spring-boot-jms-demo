package zik.zak.jack.SpringBootDemoJms.jms;

import zik.zak.jack.SpringBootDemoJms.domain.TransactionAlert;

/**
 * Abstract the class for publishing messages to Topic
 * 
 * @author kalyan
 *
 */
public interface TopicPublisher {

  void publish(TransactionAlert alert);

}
