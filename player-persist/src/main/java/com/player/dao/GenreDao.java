package com.player.dao;

import com.player.dto.GenreDto;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author Николай
 */
@Repository
public class GenreDao {

    @Autowired
    private SessionFactory sessionFactory;

    public GenreDto addGenre(GenreDto genre) {
        Session session = sessionFactory.getCurrentSession();

        GenreDto genreDto = getArtistByName(genre);
        if (genreDto != null) {
            return genreDto;
        }

        session.save(genre);
        return genre;
    }

    public GenreDto getArtistByName(GenreDto entity) {
        String hql = "from GenreDto where name = :name";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hql);
        query.setString("name", entity.getName());
        return (GenreDto) query.uniqueResult();
    }
}
