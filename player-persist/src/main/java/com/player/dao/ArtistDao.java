package com.player.dao;

import com.player.dto.ArtistDto;
import org.hibernate.Query;
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

        ArtistDto artistDto = getArtistByName(artist);
        if (artistDto != null) {
            return artistDto;
        }

        session.save(artist);
        return artist;
    }

    public ArtistDto getArtistByName(ArtistDto entity) {
        String hql = "from ArtistDto where name = :name";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hql);
        query.setString("name", entity.getName());
        return (ArtistDto) query.uniqueResult();
    }
}
