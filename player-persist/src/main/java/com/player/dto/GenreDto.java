package com.player.dto;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * @author Mykola_Zalyayev
 */
@Entity
@Table(name = "GENRE")
public class GenreDto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "genreDto")
    private Set<SongDto> songs;

    public Set<SongDto> getSongs() {
        return songs;
    }

    public void setSongs(Set<SongDto> songs) {
        this.songs = songs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GenreDto genreDto = (GenreDto) o;

        if (id != null ? !id.equals(genreDto.id) : genreDto.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
