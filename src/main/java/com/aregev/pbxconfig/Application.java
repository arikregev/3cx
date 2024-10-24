package com.aregev.pbxconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.aregev.pbxconfig"})
public class Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext fa = SpringApplication.run(Application.class, args);

		fa.getBean(Execution.class).mainExecution();
	}

}
