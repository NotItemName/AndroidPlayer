package com.player.controller;

import com.player.service.songs.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author Николай
 */
@Controller
public class SongController {

    @Autowired
    private SongService songService;

}
