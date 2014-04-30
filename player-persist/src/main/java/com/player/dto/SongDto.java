package com.player.dto;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Mykola_Zalyayev
 */
@Entity
@Table(name = "SONG", schema = "testdb")
public class SongDto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "TRACK_NUMBER")
    private Integer trackNumber;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ARTIST_ID")
    private ArtistDto artistDto;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ALBUM_ID")
    private AlbumDto albumDto;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "GENRE_ID")
    private GenreDto genreDto;

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

    public Integer getTrackNumber() {
        return trackNumber;
    }

    public void setTrackNumber(Integer trackNumber) {
        this.trackNumber = trackNumber;
    }

    public ArtistDto getArtistDto() {
        return artistDto;
    }

    public void setArtistDto(ArtistDto artistDto) {
        this.artistDto = artistDto;
    }

    public AlbumDto getAlbumDto() {
        return albumDto;
    }

    public void setAlbumDto(AlbumDto albumDto) {
        this.albumDto = albumDto;
    }

    public GenreDto getGenreDto() {
        return genreDto;
    }

    public void setGenreDto(GenreDto genreDto) {
        this.genreDto = genreDto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SongDto songDto = (SongDto) o;

        if (id != null ? !id.equals(songDto.id) : songDto.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
