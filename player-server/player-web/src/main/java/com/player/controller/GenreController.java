package com.player.controller;

import com.player.entity.Genre;
import com.player.model.genres.GenreDto;
import com.player.model.genres.Genres;
import com.player.service.genres.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static com.player.convertors.GenreConverter.convert;
import static com.player.convertors.GenreConverter.convertList;

/**
 * @author Mykola_Zalyayev
 */
@Controller
@RequestMapping(value = "/genre")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public GenreDto addGenre(@RequestBody GenreDto genreDto) {
        Genre genre = genreService.addGenre(convert(genreDto));
        return convert(genre);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public Genres getAllGenre() {
        Genres genres = new Genres();
        genres.setGenreDtos(convertList(genreService.getAllGenres()));
        return genres;
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    @ResponseBody
    public GenreDto getGenreById(@PathVariable Integer id) {
        Genre genre = genreService.getGenreById(id);
        return convert(genre);
    }
}
