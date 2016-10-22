package zik.zak.jack.SpringBootDemoJms.jms;

import zik.zak.jack.SpringBootDemoJms.domain.TransactionAlert;

/**
 * Abstract the Listener class for receiving messages from Topic
 * 
 * @author kalyan
 *
 */
public interface TopicSubscriber {

  void subscribe(TransactionAlert alert);

}
