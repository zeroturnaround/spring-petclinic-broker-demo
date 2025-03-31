package org.zt.demo.supplements.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebListener;
import java.util.Map;

@WebListener
public class ServletPathLoggingListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext context = sce.getServletContext();

		log("===== Servlet Application Started =====");
		log("Registered Servlet Mappings:");

		Map<String, ? extends ServletRegistration> servletRegistrations = context.getServletRegistrations();

		for (Map.Entry<String, ? extends ServletRegistration> entry : servletRegistrations.entrySet()) {
			String servletName = entry.getKey();
			ServletRegistration registration = entry.getValue();

			for (String mapping : registration.getMappings()) {
				log(String.format("Servlet: %s, Path: %s", servletName, mapping));
			}
		}

		log("===== Servlet Mapping Logging Complete =====");
	}

	private void log(String entry) {
		System.out.println(entry);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		sce.getServletContext().log("===== Servlet Application Stopped =====");
	}
}
