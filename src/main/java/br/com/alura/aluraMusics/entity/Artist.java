package br.com.alura.aluraMusics.entity;


import br.com.alura.aluraMusics.model.GroupType;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "artists", schema = "musics")
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String  name;
    @Enumerated(EnumType.STRING)
    @Column(name = "artistGroup")
    private GroupType group;

    @OneToMany(mappedBy = "artist", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Music> musicList= new ArrayList<>();

    public Artist(String name, GroupType group) {
        this.name = name;
        this.group = group;
    }

    public Artist() {}

    public void setMusicList(List<Music> musicList) {
        this.musicList = musicList;
    }

    public void addToMusicList(Music music){
        this.musicList.add(music);
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Music> getMusicList() {
        return musicList;
    }

    public GroupType getGroup() {
        return group;
    }
}
