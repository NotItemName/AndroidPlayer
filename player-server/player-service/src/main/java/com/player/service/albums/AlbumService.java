package com.player.service.albums;

import com.player.entity.Album;

import java.util.List;

/**
 * @author Mykola_Zalyayev
 */
public interface AlbumService {
    Album addAlbum(Album album);

    List<Album> getAllAlbums();

    Album getAlbumById(Integer id);

    void updateAlbum(Album album);

    void deleteAlbum(Integer id);
}
