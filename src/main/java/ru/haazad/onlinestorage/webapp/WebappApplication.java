package ru.haazad.onlinestorage.webapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.haazad.onlinestorage.webapp.config.FlywayConfiguration;

@SpringBootApplication
public class WebappApplication {

	private static FlywayConfiguration flywayConfiguration;

	@Autowired
	public WebappApplication(FlywayConfiguration configuration) {
		flywayConfiguration = configuration;
	}

	public static void main(String[] args) {
		SpringApplication.run(WebappApplication.class, args);
		flywayConfiguration.flywayMigrate();
	}

}
