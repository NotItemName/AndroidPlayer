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
public class ArtistDao {

    @Bean
    DBHelper dbHelper;

    public int addArtist(String name) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        Cursor cursor = database.rawQuery("select id from artist where name = ?", new String[]{name});
        if (cursor.moveToNext()) {
            return cursor.getInt(0);
        }
        ContentValues artistCv = new ContentValues();
        artistCv.put("name", name);
        return (int) database.insert("artist", null, artistCv);
    }

    public Cursor getAllArtists() {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        return database.rawQuery("select id as _id, name as name from artist order by name", null);
    }
}
