package com.jd.config;

import org.slf4j.Logger;
import org.springframework.core.io.Resource;

import java.io.IOException;


/**
 * Created by chenminghe on 2017/6/25.
 */
public class PropertiesResourcesLogger {

	private boolean logPrinted;

	private Logger logger;

	public PropertiesResourcesLogger(Logger logger) {
		this.logger = logger;
		this.logPrinted = false;
	}

	public void log(String propsName, Resource[] res) {
		synchronized (this) {
			if (!this.logPrinted) {
				StringBuilder sb = new StringBuilder();
				if (res != null && res.length > 0) {
					sb.append("Using properties with name : " + propsName + "[");
					for (Resource rs : res) {
						if(rs != null){
							try {
								sb.append(rs.getURL() + ",");
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
					sb.append("]");
				}

				this.logger.info(sb.toString());
				this.logPrinted = true;
			}
		}
	}
}
