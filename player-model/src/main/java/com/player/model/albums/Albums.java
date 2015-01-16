package com.player.model.albums;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.List;

/**
 * @author Mykola_Zalyayev
 */
@JsonRootName("albums")
public class Albums {

    private List<AlbumDto> albumDtos;

    @JsonProperty("album")
    public List<AlbumDto> getAlbumDtos() {
        return albumDtos;
    }

    public void setAlbumDtos(List<AlbumDto> albumDtos) {
        this.albumDtos = albumDtos;
    }
}
