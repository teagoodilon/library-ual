package br.com.compass.pb.libraryual;

import br.com.compass.pb.libraryual.config.WebMvcConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(WebMvcConfig.class)
public class LibraryUalApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryUalApplication.class, args);
	}

}
