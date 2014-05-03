package com.player.dao;

import com.player.dto.AlbumDto;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author Николай
 */
@Repository
public class AlbumDao {

    @Autowired
    private SessionFactory sessionFactory;

    public AlbumDto addAlbum(AlbumDto album) {
        Session session = sessionFactory.getCurrentSession();

        AlbumDto albumDto = getArtistByName(album);
        if (albumDto != null) {
            return albumDto;
        }

        session.save(album);
        return album;
    }

    public AlbumDto getArtistByName(AlbumDto entity) {
        String hql = "from AlbumDto where name = :name";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hql);
        query.setString("name", entity.getName());
        return (AlbumDto) query.uniqueResult();
    }
}
