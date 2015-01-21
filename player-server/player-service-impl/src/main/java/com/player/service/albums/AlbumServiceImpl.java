package com.player.service.albums;

import com.player.entity.Album;
import com.player.entity.Artist;
import com.player.model.albums.AlbumDto;
import com.player.repository.AlbumRepository;
import com.player.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.player.service.albums.AlbumConverter.convert;
import static com.player.service.albums.AlbumConverter.convertList;

/**
 * @author Mykola_Zalyayev
 */
@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private SongRepository songRepository;

    @Override
    public AlbumDto addAlbum(AlbumDto albumDto) {
        Album entity = convert(albumDto);
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

        return convert(album);
    }

    @Override
    public List<AlbumDto> getAllAlbums() {
        return convertList(albumRepository.findAll());
    }

    @Override
    public AlbumDto getAlbumById(Integer id) {
        return convert(albumRepository.findOne(id));
    }

    @Override
    public void updateAlbum(AlbumDto albumDto) {
        albumRepository.save(convert(albumDto));
    }

    @Override
    public void deleteAlbum(Integer id) {
        albumRepository.delete(id);
    }
}
