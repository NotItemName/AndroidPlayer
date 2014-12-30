package com.player.dao;

import com.player.dto.AlbumDto;
import com.player.dto.ArtistDto;
import com.player.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Mykola_Zalyayev
 */
@Component
public class AlbumDao {

    @Autowired
    private AlbumRepository repository;

    public AlbumDto save(AlbumDto dto) {
        String name = dto.getName();
        if (name == null) {
            return null;
        }
        ArtistDto artistDto = dto.getArtistDto();
        String artistName = artistDto == null ? null : artistDto.getName();

        AlbumDto readAlbum = repository.findByNameAndArtistDto_Name(dto.getName(), artistName);
        if (readAlbum == null) {
            return repository.save(dto);
        }
        return readAlbum;
    }
}
