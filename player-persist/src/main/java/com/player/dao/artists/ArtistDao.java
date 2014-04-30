package com.player.dao.artists;

import com.player.dto.ArtistDto;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Mykola_Zalyayev
 */
@Component
public class ArtistDao {

    @Autowired
    private SessionFactory sessionFactory;

    public ArtistDto addArtist(ArtistDto artist) {
        Session session = sessionFactory.getCurrentSession();
        session.save(artist);

        return artist;
    }
}
