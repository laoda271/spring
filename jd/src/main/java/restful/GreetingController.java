package restful;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by chenminghe on 2017/6/23.
 */
@RestController
public class GreetingController {

	public static final String template = "a,s%";

	private final AtomicLong counter = new AtomicLong();

	@RequestMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "ytt") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}

}


