package config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * 用@Configuration注解该类，等价 与XML中配置beans；用@Bean标注方法等价于XML中配置bean。
 *
 * Created by chenminghe on 2017/6/24.
 */
@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy(
	proxyTargetClass = true
)
@ComponentScan(
		basePackages = {"${spring.scan.packages:com.laoda271}"}
)
public class ServletApplicationConfig {
}
