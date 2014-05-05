package com.player.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author Николай
 */
@XmlRootElement(name = "songs")
public class Songs {

    List<Song> songs;

    @XmlElement(name = "song")
    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }
}
