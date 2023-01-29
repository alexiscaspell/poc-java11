package ar.org.blacktoast.poc.java11;



import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import ar.org.blacktoast.poc.java11.configuration.ConfigurationReader;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		ConfigurationReader.loadProperties();
		return application.sources(Application.class);
	}

}
