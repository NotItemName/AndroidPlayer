package com.player.service.genres;

import com.player.dao.GenreDao;
import com.player.entity.Genre;
import com.player.model.genres.GenreDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.player.service.genres.GenreConverter.convert;
import static com.player.service.genres.GenreConverter.convertList;

/**
 * @author Mykola_Zalyayev
 */
@Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    private GenreDao genreDao;

    @Override
    public GenreDto addGenre(GenreDto genreDto) {
        Genre genre = genreDao.save(convert(genreDto));
        return convert(genre);
    }

    @Override
    public List<GenreDto> getAllGenres() {
        return convertList(genreDao.findAll());
    }

    @Override
    public GenreDto getGenreById(Integer id) {
        return convert(genreDao.findOne(id));
    }
}
