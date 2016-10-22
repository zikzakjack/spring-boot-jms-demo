package zik.zak.jack.SpringBootDemoJms;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Bootstrap the Spring Boot Application
 * 
 * @author kalyan
 *
 */
@SpringBootApplication
@EnableScheduling
@EnableJms
public class SpringBootDemoJmsApplication {

  public static void main(String[] args) {
    ApplicationContext context = SpringApplication.run(SpringBootDemoJmsApplication.class, args);
    // printBeanNames(context);
  }

  private static void printBeanNames(ApplicationContext context) {
    // for(String beanName : Arrays.sort(context.getBeanDefinitionNames())) {
    String[] beanNames = context.getBeanDefinitionNames();
    Arrays.sort(beanNames);
    for (int i = 0; i < beanNames.length; i++) {
      System.out.println("beanName:" + beanNames[i]);
    }
  }
}
