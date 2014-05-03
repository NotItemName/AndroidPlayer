package com.player.dao;

import com.player.dto.SongDto;
import org.hibernate.Query;
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

    public boolean checkSongExist(String fileName) {
        String hql = "from SongDto where fileName = :fileName";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hql);
        query.setString("fileName", fileName);
        SongDto dto = (SongDto) query.uniqueResult();
        return dto != null;
    }
}
