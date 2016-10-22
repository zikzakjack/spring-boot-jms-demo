package zik.zak.jack.SpringBootDemoJms.jms;

import javax.jms.Queue;

import zik.zak.jack.SpringBootDemoJms.domain.TransactionAlert;

/**
 * Abstract the class for sending messages to Queue
 * 
 * @author kalyan
 *
 */
public interface QueueSender {

  void send(Queue queue, TransactionAlert alert);

}
