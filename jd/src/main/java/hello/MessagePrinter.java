package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by chenminghe on 2017/6/23.
 */
@Component
public class MessagePrinter {

	final private MessageService service;

	@Autowired
	public MessagePrinter(MessageService service) {
		this.service = service;
	}

	public void printMessage() {
		System.out.println(this.service.getMessage());
	}
}
