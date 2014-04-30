package com.player.dao.songs;

import com.player.dto.SongDto;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author Mykola_Zalyayev
 */
@Repository
public class SongDao {

    @Autowired
    private SessionFactory sessionFactory;

    public SongDto addSong(SongDto song) {
        Session session = sessionFactory.getCurrentSession();
        session.save(song);

        return song;
    }
}
