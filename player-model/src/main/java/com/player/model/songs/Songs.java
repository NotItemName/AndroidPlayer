package com.player.model.songs;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author Николай
 */
@XmlRootElement(name = "songs")
public class Songs {

    private List<SongDto> songDtos;

    @XmlElement(name = "song")
    public List<SongDto> getSongDtos() {
        return songDtos;
    }

    public void setSongDtos(List<SongDto> songDtos) {
        this.songDtos = songDtos;
    }
}
