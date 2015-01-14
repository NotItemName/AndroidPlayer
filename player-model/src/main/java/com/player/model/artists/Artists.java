package com.player.model.artists;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author Mykola_Zalyayev
 */
@XmlRootElement(name = "artists")
@JsonRootName("artists")
public class Artists {

    private List<ArtistDto> artistDtos;

    @XmlElement(name = "artist")
    @JsonProperty("artist")
    public List<ArtistDto> getArtistDtos() {
        return artistDtos;
    }

    public void setArtistDtos(List<ArtistDto> artistDtos) {
        this.artistDtos = artistDtos;
    }
}
