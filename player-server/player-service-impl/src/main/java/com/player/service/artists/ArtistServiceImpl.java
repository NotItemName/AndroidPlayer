package com.player.service.artists;

import com.player.dao.ArtistDao;
import com.player.entity.Artist;
import com.player.model.artists.ArtistDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.player.service.artists.ArtistConverter.convert;
import static com.player.service.artists.ArtistConverter.convertList;

/**
 * @author Mykola_Zalyayev
 */
@Service
public class ArtistServiceImpl implements ArtistService {

    @Autowired
    private ArtistDao artistDao;

    @Override
    public ArtistDto addArtist(ArtistDto artistDto) {
        Artist artist = artistDao.save(convert(artistDto));
        return convert(artist);
    }

    @Override
    public List<ArtistDto> getAllArtists() {
        return convertList(artistDao.findAll());
    }

    @Override
    public ArtistDto getArtistById(Integer id) {
        return convert(artistDao.findOne(id));
    }
}
