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
public class GenreDao {

    @Bean
    DBHelper dbHelper;

    public int addGenre(String name) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        Cursor cursor = database.rawQuery("select id from genre where name = ?", new String[]{name});
        if (cursor.moveToNext()) {
            return cursor.getInt(0);
        }
        ContentValues artistCv = new ContentValues();
        artistCv.put("name", name);
        return (int) database.insert("genre", null, artistCv);
    }
}
