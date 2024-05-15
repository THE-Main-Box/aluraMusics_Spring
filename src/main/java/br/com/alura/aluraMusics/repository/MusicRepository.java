package br.com.alura.aluraMusics.repository;

import br.com.alura.aluraMusics.entity.Music;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MusicRepository extends JpaRepository<Music, Long> {

    List<Music> findByTitleContainingIgnoreCase(String userResponse);

}
