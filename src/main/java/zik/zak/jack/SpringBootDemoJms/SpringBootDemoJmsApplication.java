package zik.zak.jack.SpringBootDemoJms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringBootDemoJmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDemoJmsApplication.class, args);
	}
}
