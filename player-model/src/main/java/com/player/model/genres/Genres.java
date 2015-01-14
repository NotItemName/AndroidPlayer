package com.player.model.genres;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author Mykola_Zalyayev
 */
@XmlRootElement(name = "genres")
@JsonRootName("genres")
public class Genres {

    private List<GenreDto> genreDtos;

    @XmlElement(name = "genre")
    @JsonProperty("genre")
    public List<GenreDto> getGenreDtos() {
        return genreDtos;
    }

    public void setGenreDtos(List<GenreDto> genreDtos) {
        this.genreDtos = genreDtos;
    }
}
