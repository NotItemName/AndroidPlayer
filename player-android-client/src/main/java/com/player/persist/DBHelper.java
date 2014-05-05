package com.player.persist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

/**
 * @author Николай
 */
@EBean
public class DBHelper extends SQLiteOpenHelper {

    @RootContext
    Context context;

    public DBHelper(Context context) {
        super(context, "player_db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE `artist` (" +
                " `id` INTEGER primary key autoincrement," +
                " `NAME` varchar(255) NOT NULL)");

        sqLiteDatabase.execSQL("CREATE TABLE `album` (" +
                " `id` INTEGER primary key autoincrement," +
                " `NAME` varchar(255) NOT NULL," +
                " `YEAR` INTEGER DEFAULT NULL," +
                " `ARTIST_ID` INTEGER DEFAULT NULL," +
                " FOREIGN KEY(ARTIST_ID) REFERENCES artist(id))");

        sqLiteDatabase.execSQL("CREATE TABLE `genre` (" +
                " `id` INTEGER primary key autoincrement," +
                " `NAME` varchar(255) NOT NULL)");

        sqLiteDatabase.execSQL("CREATE TABLE `song` (" +
                " `id` INTEGER primary key autoincrement," +
                " `FILE_NAME` varchar(255) DEFAULT NULL," +
                " `NAME` varchar(255) NOT NULL," +
                " `TRACK_NUMBER` INTEGER DEFAULT NULL," +
                " `ALBUM_ID` INTEGER DEFAULT NULL," +
                " `ARTIST_ID` INTEGER DEFAULT NULL," +
                " `GENRE_ID` INTEGER DEFAULT NULL," +
                " FOREIGN KEY(ARTIST_ID) REFERENCES artist(id)," +
                " FOREIGN KEY(ALBUM_ID) REFERENCES album(id)," +
                " FOREIGN KEY(GENRE_ID) REFERENCES genre(id))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

    }
}
