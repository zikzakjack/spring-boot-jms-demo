package zik.zak.jack.SpringBootDemoJms.jms;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

/**
 * Convenient class to declare all the Bean defintions that will be processed by the Spring
 * container. Just my personal preference to put together all jms related beans here. This can also
 * be done in the main boot strap class
 * 
 * @author kalyan
 *
 */
@Configuration
public class JmsConfig {

  @Value("${spring.activemq.broker-url}")
  String brokerURL;

  @Value("${trans.alert.topic}")
  private String transactionAlertTopicName;

  @Value("${trans.alert.email.queue}")
  private String emailNotificationQueueName;

  @Value("${trans.alert.sms.queue}")
  private String smsNotificationQueueName;

  @Bean
  public Topic transactionAlertTopic() {
    return new ActiveMQTopic(transactionAlertTopicName);
  }

  @Bean
  public Queue emailNotificationQueue() {
    return new ActiveMQQueue(emailNotificationQueueName);
  }

  @Bean
  public Queue smsNotificationQueue() {
    return new ActiveMQQueue(smsNotificationQueueName);
  }

  @Bean // Serialize message content to json using TextMessage
  public MessageConverter jacksonJmsMessageConverter() {
    MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
    converter.setTargetType(MessageType.TEXT);
    converter.setTypeIdPropertyName("_type");
    return converter;
  }

  @Bean
  @Primary
  public ConnectionFactory queueConnectionFactory() {
    ActiveMQConnectionFactory queueConnectionFactory = new ActiveMQConnectionFactory();
    queueConnectionFactory.setTrustAllPackages(true);
    return queueConnectionFactory;
  }

  @Bean
  public ConnectionFactory topicConnectionFactory() {
    ActiveMQConnectionFactory topicConnectionFactory = new ActiveMQConnectionFactory();
    topicConnectionFactory.setTrustAllPackages(true);
    // CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
    return topicConnectionFactory;
  }

  @Bean
  public DefaultJmsListenerContainerFactory queueJmsListenerContainerFactory(ConnectionFactory queueConnectionFactory) {
    DefaultJmsListenerContainerFactory queueListenerFactory = new DefaultJmsListenerContainerFactory();
    queueListenerFactory.setConnectionFactory(queueConnectionFactory);
    queueListenerFactory.setPubSubDomain(false);
    queueListenerFactory.setMessageConverter(jacksonJmsMessageConverter());
    return queueListenerFactory;
  }

  @Bean
  public DefaultJmsListenerContainerFactory topicJmsListenerContainerFactory(ConnectionFactory topicConnectionFactory) {
    DefaultJmsListenerContainerFactory topicListenerFactory = new DefaultJmsListenerContainerFactory();
    topicListenerFactory.setConnectionFactory(topicConnectionFactory);
    topicListenerFactory.setPubSubDomain(true);
    topicListenerFactory.setMessageConverter(jacksonJmsMessageConverter());
    return topicListenerFactory;
  }

  @Bean
  public JmsTemplate queueJmsTemplate(ConnectionFactory queueConnectionFactory) {
    JmsTemplate queueJmsTemplate = new JmsTemplate();
    queueJmsTemplate.setConnectionFactory(queueConnectionFactory);
    queueJmsTemplate.setMessageConverter(jacksonJmsMessageConverter());
    return queueJmsTemplate;
  }

  @Bean
  public JmsTemplate topicJmsTemplate(ConnectionFactory topicConnectionFactory) {
    JmsTemplate topicJmsTemplate = new JmsTemplate();
    topicJmsTemplate.setConnectionFactory(topicConnectionFactory);
    topicJmsTemplate.setMessageConverter(jacksonJmsMessageConverter());
    return topicJmsTemplate;
  }
}
