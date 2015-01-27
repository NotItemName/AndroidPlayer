package com.player.service.albums;

import com.google.common.collect.Lists;
import com.player.entity.Album;
import com.player.entity.Artist;
import com.player.repository.AlbumRepository;
import com.player.service.artists.ArtistService;
import com.player.service.genres.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Mykola_Zalyayev
 */
@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private ArtistService artistService;

    @Autowired
    private GenreService genreService;

    @Override
    @Transactional
    public Album addAlbum(Album entity) {
        String name = entity.getName();
        if (name == null) {
            return null;
        }
        Artist artist = entity.getArtist();
        artist = artistService.addArtist(artist);
        entity.setArtist(artist);
        entity.setGenres(genreService.addAllGenre(entity.getGenres()));
        String artistName = artist == null ? null : artist.getName();

        Album album = albumRepository.findByNameAndArtist_Name(entity.getName(), artistName);


        if (album == null) {
            album = albumRepository.save(entity);
        }


        return album;
    }

    @Override
    @Transactional
    public List<Album> getAllAlbums() {
        return Lists.newArrayList(albumRepository.findAll());
    }

    @Override
    @Transactional
    public Album getAlbumById(Integer id) {
        return albumRepository.findOne(id);
    }

    @Override
    @Transactional
    public void updateAlbum(Album album) {
        album.setGenres(genreService.addAllGenre(album.getGenres()));
        album.setArtist(artistService.addArtist(album.getArtist()));
        albumRepository.save(album);
    }

    @Override
    @Transactional
    public void deleteAlbum(Integer id) {
        albumRepository.delete(id);
    }
}
