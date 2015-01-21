package com.player.model.songs;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.List;

/**
 * @author Николай
 */
@JsonRootName("songs")
public class Songs {

    private List<SongDto> songDtos;

    @JsonProperty("songs")
    public List<SongDto> getSongDtos() {
        return songDtos;
    }

    public void setSongDtos(List<SongDto> songDtos) {
        this.songDtos = songDtos;
    }
}
