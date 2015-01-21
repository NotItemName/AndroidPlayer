package com.player.controller;

import com.player.model.genres.GenreDto;
import com.player.model.genres.Genres;
import com.player.service.genres.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
        return genreService.addGenre(genreDto);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public Genres getAllGenre() {
        Genres genres = new Genres();
        genres.setGenreDtos(genreService.getAllGenres());
        return genres;
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    @ResponseBody
    public GenreDto getGenreById(@PathVariable Integer id) {
        return genreService.getGenreById(id);
    }
}
