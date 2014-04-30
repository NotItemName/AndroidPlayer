package com.player.dto;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * @author Mykola_Zalyayev
 */
@Entity
@Table(name = "ARTIST")
public class ArtistDto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "artistDto")
    private Set<SongDto> songs;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "artistDto")
    private Set<AlbumDto> albums;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<SongDto> getSongs() {
        return songs;
    }

    public void setSongs(Set<SongDto> songs) {
        this.songs = songs;
    }

    public Set<AlbumDto> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<AlbumDto> albums) {
        this.albums = albums;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArtistDto artistDto = (ArtistDto) o;

        if (id != null ? !id.equals(artistDto.id) : artistDto.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
