package com.player.service.albums;

import com.player.model.albums.AlbumDto;

import java.util.List;

/**
 * @author Mykola_Zalyayev
 */
public interface AlbumService {
    AlbumDto addAlbum(AlbumDto albumDto);

    List<AlbumDto> getAllAlbums();

    AlbumDto getAlbumById(Integer id);
}
