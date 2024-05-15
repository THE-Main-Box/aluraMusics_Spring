package br.com.alura.aluraMusics.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "musics", schema = "musics")
public class Music {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Artist artist;

    private String title;

    public Music(Artist artist, String title) {
        this.artist = artist;
        this.title = title;
    }

    public Music() {}

    public long getId() {
        return id;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "Music{" +
                "artist=" + artist +
                ", title='" + title + '\'' +
                '}';
    }
}
