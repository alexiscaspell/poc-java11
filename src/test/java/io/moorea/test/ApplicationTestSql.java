package io.blacktoast.test;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import ar.org.blacktoast.poc.java11.Application;
import ar.org.blacktoast.poc.java11.configuration.ConfigurationReader;


@SpringBootApplication
@ComponentScan({Application.BASE_PACKAGE,"io.blacktoast.utils"})
@ComponentScan({Application.BASE_PACKAGE+".configuration","io.blacktoast.utils.bean",Application.BASE_PACKAGE+".repository"})
public class ApplicationTestSql {

	public static final String PROJECT_NAME = Application.PROJECT_NAME;
	
	public ApplicationTestSql() {
		super();
		ConfigurationReader.loadProperties();
	}
}
