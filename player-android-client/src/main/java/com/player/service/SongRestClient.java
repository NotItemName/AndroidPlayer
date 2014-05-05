package com.player.service;

import com.player.model.Songs;
import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Rest;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

/**
 * @author Николай
 */
@Rest(rootUrl = "http://109.86.225.206/player", converters = {MappingJackson2HttpMessageConverter.class})
public interface SongRestClient {

    @Get("/songs")
    Songs getAllSongs();
}
