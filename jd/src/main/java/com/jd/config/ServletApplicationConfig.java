package com.jd.config;

import com.jd.annotation.ScanIgnore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.*;
import org.springframework.core.io.ResourceLoader;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.annotation.PostConstruct;

/**
 * @Configuration:等价于XML中配置beans；用@Bean标注方法等价于XML中配置bean。
 * @ImportResource:等价于<import resource="cons-injecxml" />
 * @EnableAsync:在spring中启用@Async
 * @EnableScheduling:允许定时任务 <p>
 * Created by chenminghe on 2017/6/24.
 */
@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy(
		proxyTargetClass = true
)
@ComponentScan(
		basePackages = {"com.jd"},
		excludeFilters = {
				@ComponentScan.Filter(
						type = FilterType.ANNOTATION,
						value = {ScanIgnore.class}
				)
		}
)
@ImportResource({"classpath:${spring.scan.xml:spring-beans-*.xml}"})
@PropertySource(
		value = {"classpath:${spring.scan.props:spring-props-*.properties}"},
		ignoreResourceNotFound = true
)
@EnableAsync
@EnableScheduling
public class ServletApplicationConfig implements ResourceLoaderAware {

	private static final Logger LOG = LoggerFactory.getLogger(ServletApplicationConfig.class);

	private static String ERROR_PAGE;

	private static PropertiesResourcesLogger propertiesResourceLogger;

	private ResourceLoader resourceLoader;

	@Value("#{rootprops['spring.scan.packages']}")
	private String springPackageScan;

	@Value("#rootprops['spring.scan.xml']")
	private String springXmlScan;

	@Value("#rootprops['spring.scan.props']")
	private String springPropsScan;

	@Value("#rootprops['error.page']")
	private String errorPage;

	public ServletApplicationConfig() {
	}

	public static String getErrorPage() {
		return ERROR_PAGE;
	}

	@PostConstruct
	private void log() {
		LOG.info("Using web props 'spring.scan.packages'=" + (this.springPackageScan == null ? "com.jd(default)" : this.springPackageScan));
		LOG.info("Using web props 'spring.scan.xml'=" + (this.springXmlScan == null ? "spring-beans-*.xml(default)" : this.springXmlScan));
		LOG.info("Using web props 'spring.scan.props'=" + (this.springPropsScan == null ? "spring-props-*.properties(default)" : this.springPropsScan));
		ERROR_PAGE = this.errorPage == null ? "error" : this.errorPage;
		LOG.info("Using web props 'error.page'=" + (this.errorPage == null ? "error(default)" : this.errorPage));
	}

	@Bean(
			name = {"props"}
	)

	@Override
	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}
}
