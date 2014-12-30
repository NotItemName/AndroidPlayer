package com.player.dao;

import com.player.dto.AlbumDto;
import com.player.dto.ArtistDto;
import com.player.dto.SongDto;
import com.player.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Mykola_Zalyayev
 */
@Component
public class SongDao {

    @Autowired
    private SongRepository repository;


    public SongDto save(SongDto dto) {
        String name = dto.getName();
        AlbumDto albumDto = dto.getAlbumDto();
        String album = albumDto == null ? null : albumDto.getName();
        ArtistDto artistDto = dto.getArtistDto();
        String artist = artistDto == null ? null : artistDto.getName();

        if (name == null) {
            return null;
        }
        SongDto readSong = repository.findByNameAndAlbumDto_NameAndArtistDto_Name(name, album, artist);
        if (readSong == null) {
            return repository.save(dto);
        }

        return readSong;
    }

    public SongDto findByFileName(String fileName) {
        return repository.findByFileName(fileName);
    }

    public SongDto findOne(Integer id) {
        return repository.findOne(id);
    }

    public Iterable<SongDto> findAll() {
        return repository.findAll();
    }
}
