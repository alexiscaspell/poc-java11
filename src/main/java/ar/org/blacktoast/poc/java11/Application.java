package ar.org.blacktoast.poc.java11;

import static ar.org.blacktoast.poc.java11.Application.BASE_PACKAGE;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import ar.org.blacktoast.poc.java11.configuration.ConfigurationReader;

@SpringBootApplication
@ComponentScan(basePackages={BASE_PACKAGE,"io.blacktoast.utils"})
//EXCLUYE LA CONEXION A LA BD
//@ComponentScan(excludeFilters=@ComponentScan.Filter(type = FilterType.ASPECTJ, pattern = BASE_PACKAGE+"repository*"))
//@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class Application {

	public static final String PROJECT_NAME = "poc-java11";
	public static final String BASE_PACKAGE = "ar.org.blacktoast.poc.java11";

	public static void main(String[] args) {
		ConfigurationReader.loadProperties();
		SpringApplication.run(Application.class, args);
	}

}
