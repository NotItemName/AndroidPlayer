package com.player.service.albums;

import com.google.common.collect.Lists;
import com.player.entity.Album;
import com.player.entity.Artist;
import com.player.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Mykola_Zalyayev
 */
@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

    @Override
    public Album addAlbum(Album entity) {
        String name = entity.getName();
        if (name == null) {
            return null;
        }
        Artist artist = entity.getArtist();
        String artistName = artist == null ? null : artist.getName();

        Album album = albumRepository.findByNameAndArtist_Name(entity.getName(), artistName);
        if (album == null) {
            album = albumRepository.save(entity);
        }

        return album;
    }

    @Override
    public List<Album> getAllAlbums() {
        return Lists.newArrayList(albumRepository.findAll());
    }

    @Override
    public Album getAlbumById(Integer id) {
        return albumRepository.findOne(id);
    }

    @Override
    public void updateAlbum(Album album) {
        albumRepository.save(album);
    }

    @Override
    public void deleteAlbum(Integer id) {
        albumRepository.delete(id);
    }
}
