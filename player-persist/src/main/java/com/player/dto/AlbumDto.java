package com.player.dto;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * @author Mykola_Zalyayev
 */
@Entity
@Table(name = "ALBUM")
public class AlbumDto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "albumDto")
    private Set<SongDto> songs;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ARTIST_ID")
    private ArtistDto artistDto;

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

    public ArtistDto getArtistDto() {
        return artistDto;
    }

    public void setArtistDto(ArtistDto artistDto) {
        this.artistDto = artistDto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AlbumDto albumDto = (AlbumDto) o;

        if (id != null ? !id.equals(albumDto.id) : albumDto.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
