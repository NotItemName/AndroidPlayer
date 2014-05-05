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
public class AlbumDao {
    @Bean
    DBHelper dbHelper;


    public int addAlbum(String name, Integer year, Integer artistId) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        Cursor cursor = database.rawQuery("select id from album where name = ? and artist_id = ?", new String[]{name, String.valueOf(artistId)});
        if (cursor.moveToNext()) {
            return cursor.getInt(0);
        }
        ContentValues albumCv = new ContentValues();
        albumCv.put("name", name);
        albumCv.put("year", year);
        albumCv.put("artist_id", artistId);
        return (int) database.insert("album", null, albumCv);
    }

    public Cursor getAlbumsByArtist(long artistId) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        return database.rawQuery("select id as _id, name as name, year as year " +
                "from album where artist_id = ? order by year", new String[]{String.valueOf(artistId)});
    }

    public Cursor getAllAlbums() {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        return database.rawQuery("select id as _id, name as name from album order by name", null);
    }
}
