package io.blacktoast.test;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import ar.org.blacktoast.poc.java11.Application;
import ar.org.blacktoast.poc.java11.configuration.ConfigurationReader;


@SpringBootApplication
@ComponentScan({Application.BASE_PACKAGE+".configuration","io.blacktoast.utils.bean","io.blacktoast.utils.redis"})
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class ApplicationTestRedis {

	public static final String PROJECT_NAME = Application.PROJECT_NAME;
	
	public ApplicationTestRedis() {
		super();
		ConfigurationReader.loadProperties();
	}
}
