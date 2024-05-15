package br.com.alura.aluraMusics.main;

import br.com.alura.aluraMusics.entity.Artist;
import br.com.alura.aluraMusics.entity.Music;
import br.com.alura.aluraMusics.model.GroupType;
import br.com.alura.aluraMusics.repository.ArtistRepository;
import br.com.alura.aluraMusics.repository.MusicRepository;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {

    private Scanner reader = new Scanner(System.in);

    private MusicRepository musicRepository;
    private ArtistRepository artistRepository;

    private Artist artist;

    private Music music;

    public Main(MusicRepository musicRepository, ArtistRepository artistRepository) {
        this.musicRepository = musicRepository;
        this.artistRepository = artistRepository;
    }

    public void showMenu() {
        int choice = -1;

        try {
            while (choice != 0) {
                System.out.println(String.format("""
                        1 - Cadastrar Artista
                        2 - Cadastrar Música
                                                
                        3 - Listar Músicas
                        4 - Listar Artistas
                                                
                        5 - Buscar Música por ARTISTA
                        6 - Buscar Música por NOME
                                                
                        0 - SAIR
                        """
                ));

                choice = reader.nextInt();
                reader.nextLine();
                if (choice == 0) break;

                switch (choice) {
                    case 1:
                        this.setArtist();
                        break;
                    case 2:
                        this.setArtist();
                        this.setMusic();
                        break;
                    case 3:
                        this.listMusics();
                        break;
                    case 4:
                        this.listArtists();
                        break;
                    case 5:
                        this.searchMusicByArtist();
                        break;
                    case 6:
                        this.searchMusicByName();
                        break;
                    case 0:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Insira uma escolha válido");
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("Houve um erro tente novamente: " + e);
        }

    }

    private void searchMusicByName() {
        System.out.println("Qual a música");
        String userResponse = reader.nextLine();

        try {
            List<Music> musicList = musicRepository.findByTitleContainingIgnoreCase(userResponse);

            musicList.forEach(music -> {
                System.out.println();
                System.out.println("Music............:  " + music.getTitle());
                System.out.println("ArtistName.......:  " + music.getArtist().getName());
                System.out.println();
            });
        }catch (Exception e){
            System.out.println("Não foi possivel encontrar sua música");
        }
    }

    private void searchMusicByArtist() {

        try {
            System.out.println("Informe o ARTISTA");
            String userResponse = reader.nextLine();

            Artist existingArtist = artistRepository.findByNameIgnoreCase(userResponse);

            existingArtist.getMusicList().forEach(music -> {
                System.out.println();
                System.out.println("Music............:  " + music.getTitle());
                System.out.println("ArtistName.......:  " + music.getArtist().getName());
                System.out.println();
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void listArtists() {
        try {
            artistRepository.findAll()
                    .forEach(a -> {
                        System.out.println();
                        System.out.println("Name.......:  " + a.getName());
                        System.out.println("ArtistGroup:    " + a.getGroup());
                        System.out.println();
                    });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void listMusics() {
        try {
            musicRepository.findAll()
                    .forEach(m -> {
                        System.out.println();
                        System.out.println("Title......:  " + m.getTitle());
                        System.out.println("ArtistName:  " + m.getArtist().getName());
                        System.out.println();

                    });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

//  método para criar um artista e setar seus dados no banco caso ainda não esteja persistido caso esteja o seleciona
    private void setArtist() throws Exception {
        try {
            System.out.println("Informe o ARTISTA");
            String userResponse = reader.nextLine();

            Artist existingArtist = artistRepository.findByNameIgnoreCase(userResponse);


            if (existingArtist != null) {
                this.artist = existingArtist;
            } else {
                System.out.println("Esse ARTISTA trabalha como (Solo, Dueto, Banda)");
                String artistGroup = reader.nextLine();

                GroupType groupType = GroupType.fromString(artistGroup);

                this.artist = new Artist(userResponse, groupType);
                artistRepository.save(this.artist);
            }

            System.out.println(this.artist);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private void setMusic() {
        try {
            System.out.println("Qual o título da música?");
            String musicTitle = reader.nextLine();

            Optional<Music> music1 = artistRepository.findByNameIgnoreCase(this.artist.getName())
                    .getMusicList()
                    .stream()
                    .filter(m -> m.getTitle().equalsIgnoreCase(musicTitle))
                    .findFirst();

            if (music1.isPresent()) {
                System.out.println("Essa música já está na lista do artista.");
            } else {
                this.music = new Music(this.artist, musicTitle);
                this.artist.addToMusicList(this.music);

                artistRepository.save(this.artist);
                musicRepository.save(this.music);

                System.out.println("Música adicionada com sucesso.");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



}
