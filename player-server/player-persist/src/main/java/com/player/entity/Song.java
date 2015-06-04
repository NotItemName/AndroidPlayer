package com.player.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Mykola_Zalyayev
 */
@Entity
@Table(name = "SONG")
public class Song implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "TRACK_NUMBER")
    private Integer trackNumber;

    @Column(name = "FILE_NAME", unique = true)
    private String fileName;

    @ManyToOne
    @JoinColumn(name = "ARTIST_ID")
    private Artist artist;

    @ManyToOne
    @JoinColumn(name = "ALBUM_ID")
    private Album album;

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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Song song = (Song) o;

        return !(id != null ? !id.equals(song.id) : song.id != null);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
