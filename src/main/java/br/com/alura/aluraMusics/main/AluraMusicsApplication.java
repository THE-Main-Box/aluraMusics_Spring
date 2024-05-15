package br.com.alura.aluraMusics.main;

import br.com.alura.aluraMusics.repository.ArtistRepository;
import br.com.alura.aluraMusics.repository.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("br.com.alura.aluraMusics.repository")
@EntityScan("br.com.alura.aluraMusics.entity")
@ComponentScan("br.com.alura.aluraMusics.repository")
public class AluraMusicsApplication implements CommandLineRunner {

	private MusicRepository musicRepository;
	private ArtistRepository artistRepository;

    public static void main(String[] args) {
		SpringApplication.run(AluraMusicsApplication.class, args);
	}

	@Autowired
	public AluraMusicsApplication(MusicRepository musicRepository, ArtistRepository artistRepository) {
		this.musicRepository = musicRepository;
		this.artistRepository = artistRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		Main main = new Main(this.musicRepository, this.artistRepository);

		main.showMenu();

	}
}
