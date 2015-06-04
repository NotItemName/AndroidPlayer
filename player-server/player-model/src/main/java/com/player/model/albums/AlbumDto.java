package com.player.model.albums;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.List;

/**
 * @author Mykola_Zalyayev
 */
@JsonRootName("album")
public class AlbumDto {

    private Integer id;

    private String name;

    private Integer year;

    private String artist;

    private List<String> genres;

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("year")
    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @JsonProperty("artist")
    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    @JsonProperty("genres")
    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AlbumDto albumDto = (AlbumDto) o;

        if (artist != null ? !artist.equals(albumDto.artist) : albumDto.artist != null) {
            return false;
        }
        if (id != null ? !id.equals(albumDto.id) : albumDto.id != null) {
            return false;
        }
        if (name != null ? !name.equals(albumDto.name) : albumDto.name != null) {
            return false;
        }
        if (year != null ? !year.equals(albumDto.year) : albumDto.year != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (year != null ? year.hashCode() : 0);
        result = 31 * result + (artist != null ? artist.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AlbumDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", year=" + year +
                ", artist='" + artist + '\'' +
                '}';
    }
}
