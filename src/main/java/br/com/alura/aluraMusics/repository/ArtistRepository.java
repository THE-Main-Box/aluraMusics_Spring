package br.com.alura.aluraMusics.repository;

import br.com.alura.aluraMusics.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {
    boolean existsByName(String name);

    Artist findByNameIgnoreCase(String name);
}
