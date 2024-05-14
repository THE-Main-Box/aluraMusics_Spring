package br.com.alura.aluraMusics.main;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan("br.com.alura.aluraMusics.entity")
@ComponentScan("br.com.alura.aluraMusics.repository")
public class AluraMusicsApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(AluraMusicsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Main main = new Main();

	}
}
