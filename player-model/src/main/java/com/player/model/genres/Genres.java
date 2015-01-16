package com.player.model.genres;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.List;

/**
 * @author Mykola_Zalyayev
 */
@JsonRootName("genres")
public class Genres {

    private List<GenreDto> genreDtos;

    @JsonProperty("genre")
    public List<GenreDto> getGenreDtos() {
        return genreDtos;
    }

    public void setGenreDtos(List<GenreDto> genreDtos) {
        this.genreDtos = genreDtos;
    }
}
