package com.player.model.artists;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.List;

/**
 * @author Mykola_Zalyayev
 */
@JsonRootName("artists")
public class Artists {

    private List<ArtistDto> artistDtos;

    @JsonProperty("artist")
    public List<ArtistDto> getArtistDtos() {
        return artistDtos;
    }

    public void setArtistDtos(List<ArtistDto> artistDtos) {
        this.artistDtos = artistDtos;
    }
}
