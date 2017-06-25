package com.jd.config;

import config.ServletApplicationConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/**
 * Servlet3.0规范，可以用Java来配置servlet，而不仅仅是web.xml
 * <p>
 * Servlet 配置类只需要继承AbstractAnnotationConfigDispatcherServletInitializer
 * <p>
 * Created by chenminghe on 2017/6/24.
 */
public class ApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	public static final Logger LOG = LoggerFactory.getLogger(ApplicationInitializer.class);

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		int majorVersion = servletContext.getMajorVersion();
		int minorVersion = servletContext.getMinorVersion();
		LOG.info("Container servlet version:{}.{}", majorVersion, minorVersion);
		int effectiveMajorVersion = servletContext.getEffectiveMajorVersion();
		int effectiveMinorVersion = servletContext.getEffectiveMinorVersion();
		LOG.info("App servlet version: {}.{}", effectiveMajorVersion, effectiveMinorVersion);
		Map<String, ? extends ServletRegistration> servletRegistrations = servletContext.getServletRegistrations();
		if (servletRegistrations != null && !servletRegistrations.isEmpty()) {
			Collection<? extends ServletRegistration> values = servletRegistrations.values();
			Iterator<? extends ServletRegistration> iter = values.iterator();
			while (iter.hasNext()) {
				ServletRegistration next = iter.next();
				String cn = next.getClassName();
				// 规范名称
				if (DispatcherServlet.class.getCanonicalName().equals(cn)) {
					LOG.info("DispatcherServlet is already registered in web.xml");
				}
			}
		}
		super.onStartup(servletContext);
	}

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[0];
	}

	/**
	 * 每个DispatcherServlet有自己的应用上下文（WebApplicationContext），
	 * 这个应用上下文只针对这个DispatcherServlet有用。
	 * 这也就是getServletConfigClasses的作用，获取这个DispatcherServlet的应用上下文的配置类。
	 * @return
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[]{ServletApplicationConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[]{"/"};
	}

	/**
	 * 如果你需要为 DispatcherServlet 添加 filter 的话，需要重写这个方法
	 *
	 * @return
	 */
	@Override
	protected javax.servlet.Filter[] getServletFilters() {
		return super.getServletFilters();
	}
}
