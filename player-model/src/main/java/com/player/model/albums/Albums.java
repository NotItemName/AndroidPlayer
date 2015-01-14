package com.player.model.albums;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author Mykola_Zalyayev
 */
@XmlRootElement(name = "albums")
@JsonRootName("albums")
public class Albums {

    private List<AlbumDto> albumDtos;

    @XmlElement(name = "album")
    @JsonProperty("album")
    public List<AlbumDto> getAlbumDtos() {
        return albumDtos;
    }

    public void setAlbumDtos(List<AlbumDto> albumDtos) {
        this.albumDtos = albumDtos;
    }
}
