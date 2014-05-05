package com.player.persist.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.player.persist.DBHelper;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

/**
 * @author Николай
 */
@EBean
public class SongDao {
    @Bean
    DBHelper dbHelper;

    public int addAlbum(String name, Integer trackNumber, Integer artistId, Integer albumId, Integer genreId) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        Cursor cursor = database.rawQuery("select id from song where name = ?", new String[]{name});
        if (cursor.moveToNext()) {
            return cursor.getInt(0);
        }
        ContentValues albumCv = new ContentValues();
        albumCv.put("name", name);
        albumCv.put("track_number", trackNumber);
        albumCv.put("artist_id", artistId);
        albumCv.put("album_id", albumId);
        albumCv.put("genre_id", genreId);
        return (int) database.insert("song", null, albumCv);
    }

    public Cursor getSongsByAlbumId(long albumId) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        return database.rawQuery("select id as _id, name as name from song where album_id = ? " +
                "order by track_number", new String[]{String.valueOf(albumId)});
    }

    public Cursor getAllSongs() {
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        return database.rawQuery("select id as _id, name as name from song order by name", null);

    }
}
