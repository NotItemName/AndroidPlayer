package com.player.service.albums;

import com.player.dao.AlbumDao;
import com.player.entity.Album;
import com.player.model.albums.AlbumDto;
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
    private AlbumDao albumDao;

    @Override
    public AlbumDto addAlbum(AlbumDto albumDto) {
        Album album = albumDao.save(convert(albumDto));
        return convert(album);
    }

    @Override
    public List<AlbumDto> getAllAlbums() {
        return convertList(albumDao.findAll());
    }

    @Override
    public AlbumDto getAlbumById(Integer id) {
        return convert(albumDao.findOne(id));
    }

    @Override
    public void updateAlbum(AlbumDto albumDto) {
        albumDao.update(convert(albumDto));
    }

    @Override
    public void deleteAlbum(Integer id) {
        albumDao.delete(id);
    }
}
