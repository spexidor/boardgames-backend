package se.hiq.boardgamesbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
public class BoardgamesBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoardgamesBackendApplication.class, args);
	}

}
