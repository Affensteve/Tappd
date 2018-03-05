package de.tappd.Tappd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("de.tappd.Tappd")
public class TappdApplication {

	public static void main(String[] args) {
		SpringApplication.run(TappdApplication.class, args);
	}

}
