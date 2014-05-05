package com.player.dao;

import com.player.dto.SongDto;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    public String getFileName(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        SongDto songDto = (SongDto) session.get(SongDto.class, id);
        return songDto.getFileName();
    }

    public List<SongDto> getAllSongs() {
        String hql = "from SongDto";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hql);
        return query.list();
    }
}
