package hello;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by chenminghe on 2017/6/23.
 */
@Configuration
@ComponentScan
public class Application {

	@Bean
	MessageService mockMessageService() {
		return new MessageService() {
			@Override
			public String getMessage() {
				return "ok";
			}
		};
	}

//	public static void main(String[] args) {
//		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
//		MessagePrinter printer = context.getBean(MessagePrinter.class);
//		printer.printMessage();
//	}
}
