package org.jeecg.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * FineReport支持
 * @Author scott
 *
 */
@Configuration
public class FineReportConfig{
	/*
	 * FR报表集成
	 */
	@Bean
	public ServletRegistrationBean fineReportServlet() {
		return new ServletRegistrationBean(new com.fr.web.ReportServlet(), "/ReportServer");
	}
}
